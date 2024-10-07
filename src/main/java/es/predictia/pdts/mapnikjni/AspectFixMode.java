package es.predictia.pdts.mapnikjni;

/**
 * Mirror of the aspect_fix_mode C++ enum
 *
 * @author stella
 */
public enum AspectFixMode {
    GROW_BBOX(0),
    GROW_CANVAS(1),
    SHRINK_BBOX(2),
    SHRINK_CANVAS(3),
    ADJUST_BBOX_WIDTH(4),
    ADJUST_BBOX_HEIGHT(5),
    ADJUST_CANVAS_WIDTH(6),
    ADJUST_CANVAS_HEIGHT(7),
    RESPECT(8);

    public final int nativeCode;

    AspectFixMode(int nativeCode) {
        this.nativeCode = nativeCode;
    }

    public static AspectFixMode fromNativeCode(int nativeCode) {

        for (var m : values()) {
            if (m.nativeCode == nativeCode) {
                return m;
            }
        }
        throw new IllegalArgumentException("Cannot convert code " + nativeCode + " to AspectFixMode");
    }
}
