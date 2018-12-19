package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.EmptyDrawableException;
import cz.muni.fi.pb162.project.exception.MissingVerticesException;

import java.util.List;

/**
 * Interface for methods which create and draw polygons.
 *
 * @author Marek Sabo
 */
public interface PolygonFactory {

    /**
     * Creates polygon from the collection.
     *
     * @param vertices collection which the polygon can be built from; the collection is not modified
     * @return created polygon
     * @throws MissingVerticesException if there is not enough not null vertices
     */
    Polygon tryToCreatePolygon(List<Vertex2D> vertices) throws MissingVerticesException;

    /***
     * Draws the polygons, ignores all null vertices.
     *
     * @param collectionPolygons collection of polygons (every polygon is collection of vertices)
     * @throws EmptyDrawableException if nothing has been drawn
     */
    void tryToDrawPolygons(List<List<Vertex2D>> collectionPolygons) throws EmptyDrawableException;

}
