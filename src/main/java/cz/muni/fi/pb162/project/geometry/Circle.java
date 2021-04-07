package cz.muni.fi.pb162.project.geometry;
/**
 * Class for working with Circles.
 *
 * @author Adam Slíva
 */
public class Circle extends GeneralRegularPolygon {
    private static final int MAX_INT = Integer.MAX_VALUE;
    /**
     * Constructing Circle.
     *
     * @param center Gives center of circle in Vertex type.
     * @param radius Gives radius of circle.
     */
    public Circle(Vertex2D center, double radius) {
        super(center, MAX_INT, radius);
        this.setColor(Color.RED);
    }

    /**
     * Constructing Circle with no parameters.
     * Setting center by default to '[0.0, 0.0]' and radius to '1.0'.
     *
     */
    public Circle() {
        this(new Vertex2D(0.0,0.0), 1.0);
    }


    @Override
    public String toString(){
        return "Circle: center=" + this.getCenter() + ", radius=" + this.getRadius();
    }

    @Override
    public double getEdgeLength() {
        return 0.0;
    }
}
