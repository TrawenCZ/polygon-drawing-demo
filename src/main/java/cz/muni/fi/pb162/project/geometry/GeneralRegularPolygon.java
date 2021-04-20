package cz.muni.fi.pb162.project.geometry;

/**
 * Class for creating regular polygons.
 *
 * @author Adam Slíva
 */
public class GeneralRegularPolygon implements RegularPolygon, Colored {
    private final Vertex2D center;
    private final int edges;
    private final double radius;
    private Color color = Color.BLACK;

    /**
     * Constructor for Regular Polygon.
     *
     * @param center Gives center of Polygon.
     * @param edges Gives number of edges of Polygon.
     * @param radius Gives radius of Polygon.
     */
    public GeneralRegularPolygon(Vertex2D center, int edges, double radius){
        this.center = center;
        this.edges = edges;
        this.radius = radius;
    }

    /**
     * Returns data about Polygon in String.
     *
     * @return String
     */
    public String toString() {
        return this.edges + "-gon: center=" + this.center +
                ", radius=" + this.radius + ", color=" + this.color;
    }

    @Override
    public Vertex2D getCenter() {
        return this.center;
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public double getWidth() {
        return 2 * this.radius;
    }

    @Override
    public double getHeight() {
        return getWidth();
    }

    @Override
    public int getNumEdges() {
        return this.edges;
    }

    @Override
    public double getEdgeLength() {
        return 2 * this.radius * Math.sin(Math.PI / this.edges);
    }

    @Override
    public Vertex2D getVertex(int index) {
        return new Vertex2D(this.center.getX() - this.radius * Math.cos(index * 2 * Math.PI / edges),
                            this.center.getY() - this.radius * Math.sin(index * 2 * Math.PI / edges));
    }
}
