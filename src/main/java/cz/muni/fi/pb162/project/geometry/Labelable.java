package cz.muni.fi.pb162.project.geometry;

import java.util.Collection;

/**
 * Interface representing basic methods for labeling vertices.
 *
 * @author Marek Sabo
 */
public interface Labelable {

    /**
     * Get vertex stored under given label in a polygon.
     * If label does not exists, IllegalArgumentException is thrown.
     *
     * @param label label under which the vertex is stored
     * @return vertex with given label
     */
    Vertex2D getVertex(String label);

    /**
     * Method returns the labels of vertices in a polygon.
     * The labels are sorted in the ascending order lexicographically.
     *
     * @return collection of labels in ascending order
     */
    Collection<String> getLabels();

    /**
     * Finds all labels corresponding to given vertex.
     *
     * @param vertex vertex which labels are searched
     * @return collection of corresponding labels
     */
    Collection<String> getLabels(Vertex2D vertex);
}
