package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.utils.SimpleMath;

/**
 * Class for operations with simple Polygons.
 *
 * @author Adam Slíva
 */
public abstract class SimplePolygon implements Polygon {


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
