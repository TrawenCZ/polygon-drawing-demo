package cz.muni.fi.pb162.project.demo;


import cz.muni.fi.pb162.project.geometry.Square;
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
        Square square = new Square(new Vertex2D(0,0), 100);
        System.out.println(square.toString());
    }
}
