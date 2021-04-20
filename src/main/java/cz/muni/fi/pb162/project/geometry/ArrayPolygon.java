package cz.muni.fi.pb162.project.geometry;

import java.util.Arrays;

/**
 * Class for working with Polygons.
 *
 * @author Adam Slíva
 */
public class ArrayPolygon extends SimplePolygon {
    private final Vertex2D[] verticesArray;

    /**
     * Constructor for Polygon represented by array of vertices.
     *
     * @param verticesArray Array of vertices
     */
    public ArrayPolygon(Vertex2D[] verticesArray) {
        if (verticesArray == null || verticesArray.length < 3 || Arrays.asList(verticesArray).contains(null)) {
            throw new IllegalArgumentException("Too few vertices or given a null pointer or null value in given array");
        }
        this.verticesArray = Arrays.copyOf(verticesArray, verticesArray.length);
    }

    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Input index must be 0 or greater!");
        }
        return verticesArray[index % verticesArray.length];
    }

    @Override
    public int getNumVertices() {
        return verticesArray.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayPolygon that = (ArrayPolygon) o;
        return Arrays.equals(verticesArray, that.verticesArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(verticesArray);
    }
}
