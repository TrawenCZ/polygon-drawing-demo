package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Polygon;
/**
 * Class calculating minimums and maximums from Vertices of Polygons.
 *
 * @author Adam Slíva
 */
public class SimpleMath {
    /**
     * Method for calculating minimum X coordinate.
     *
     * @param polygon Gives Polygon from which it will take coordinate.
     *
     * @return Minimum X.
     */
    public static double minX(Polygon polygon) {
        double minimum = polygon.getVertex(0).getX();
        for (int i = 1; i < polygon.getNumVertices(); i++) {
            double val = polygon.getVertex(i).getX();
            if (val < minimum) {
                minimum = val;
            }
        }
        return minimum;
    }

    /**
     * Method for calculating minimum Y coordinate.
     *
     * @param polygon Gives Polygon from which it will take coordinate.
     *
     * @return Minimum Y
     */
    public static double minY(Polygon polygon) {
        double minimum = polygon.getVertex(0).getY();
        for (int i = 1; i < polygon.getNumVertices(); i++) {
            double val = polygon.getVertex(i).getY();
            if (val < minimum) {
                minimum = val;
            }
        }
        return minimum;
    }

    /**
     * Method for calculating maximum X coordinate.
     *
     * @param polygon Gives Polygon from which it will take coordinate.
     *
     * @return Maximum X
     */
    public static double maxX(Polygon polygon) {
        double maximum = polygon.getVertex(0).getX();
        for (int i = 1; i < polygon.getNumVertices(); i++) {
            double val = polygon.getVertex(i).getX();
            if (val > maximum) {
                maximum = val;
            }
        }
        return maximum;
    }

    /**
     * Method for calculating minimum Y coordinate.
     *
     * @param polygon Gives Polygon from which it will take coordinate.
     *
     * @return Maximum Y
     */
    public static double maxY(Polygon polygon) {
        double maximum = polygon.getVertex(0).getY();
        for (int i = 1; i < polygon.getNumVertices(); i++) {
            double val = polygon.getVertex(i).getY();
            if (val > maximum) {
                maximum = val;
            }
        }
        return maximum;
    }
}


