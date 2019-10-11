package cz.muni.fi.pb162.project.geometry;

/**
 * Class represents circle that passes through every vertex of 2d geometry object.
 *
 * @author Marek Sabo
 */
public interface Circular {

    /**
     * Returns the middle point of the circle.
     *
     * @return the middle point.
     */
    Vertex2D getCenter();

    /**
     * Returns the radius of the circumcircle.
     *
     * @return circle's radius
     */
    double getRadius();

}
