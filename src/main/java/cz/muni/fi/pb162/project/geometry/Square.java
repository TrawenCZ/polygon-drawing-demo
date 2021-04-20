package cz.muni.fi.pb162.project.geometry;

/**
 * Class for creating beautiful Squares.
 *
 * @author Adam Slíva
 */
public class Square extends GeneralRegularPolygon {

    /**
     * Constructor
     *
     * @param center Gives center of Square.
     * @param diameter Gives diameter from one corner to his opposite partner.
     */
    public Square(Vertex2D center, double diameter) {
        super(center, 4, diameter/2);
    }

    /**
     * Constructor.
     *
     * @param object Gives object from which it will create Square.
     */
    public Square(Circular object) {
        this(object.getCenter(), 2 * object.getRadius());
    }

    /**
     * Data about Square in String format.
     *
     * @return String.
     */
    public String toString() {
        StringBuilder output = new StringBuilder().append("Square: vertices=");
        for (int i = 0; i < 4; i++) {
            output.append(getVertex(i).toString()).append(" ");
        }
        return output.toString().trim();
    }
}
