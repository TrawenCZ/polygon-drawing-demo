package cz.muni.fi.pb162.project.geometry;

/**
 * Class for creating beautiful Squares.
 *
 * @author Adam Slíva
 */
public class Square implements Circular {
    private final Vertex2D center;
    private final double diameter;

    /**
     * Constructor
     *
     * @param center Gives center of Square.
     * @param diameter Gives diameter from one corner to his opposite partner.
     */
    public Square(Vertex2D center, double diameter) {
        this.center = center;
        this.diameter = diameter;
    }

    /**
     * Constructor.
     *
     * @param object Gives object from which it will create Square.
     */
    public Square(Circular object) {
        this(object.getCenter(), 2 * object.getRadius());
    }

    @Override
    public Vertex2D getCenter() {
        return this.center;
    }

    @Override
    public double getRadius() {
        return this.diameter / 2;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder().append("Square: vertices=");
        for (int i = 0; i < 4; i++) {
            output.append(this.getVertex(i).toString()).append(" ");
        }
        return output.substring(0, output.length()-1);
    }
    /**
     * Method for calculating corner Vertices.
     *
     * @param index Gives index of corner.
     *
     * @return Given corner's coordinates.
     */
    public Vertex2D getVertex(int index) {
        switch (index) {
            case 0:
                return new Vertex2D(this.center.getX() - diameter/2, this.center.getY());
            case 1:
                return new Vertex2D(this.center.getX(), this.center.getY() - diameter/2);
            case 2:
                return new Vertex2D(this.center.getX() + diameter/2, this.center.getY());
            case 3:
                return new Vertex2D(this.center.getX(), this.center.getY() + diameter/2);
            default:
                return null;
        }
    }
}
