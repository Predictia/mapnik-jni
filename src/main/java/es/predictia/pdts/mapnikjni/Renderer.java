package es.predictia.pdts.mapnikjni;

/**
 * Methods that interface with the rendering subsystem.  We don't mirror these classes directly
 * as they are operation oriented C++ templates.
 *
 * @author stella
 */

public class Renderer {

    private Renderer() {
        // Utility class
    }

    /**
     * Render a map to an image object
     *
     * @param map         The map object to render
     * @param image       The image to render to
     * @param scaleFactor The scale factor to render at
     *                    (1.0 is normal, 2.0 is twice as big, 0.5 is half as big)
     * @param offsetX     The x offset to render at
     * @param offsetY     The y offset to render at
     */
    public static native void renderAgg(MapDefinition map, Image image, double scaleFactor, int offsetX, int offsetY);

    /**
     * Render map in a standard image (1.0 scale, 0 offset)
     *
     * @param map   The map object to render
     * @param image The image to render to
     */
    public static void renderAgg(MapDefinition map, Image image) {
        renderAgg(map, image, 1.0, 0, 0);
    }
}
