package cz.muni.fi.pb162.project.geometry;

/**
 * Class for working with vectors.
 *
 * @author Adam Slíva
 */
public class Vertex2D {
    private final double myX;
    private final double myY;

    /**
     * Constructing Vertex.
     *
     * @param newX Gives X.
     * @param newY Gives Y.
     */
    public Vertex2D(double newX, double newY) {
        this.myX = newX;
        this.myY = newY;
    }
    public double getX() {
        return myX;
    }

    public double getY() {
        return myY;
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

    /**
     * Method calculates distance between two points.
     *
     * @param vertex Gives second Vertex to which it will calculate distance.
     *
     * @return Distance.
     */
    public double distance(Vertex2D vertex) {
        if (vertex == null) {
            return -1.0;
        }
        return Math.sqrt(Math.pow(this.myX - vertex.getX(), 2) + Math.pow(this.myY - vertex.getY(), 2));
    }


}


