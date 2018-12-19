package cz.muni.fi.pb162.project.geometry;

/**
 * Measurable objects, i.e. 2D objects with width and height.
 *
 * @author Radek Oslejsek, Marek Sabo
 */
public interface Measurable {

    /**
     * Returns the width of the object,
     * i.e. the width of the smallest bounding rectangle.
     *
     * @return object's width
     */
    double getWidth();

    /**
     * Returns the height of the object,
     * i.e. the height of the smallest bounding rectangle.
     *
     * @return object's height
     */
    double getHeight();

}
