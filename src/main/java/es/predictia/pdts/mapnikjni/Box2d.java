package es.predictia.pdts.mapnikjni;

import java.text.DecimalFormat;

/**
 * Wrapper for the native mapnik::box2d object.
 *
 * @author stella
 */
public class Box2d {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.000000");

    public double minx;
    public double miny;
    public double maxx;
    public double maxy;

    public Box2d() {
    }

    public Box2d(double minx, double miny, double maxx, double maxy) {
        this.minx = minx;
        this.miny = miny;
        this.maxx = maxx;
        this.maxy = maxy;
    }

    public Box2d(Box2d other) {
        this.minx = other.minx;
        this.miny = other.miny;
        this.maxx = other.maxx;
        this.maxy = other.maxy;
    }

    @Override
    public String toString() {
        return "Box2d(" +
                DECIMAL_FORMAT.format(minx) + "," +
                DECIMAL_FORMAT.format(miny) + "," +
                DECIMAL_FORMAT.format(maxx) + "," +
                DECIMAL_FORMAT.format(maxy) +
                ")";
    }
}
