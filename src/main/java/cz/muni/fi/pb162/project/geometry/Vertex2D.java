package cz.muni.fi.pb162.project.geometry;

/**
 * Class for working with vectors.
 *
 * @author Adam Slíva
 */
public class Vertex2D {
    private double myX = 0.0;
    private double myY = 0.0;

    /**
     * Constructing Vertex.
     *
     * @param newX Gives X.
     * @param newY Gives Y.
     */
    public Vertex2D(double newX, double newY) {
        myX = newX;
        myY = newY;
    }
    public double getX() {
        return myX;
    }

    public double getY() {
        return myY;
    }

    public void setX(double newX) {
        myX = newX;
    }

    public void setY(double newY) {
        myY = newY;
    }

    @Override
    public String toString() {
        return "[" + myX + ", " + myY + "]";
    }

    /**
     * Method creates middle Vertex between two given Vertices.
     *
     * @param vertex Gives second Vertex of which it will creates middle one.
     *
     * @return New middle Vertex.
     */
    public Vertex2D createMiddle(Vertex2D vertex) {
        return new Vertex2D((myX + vertex.getX()) / 2, (myY + vertex.getY()) / 2);
    }


}


