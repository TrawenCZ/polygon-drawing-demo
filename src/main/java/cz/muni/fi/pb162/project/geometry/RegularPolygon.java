package cz.muni.fi.pb162.project.geometry;

/**
 * Interface for convex regular polygons, e.g. equilateral triangle, square, regular octagon.
 *
 * @author Radek Oslejsek, Marek Sabo
 */
public interface RegularPolygon extends Measurable, Circular {

    /**
     * Returns number of edges of the regular polygon.
     *
     * @return number of edges
     */
    int getNumEdges();

    /**
     * Returns length of a single edge.
     *
     * @return edge length
     */
    double getEdgeLength();

    /**
     * Returns vertex at a given index modulo number of indices,
     * in a counter-clockwise order.
     *
     * @param index integral number of index
     * @return vertex at given index
     */
    Vertex2D getVertex(int index);
}
