package cz.muni.fi.pb162.project.geometry;

/**
 * Extender for Octagon polygons.
 *
 * @author Adam Slíva
 */
public class RegularOctagon extends GeneralRegularPolygon {

    /**
     * Constructor for Octagon.
     *
     * @param center Gives center of Octagon.
     * @param radius Gives radius of Octagon.
     */
    public RegularOctagon(Vertex2D center, double radius) {
        super(center, 8, radius);
    }
}
