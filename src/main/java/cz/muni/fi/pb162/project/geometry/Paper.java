package cz.muni.fi.pb162.project.geometry;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for drawing Colored Polygons.
 *
 * @author Adam Slíva
 */
public class Paper implements Drawable {
    private Color color = Color.BLACK;
    private Set<ColoredPolygon> objectSet = new HashSet<>();;

    /**
     * Constructor.
     *
     */
    public Paper() {
    }

    /**
     * Constructor.
     *
     * @param object Gives Drawable object to be copied to the new Paper.
     */
    public Paper(Drawable object) {
        this.objectSet = Set.copyOf(object.getAllDrawnPolygons());
    }

    @Override
    public void changeColor(Color color) {
        this.color = color;
    }

    @Override
    public void drawPolygon(Polygon polygon) {
        if (this.color != Color.WHITE) {
            this.objectSet.add(new ColoredPolygon(polygon, color));
        }
    }

    @Override
    public void erasePolygon(ColoredPolygon polygon) {
        this.objectSet.remove(polygon);
    }

    @Override
    public void eraseAll() {
        this.objectSet.clear();
    }

    @Override
    public Collection<ColoredPolygon> getAllDrawnPolygons() {
        return Collections.unmodifiableCollection(this.objectSet);
    }

    @Override
    public int uniqueVerticesAmount() {
        Set<Vertex2D> uniqueVertices = new HashSet<>();
        for (ColoredPolygon polygon : this.objectSet) {
            for (int i = 0; i < polygon.getPolygon().getNumVertices(); i++) {
                uniqueVertices.add(polygon.getPolygon().getVertex(i));
            }
        }
        return uniqueVertices.size();
    }
}
