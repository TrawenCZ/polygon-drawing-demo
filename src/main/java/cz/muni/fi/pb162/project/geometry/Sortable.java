package cz.muni.fi.pb162.project.geometry;

import java.util.Collection;
import java.util.Comparator;

/**
 * Interface representing vertices sorting.
 *
 * @author Marek Sabo
 */
public interface Sortable {

    /**
     * Method returns the vertices of this polygon in ascending order
     * defined by he natural ordering of {@link Vertex2D} class.
     *
     * @return sorted vertices
     */
    Collection<Vertex2D> getSortedVertices();

    /**
     * Method returns the vertices of this polygon in ascending order
     * defined by given comparator.
     *
     * @param comparator comparator object used to determine the ordering
     * @return sorted vertices
     */
    Collection<Vertex2D> getSortedVertices(Comparator<Vertex2D> comparator);
}
