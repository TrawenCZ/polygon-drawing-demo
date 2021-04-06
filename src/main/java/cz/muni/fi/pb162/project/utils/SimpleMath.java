package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Triangle;
/**
 * Class calculating minimums and maximums from Vertices of Triangle.
 *
 * @author Adam Slíva
 */
public class SimpleMath {
    /**
     * Method for calculating minimum X coordinate.
     *
     * @param triangle Gives Triangle from which it will take coordinate.
     *
     * @return Minimum X.
     */
    public static double minX(Triangle triangle) {
        double minimum = triangle.getVertex(0).getX();
        for (int i = 1; i < 3; i++) {
            double val = triangle.getVertex(i).getX();
            if (val < minimum) {
                minimum = val;
            }
        }
        return minimum;
    }

    /**
     * Method for calculating minimum Y coordinate.
     *
     * @param triangle Gives Triangle from which it will take coordinate.
     *
     * @return Minimum Y
     */
    public static double minY(Triangle triangle) {
        double minimum = triangle.getVertex(0).getY();
        for (int i = 1; i < 3; i++) {
            double val = triangle.getVertex(i).getY();
            if (val < minimum) {
                minimum = val;
            }
        }
        return minimum;
    }

    /**
     * Method for calculating maximum X coordinate.
     *
     * @param triangle Gives Triangle from which it will take coordinate.
     *
     * @return Maximum X
     */
    public static double maxX(Triangle triangle) {
        double maximum = triangle.getVertex(0).getX();
        for (int i = 1; i < 3; i++) {
            double val = triangle.getVertex(i).getX();
            if (val > maximum) {
                maximum = val;
            }
        }
        return maximum;
    }

    /**
     * Method for calculating minimum Y coordinate.
     *
     * @param triangle Gives Triangle from which it will take coordinate.
     *
     * @return Maximum Y
     */
    public static double maxY(Triangle triangle) {
        double maximum = triangle.getVertex(0).getY();
        for (int i = 1; i < 3; i++) {
            double val = triangle.getVertex(i).getY();
            if (val > maximum) {
                maximum = val;
            }
        }
        return maximum;
    }
}


