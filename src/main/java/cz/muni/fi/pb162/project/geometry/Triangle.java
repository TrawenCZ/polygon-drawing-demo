package cz.muni.fi.pb162.project.geometry;
/**
 * Class for creating beautiful Triangles.
 *
 * @author Adam Slíva
 */
public class Triangle {
    private final Vertex2D[] array = new Vertex2D[3];
    private final Triangle[] midArray = new Triangle[3];
    private final double tolerance = 0.001;

    /**
     * Method for constructing Triangles from Vertices.
     *
     * @param vertex1 point n.1
     * @param vertex2 point n.2
     * @param vertex3 point n.3
     */
    public Triangle(Vertex2D vertex1, Vertex2D vertex2, Vertex2D vertex3) {
        array[0] = vertex1;
        array[1] = vertex2;
        array[2] = vertex3;
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
        array[0] = vertex1;
        array[1] = vertex2;
        array[2] = vertex3;
        divide(depth);
    }

    /**
     * Returns Vertex by his index in array of Vertices.
     *
     * @param index Gives index of Vertex in array of three Vertices.
     *
     * @return Vertex by his index in array of Vertices.
     */
    public Vertex2D getVertex(int index) {
        if (index < 0 || index > 2) {
            return null;
        }

        return array[index];
    }

    /**
     * Replaces Vertex at the given index in array.
     *
     * @param index Gives index of Vertex which is going to be replaced in array.
     * @param vertex Gives Vertex which is going to replace the old one.
     */
    public void setVertex(int index, Vertex2D vertex) {
        if (index >= 0 && index <= 2) {
            array[index] = vertex;
        }
    }

    /**
     * Method for converting Vertexes into readable string.
     *
     * @return String.
     */
    public String toString() {
        return "Triangle: vertices=" + array[0].toString() + " " + array[1].toString() + " " +  array[2].toString();
    }

    /**
     * Dividing triangle into three smaller triangles.
     *
     * @return Bool based on if triangle has or hasn't already been made.
     */
    public boolean divide() {
        if (!isDivided()) {
            Triangle triangle1 = new Triangle(array[0],
                    array[0].createMiddle(array[1]), array[0].createMiddle(array[2]));
            midArray[0] = triangle1;

            Triangle triangle2 = new Triangle(array[0].createMiddle(array[1]), array[1],
                    array[1].createMiddle(array[2]));
            midArray[1] = triangle2;

            Triangle triangle3 = new Triangle(array[0].createMiddle(array[2]),
                    array[1].createMiddle(array[2]), array[2]);
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

    boolean isEquilateral() {
        return (Math.abs(array[0].distance(array[1]) - array[1].distance(array[2])) < tolerance) &&
                (Math.abs(array[0].distance(array[2]) - array[0].distance(array[1])) < tolerance);
    }

    void divide(int depth) {
        if (depth != 0) {
            divide();
            for (int i = 0; i < 3 ; i++) {
                midArray[i].divide(depth -1);
            }
        }
    }
}
