package es.predictia.pdts.mapnikjni;


/**
 * Wraps the mapnik::Layer value type.  When a Layer is read from or
 * written to the map, it is copied.
 * 
 * @author stella
 *
 */
public class Layer extends NativeObject {
	private static native long alloc(String name, String srs);
	native void dealloc(long ptr);

	public Layer(String name, String srs) {
		ptr=alloc(name, srs);
	}
	public Layer(String name) {
		this(name, Projection.LATLNG_PARAMS);
	}
	
	public native String getName();
	public native void setName(String name);
	public native String getSrs();
	public native void setSrs(String srs);
	
	public native String[] getStyles();
	public native void setStyles(String[] styles);
	
	public native double getMinZoom();
	public native void setMinZoom(double z);
	
	public native double getMaxZoom();
	public native void setMaxZoom(double z);
	
	public native boolean isActive();
	public native void setActive(boolean active);
	
	public native boolean isQueryable();
	public native void setQueryable(boolean b);
	
	public native boolean isVisible(double scale);
	
	public native boolean isClearLabelCache();
	public native void setClearLabelCache(boolean b);
	
	public native boolean isCacheFeatures();
	public native void setCacheFeatures(boolean b);
	
	// -- datasource
	public native Datasource getDatasource();
	public native void setDatasource(Datasource datasource);
	
	// -- envelope
}
