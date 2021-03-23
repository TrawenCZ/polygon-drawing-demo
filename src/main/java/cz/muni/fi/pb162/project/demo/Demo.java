package cz.muni.fi.pb162.project.demo;


import cz.muni.fi.pb162.project.geometry.Triangle;
import cz.muni.fi.pb162.project.geometry.Vertex2D;

/**
 * Class for running main method.
 *
 * @author Adam Slíva
 */
public class Demo {

    /**
     * Runs the code.
     *
     * @param args command line arguments, will be ignored
     */
    public static void main(String[] args) {
        Triangle newTriangle = new Triangle(new Vertex2D(-100.0, 0.0),
                new Vertex2D(0.0, 100.0), new Vertex2D(100.0, -100.0));
        System.out.println(newTriangle.toString());

    }
}
