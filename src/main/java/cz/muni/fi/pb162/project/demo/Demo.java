package cz.muni.fi.pb162.project.demo;


import cz.muni.fi.pb162.project.geometry.LabeledPolygon;
import cz.muni.fi.pb162.project.geometry.Vertex2D;

import java.io.File;
import java.io.IOException;

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
     *
     * @throws IOException When fails.
     */
    public static void main(String[] args) throws IOException {
        LabeledPolygon newPolygon = new LabeledPolygon.Builder().read(new File("polygon-ok.txt"))
                .addVertex("vrchol x", new Vertex2D(123, 456)).build();
        newPolygon.writeJson(System.out);
        System.out.println("Hello World!");
    }
}
