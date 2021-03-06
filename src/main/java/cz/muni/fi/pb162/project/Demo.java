package cz.muni.fi.pb162.project;


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
        Vertex2D firstVertex = new Vertex2D();
        firstVertex.setX(2);
        firstVertex.setY(3);
        Vertex2D secondVertex = new Vertex2D();
        secondVertex.setX(1);
        secondVertex.setY(1);
        firstVertex.move(secondVertex);
        System.out.println(firstVertex.getInfo());
        System.out.println(secondVertex.getInfo());


    }
}
