package cz.muni.fi.pb162.project.geometry;

/**
 * Class for creating beautiful Triangles.
 *
 * @author Adam Slíva
 */
public class Triangle extends ArrayPolygon {
    private final Triangle[] midArray = new Triangle[3];
    private static final double TOLERANCE = 0.001;

    /**
     * Constructor.
     *
     * @param vertex1 point n.1
     * @param vertex2 point n.2
     * @param vertex3 point n.3
     */
    public Triangle(Vertex2D vertex1, Vertex2D vertex2, Vertex2D vertex3) {
        super(new Vertex2D[] {vertex1, vertex2, vertex3});
    }
    /**
     * Method for constructing Sierpiński Triangles from Vertices and depth.
     *
     * @param vertex1 point n.1
     * @param vertex2 point n.2
     * @param vertex3 point n.3
     * @param depth Number of divisions of Triangle.
     */
    public Triangle(Vertex2D vertex1, Vertex2D vertex2, Vertex2D vertex3, int depth) {
        this(vertex1, vertex2, vertex3);
        divide(depth);
    }

    /**
     * Method for converting Vertexes into readable string.
     *
     * @return String.
     */
    public String toString() {
        return "Triangle: vertices=" + getVertex(0).toString() + " " + getVertex(1).toString() + " " +
                getVertex(2).toString();
    }

    /**
     * Dividing triangle into three smaller triangles.
     *
     * @return Bool based on if triangle has or hasn't already been made.
     */
    public boolean divide() {
        if (!isDivided()) {
            Triangle triangle1 = new Triangle(getVertex(0),
                    getVertex(0).createMiddle(getVertex(1)), getVertex(0).createMiddle(getVertex(2)));
            midArray[0] = triangle1;

            Triangle triangle2 = new Triangle(getVertex(0).createMiddle(getVertex(1)), getVertex(1),
                    getVertex(1).createMiddle(getVertex(2)));
            midArray[1] = triangle2;

            Triangle triangle3 = new Triangle(getVertex(0).createMiddle(getVertex(2)),
                    getVertex(1).createMiddle(getVertex(2)), getVertex(2));
            midArray[2] = triangle3;
            return true;
        }
        return false;
    }

    /**
     * Checking if this triangle has been already divided.
     *
     * @return True if has been, False if hasn't.
     */
    public boolean isDivided() {
        return midArray[0] != null;
    }

    /**
     * Returns chosen Triangle by given index in array of Triangles.
     *
     * @param index Gives index in array of Triangles.
     *
     * @return chosen Triangle by given index in array of Triangles
     */
    public Triangle getSubTriangle(int index) {
        if (index < 0 || index > 2 || midArray[0] == null) {
            return null;
        }
        return midArray[index];
    }

    boolean areDoublesSimilar(double input1, double input2) {
        return Math.abs(input1 - input2) < TOLERANCE;
    }

    boolean isEquilateral() {
        return areDoublesSimilar(getVertex(0).distance(getVertex(1)), getVertex(1).distance(getVertex(2))) &&
                areDoublesSimilar(getVertex(0).distance(getVertex(2)), getVertex(0).distance(getVertex(1)));
    }

    void divide(int depth) {
        if (depth == 0) {
            return;
        }
        divide();
        for (int i = 0; i < 3 ; i++) {
            midArray[i].divide(depth - 1);
        }
    }
}
