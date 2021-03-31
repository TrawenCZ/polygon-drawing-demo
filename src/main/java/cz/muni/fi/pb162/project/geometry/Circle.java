package cz.muni.fi.pb162.project.geometry;
/**
 * Class for working with Circles.
 *
 * @author Adam Slíva
 */
public class Circle implements Circular, Measurable {
    private final Vertex2D center;
    private final double radius;

    /**
     * Constructing Circle.
     *
     * @param center Gives center of circle in Vertex type.
     * @param radius Gives radius of circle.
     */
    public Circle(Vertex2D center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    /**
     * Constructing Circle with no parameters.
     * Setting center by default to '[0.0, 0.0]' and radius to '1.0'.
     *
     */
    public Circle() {
        this(new Vertex2D(0.0,0.0), 1.0);
    }

    public Vertex2D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString(){
        return "Circle: center=" + center.toString() + ", radius=" + this.radius;
    }

    @Override
    public double getWidth() {
        return radius*2;
    }

    @Override
    public double getHeight() {
        return getWidth();
    }
}
