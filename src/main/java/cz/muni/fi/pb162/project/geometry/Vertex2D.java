package cz.muni.fi.pb162.project.geometry;

/**
 * Class for working with vectors.
 *
 * @author Adam Slíva
 */
public class Vertex2D {
    private double myX = 0.0;
    private double myY = 0.0;

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

    public String getInfo() {
        return "[" + myX + ", " + myY + "]";
    }

    /**
     * Summing coordinates of given vector.
     *
     * @return double Summarized X and Y.
     */
    public double sumCoordinates() {
        return myX + myY;
    }

    /**
     * Moving coordinates by given vector.
     *
     * @param vertex Moving by this vertex.
     */
    public void move(Vertex2D vertex) {
        myX += vertex.getX();
        myY += vertex.getY();
    }


}


