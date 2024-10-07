package es.predictia.pdts.mapnikjni;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main entry point for the mapnik-jni library.
 * This class is responsible for initializing the mapnik library and registering
 * input plugins and fonts. Not all methods are mirrored in the native side.
 *
 * @author stella
 */
public class Mapnik {

	private Mapnik() {
		// Static class
	}

	private static boolean initialized;
	private static boolean registered;
	private static boolean initializationFailure;
	
	private static final Map<Class<? extends NativeObject>, AtomicInteger> nativeAllocCounts;
	static {
		nativeAllocCounts = new HashMap<>();
		nativeAllocCounts.put(Datasource.class, new AtomicInteger());
		nativeAllocCounts.put(FeatureSet.class, new AtomicInteger());
		nativeAllocCounts.put(FeatureTypeStyle.class, new AtomicInteger());
		nativeAllocCounts.put(Geometry.class, new AtomicInteger());
		nativeAllocCounts.put(Image.class, new AtomicInteger());
		nativeAllocCounts.put(Layer.class, new AtomicInteger());
		nativeAllocCounts.put(MapDefinition.class, new AtomicInteger());
		nativeAllocCounts.put(Projection.class, new AtomicInteger());
		nativeAllocCounts.put(Query.class, new AtomicInteger());
	}
	
	/**
	 * Return a Map of Object type to allocation count of current active native allocations.
	 * Native allocations are cleared either through an explicit call to dispose() or
	 * finalization.
	 * 
	 * @return Map of object type to count for all counts greater than zero
	 */
	public static Map<String,Integer> getNativeAllocations() {
		var ret = new HashMap<String, Integer>();

		for (var entry: nativeAllocCounts.entrySet()) {
			int count=entry.getValue().get();
			if (count==0) {
				continue;
			}
			
			var name = entry.getKey().getName();
			int dotPos = name.lastIndexOf('.');
			if (dotPos>=0) {
				name=name.substring(dotPos+1);
			}
			
			ret.put(name, count);
		}
		return ret;
	}
	
	/**
	 * Print the current native allocations to a string.
	 * Useful for debugging.
	 */
	public static CharSequence reportNativeAllocations() {
		var counts = getNativeAllocations();
		var buffer = new StringBuilder();
		buffer.append("MapnikAllocations(");
		boolean first = true;
		for (Map.Entry<String, Integer> entry: counts.entrySet()) {
			if (first) {
				first=false;
			} else {
				buffer.append(", ");
			}

			buffer.append(entry.getKey()).append("=").append(entry.getValue());
		}
		buffer.append(")");
		return buffer;
	}
	
	static void incrementAlloc(Class<? extends NativeObject> clazz, int delta) {
		var i = nativeAllocCounts.getOrDefault(clazz, null);
		if (i == null) {
			throw new IllegalStateException("Not allocation counter defined for " + clazz.getName());
		}
		i.addAndGet(delta);
	}
	
	/**
	 * Initialize the mapnik library.
	 * @param register If true, then also register input plugins and fonts
	 */
	public static synchronized void initialize(boolean register) {
		if (!initialized) {
			if (initializationFailure) {
				throw new IllegalStateException("Previous call to Mapnik.initialize() failed");
			}
			
			tryLoadLibrary(true);
	
			try {
				nativeInit();
				initialized=true;
			} finally {
				if (!initialized) initializationFailure=true;
			}
		}
		
		if (!isThreadSafe()) {
			System.out.println("WARNING! Mapnik JNI bindings were compiled against a non-threadsafe Mapnik library.");
			System.out.println("The JVM is a threaded environment, running against a non-threadsafe Mapnik is highly");
			System.out.println("not recommended!");
		}
		
		if (register && !registered) {
			registered=true;
			String path;
			
			path=getInstalledFontsDir();
			if (path!=null) {
				FreetypeEngine.registerFonts(path);
			}
			
			path=getInstalledInputPluginsDir();
			if (path!=null) {
				DatasourceCache.registerDatasources(path);
			}
		}
	}

	/**
	 * Try to load the mapnik-jni library.
	 * - First, try to load it from the system library path.
	 * - If that fails, copy it from the classpath (provided) and load it from there.
	 * - If that fails, throw an UnsatisfiedLinkError.
	 * @param tryFromSystemPath If true, try to load from the system library path first.
	 */
	protected static void tryLoadLibrary(boolean tryFromSystemPath) {

		// First, try to load it from the system library path
		try {
			if (!tryFromSystemPath) {
				throw new UnsatisfiedLinkError("Not trying to load from system library path");
			}
			System.loadLibrary("mapnik-jni");
		} catch (UnsatisfiedLinkError e) {
			// Copy from classpath:native/*.so to a temporary file and load it from there
			var libName = System.mapLibraryName("mapnik-jni");
			var resourcePath = "/native/" + libName;
			System.out.println("Loading native library " + resourcePath + " from classpath");
			try (var is = Mapnik.class.getResourceAsStream(resourcePath)) {
				if (is == null) {
					throw new UnsatisfiedLinkError("Could not find native library " + resourcePath);
				}
				var tmpFile = Files.createTempFile("temp", libName);
				// Copy the stream to the file
				Files.copy(is, tmpFile, StandardCopyOption.REPLACE_EXISTING);
				System.load(tmpFile.toAbsolutePath().toString());
			} catch (IOException e2) {
				throw new UnsatisfiedLinkError("Could not load native library " + resourcePath + " from classpath");
			}
		}
	}

	/**
	 * Equivalent to initialize(true).  Fully links and initializes the mapnik library.
	 */
	public static void initialize() {
		initialize(true);
	}
	
	private static native void nativeInit();

	public static native String version();
	
	/**
	 * @return the Mapnik fonts dir as reported by mapnik at compile time or null
	 */
	public static native String getInstalledFontsDir();
	
	/**
	 * @return the Mapnik input plugins dir as reported by mapnik at compile time or null
	 */
	public static native String getInstalledInputPluginsDir();
	
	/**
	 * If mapnik is not compiled threadsafe, a warning will be issued on initialization.
	 * You can also query it here.
	 * @return true if mapnik was compiled threadsafe
	 */
	public static native boolean isThreadSafe();
}
