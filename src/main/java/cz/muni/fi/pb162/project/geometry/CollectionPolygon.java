package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.utils.SimpleMath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class for operations with Polygons represented by ArrayList.
 *
 * @author Adam Slíva
 */
public class CollectionPolygon extends SimplePolygon {
    private final ArrayList<Vertex2D> verticesArrayList;

    /**
     * Contructor.
     *
     * @param arrayOfVertices Gives array of vertices to be converted.
     */
    public CollectionPolygon(Vertex2D[] arrayOfVertices) {
        super(arrayOfVertices);
        this.verticesArrayList = new ArrayList<>(Arrays.asList(arrayOfVertices));
    }

    /**
     * Constructor
     *
     * @param verticesList Gives List of vertices to be converted.
     */
    public CollectionPolygon(List<Vertex2D> verticesList) {
        this(verticesList.toArray(new Vertex2D[0]));
    }

    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Given invalid index.");
        }
        return this.verticesArrayList.get(index % verticesArrayList.size());
    }

    @Override
    public int getNumVertices() {
        return verticesArrayList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollectionPolygon that = (CollectionPolygon) o;
        return Objects.equals(verticesArrayList, that.verticesArrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(verticesArrayList);
    }

    /**
     * Method for creating polygon out of original polygon without its Left-most vertices.
     *
     * @return New polygon.
     */
    public CollectionPolygon withoutLeftmostVertices() {
        double minimumX = SimpleMath.minX(new ArrayPolygon(verticesArrayList.toArray(new Vertex2D[0])));
        ArrayList<Vertex2D> newPolygonArray = new ArrayList<>();
        for (Vertex2D vertex : this.verticesArrayList) {
            if (vertex.getX() == minimumX) {
                continue;
            }
            newPolygonArray.add(vertex);
        }
        if (newPolygonArray.size() == 0) {
            return null;
        }
        return new CollectionPolygon(newPolygonArray.toArray(new Vertex2D[0]));
    }
}
