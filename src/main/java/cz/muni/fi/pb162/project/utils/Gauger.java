package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Measurable;
import cz.muni.fi.pb162.project.geometry.Triangle;

/**
 * Class measuring Width and Height of Objects.
 *
 * @author Adam Slíva
 */
public class Gauger {
    /**
     * Method for printing Width and Height of Object
     *
     * @param object Gives Object to be measured.
     */
    public static void printMeasurement(Measurable object) {
        System.out.println("Width: " + object.getWidth());
        System.out.println("Height: " + object.getHeight());
    }

    /**
     * Same as above but adding one line of points of given Triangle
     *
     * @param triangle Gives Triangle to be measured.
     */
    public static void printMeasurement(Triangle triangle) {
        System.out.println(triangle.toString());
        printMeasurement((Measurable) triangle);
    }
}
