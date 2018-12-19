package cz.muni.fi.pb162.project.geometry;


/**
 * Interface for general polygons including irregular polygons.
 *
 * @author Radek Oslejsek, Marek Sabo
 */
public interface Polygon extends Measurable {

    /**
     * Returns vertex at given index modulo number of indices.
     *
     * @param index vertex index, not negative number
     * @return vertex at given index modulo number of indices
     * @throws IllegalArgumentException if index is negative
     */
    Vertex2D getVertex(int index);

    /**
     * Returns number of vertices of the polygon.
     *
     * @return number of vertices
     */
    int getNumVertices();

}
