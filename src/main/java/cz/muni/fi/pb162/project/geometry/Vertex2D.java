package cz.muni.fi.pb162.project.geometry;

import java.util.Objects;

/**
 * Class for working with vectors.
 *
 * @author Adam Slíva
 */
public class Vertex2D implements Comparable<Vertex2D> {
    private final double x;
    private final double y;

    /**
     * Constructing Vertex.
     *
     * @param newX Gives X.
     * @param newY Gives Y.
     */
    public Vertex2D(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    /**
     * Method creates middle Vertex between two given Vertices.
     *
     * @param vertex Gives second Vertex of which it will creates middle one.
     *
     * @return New middle Vertex.
     */
    public Vertex2D createMiddle(Vertex2D vertex) {
        return new Vertex2D((x + vertex.getX()) / 2, (y + vertex.getY()) / 2);
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
        return Math.sqrt(Math.pow(this.x - vertex.getX(), 2) + Math.pow(this.y - vertex.getY(), 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex2D vertex2D = (Vertex2D) o;
        return Double.compare(vertex2D.x, x) == 0 && Double.compare(vertex2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Method for comparing vertexes by X and Y coordinates.
     *
     * @param another Another vertex to be compared to the one from the class.
     *
     * @return Integer from which other methods determine output.
     */
    public int compareTo(Vertex2D another) {
        int output = Double.compare(this.getX(), another.getX());
        if (output == 0) {
            return Double.compare(this.getY(), another.getY());
        }
        return output;
    }
}


