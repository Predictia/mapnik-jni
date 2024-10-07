package es.predictia.pdts.mapnikjni;

import java.util.Collection;

/**
 * Wrapper around the mapnik::Map object.
 * <p>
 * Several methods that are found elsewhere in the C++ code but operate exlusively
 * on maps are located directly on this class:
 * <ul>
 * <li>loadMap
 * <li>loadMapString
 * <li>saveMap
 * <li>saveMapToString
 * </ul>
 * <p>
 * Not all child objects are implemented.
 * <p>
 * Note that the following child objects are value types.  When they are added to or
 * read from a MapDefinition, the caller will be left with a copy.
 * <ul>
 * <li>Layer
 * <li>FeatureTypeStyle
 * </ul>
 * 
 * @author stella
 *
 */
public class MapDefinition extends NativeObject {
	private static native long alloc(int width, int height, String srs);
	private static native long alloc();
	private static native long copy(long ptr);
	
	@Override
	native void dealloc(long ptr);
	
	public MapDefinition() {
		ptr=alloc();
	}
	
	public MapDefinition(int width, int height, String srs) {
		ptr=alloc(width, height, srs);
	}
	
	public MapDefinition(MapDefinition other) {
		ptr=copy(other.ptr);
	}
	
	public native void loadMap(String filename, boolean strict);
	public native void loadMapString(String str, boolean strict, String basePath);
	
	public native int getWidth();
	public native int getHeight();
	public native void setWidth(int width);
	public native void setHeight(int height);
	public native void resize(int widht, int height);
	public native String getSrs();
	public native void setSrs(String srs);
	
	public native void setBufferSize(int bufferSize);
	public native int getBufferSize();
	
	public native String getBasePath();
	public native void setBasePath(String basePath);
	
	// Layers
	public native int getLayerCount();
	public native Layer getLayer(int index);
	public native void setLayer(int index, Layer layer);
	public native void removeLayer(int index);
	public native void removeAllLayers();
	public native void addLayer(Layer layer);
	
	// Aspect fix mode
	public void setAspectFixMode(AspectFixMode m) {
		_setAspectFixMode(m.nativeCode);
	}
	public AspectFixMode getAspectFixMode() {
		return AspectFixMode.fromNativeCode(_getAspectFixMode());
	}
	private native void _setAspectFixMode(int v);
	private native int _getAspectFixMode();
	
	
	// Styles
	public native Collection<String> getStyleNames();
	public native FeatureTypeStyle getStyle(String name);
	public native void addStyle(String name, FeatureTypeStyle style);
	public native void removeStyle(String name);
	
	// Extents
	public native Box2d getMaximumExtent();
	public native void setMaximumExtent(Box2d extent);
	public native Box2d getCurrentExtent();
	public native Box2d getBufferedExtent();
	
	// Movement
	public native void zoom(double factor);
	public native void zoomToBox(Box2d box);
	public native void zoomAll();
	public native void pan(int x, int y);
	public native void panAndZoom(int x, int y, double zoom);
	
	public native double getScale();
	public native double getScaleDenominator();
	
	// Background
	public native Color getBackground();
	public native void setBackground(Color color);
	public native String getBackgroundImage();
	public native void setBackgroundImage(String filename);
	
	// Save map
	public native void saveMap(String fileName, boolean explicitDefaults);
	public native String saveMapToString(boolean explicitDefaults);
	public void saveMap(String fileName) {
		saveMap(fileName, false);
	}
	public String saveMapToString() {
		return saveMapToString(false);
	}
}
