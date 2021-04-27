package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.utils.SimpleMath;
import java.util.Arrays;

/**
 * Class for operations with simple Polygons.
 *
 * @author Adam Slíva
 */
public abstract class SimplePolygon implements Polygon {

    /**
     * Constructor.
     *
     * @param arrayOfVertices Gives array of vertices from which it will create Polygon.
     */
    public SimplePolygon(Vertex2D[] arrayOfVertices) {
        if (arrayOfVertices == null) {
            throw new IllegalArgumentException("Given array pointer is a 'null' pointer.");
        } else if (arrayOfVertices.length < 1) {
            throw new IllegalArgumentException("Given array is empty.");
        } else if (Arrays.asList(arrayOfVertices).contains(null)) {
            throw new IllegalArgumentException("Given array contains 'null' pointer.");
        }
    }

    @Override
    public double getWidth() {
        return SimpleMath.maxX(this) - SimpleMath.minX(this);
    }

    @Override
    public double getHeight() {
        return SimpleMath.maxY(this) - SimpleMath.minY(this);
    }

    @Override
    public abstract Vertex2D getVertex(int index);

    @Override
    public abstract int getNumVertices();

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder().append("Polygon: vertices =");
        for (int i = 0; i < this.getNumVertices(); i++) {
            output.append(" ").append(this.getVertex(i));
        }
        return output.toString();
    }
}
