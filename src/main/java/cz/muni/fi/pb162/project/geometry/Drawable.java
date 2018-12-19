package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.EmptyDrawableException;
import cz.muni.fi.pb162.project.exception.TransparentColorException;

import java.util.Collection;

/**
 * This interface represents objects which can be drawn onto.
 * The objects are drawn with a magic pencil, which color can be changed.
 * The default color is black.
 *
 * @author Marek Sabo
 */
public interface Drawable {

    /**
     * Changes the pencil color.
     *
     * @param color the color which the following drawn objects are going to have
     */
    void changeColor(Color color);

    /**
     * Draws the polygon on the drawable.
     * If the current color is white, the polygon is not drawn.
     *
     * @param polygon polygon to be drawn
     * @throws TransparentColorException when polygon should be drawn with a white color
     */
    void drawPolygon(Polygon polygon) throws TransparentColorException;

    /**
     * Erases the polygon.
     *
     * @param polygon polygon to be erased
     */
    void erasePolygon(ColoredPolygon polygon);

    /**
     * Erases the whole paper. The pencil color is not changed.
     *
     * @throws EmptyDrawableException when drawable is already empty
     */
    void eraseAll() throws EmptyDrawableException;

    /**
     * Returns collection of all drawn polygons which are not erased.
     *
     * @return unmodifiable collection of drawn polygons
     */
    Collection<ColoredPolygon> getAllDrawnPolygons();

    /**
     * Calculates the number of distinct vertices on the drawable.
     *
     * @return number of distinct vertices
     */
    int uniqueVerticesAmount();


}
