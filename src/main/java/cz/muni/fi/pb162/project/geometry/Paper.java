package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.EmptyDrawableException;
import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.exception.TransparentColorException;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Class for drawing Colored Polygons.
 *
 * @author Adam Slíva
 */
public class Paper implements Drawable, PolygonFactory {
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
        this.objectSet = new HashSet<>(object.getAllDrawnPolygons());
    }

    @Override
    public void changeColor(Color color) {
        this.color = color;
    }

    @Override
    public void drawPolygon(Polygon polygon) throws TransparentColorException {
        if (this.color == Color.WHITE) {
            throw new TransparentColorException("Drawing on white paper with white? Are you sure?");
        }
        this.objectSet.add(new ColoredPolygon(polygon, color));
    }

    @Override
    public void erasePolygon(ColoredPolygon polygon) {
        this.objectSet.remove(polygon);
    }

    @Override
    public void eraseAll() throws EmptyDrawableException {
        if (objectSet.size() == 0) {
            throw new EmptyDrawableException("Paper is already empty!");
        }
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

    public Color getColor() {
        return this.color;
    }

    @Override
    public Polygon tryToCreatePolygon(List<Vertex2D> vertices) {
        if (vertices == null) {
            throw new NullPointerException("Input is a null pointer");
        }
        List<Vertex2D> copyOfVertices = new ArrayList<>(vertices);
        try {
            return new CollectionPolygon(copyOfVertices);
        } catch (IllegalArgumentException e) {
            copyOfVertices.removeIf(Objects::isNull);
            return tryToCreatePolygon(copyOfVertices);
        }
    }

    @Override
    public void tryToDrawPolygons(List<List<Vertex2D>> collectionPolygons) throws EmptyDrawableException {
        boolean somethingDrawn = false;
        int counter = 0;
        for (List<Vertex2D> verticesList : collectionPolygons) {
            try {
                drawPolygon(tryToCreatePolygon(verticesList));
                somethingDrawn = true;
            } catch (TransparentColorException e) {
                this.color = Color.BLACK;
            } catch (NullPointerException | MissingVerticesException e) {
                if (!somethingDrawn && counter == collectionPolygons.size() - 1) {
                    throw new EmptyDrawableException("Nothing has been drawn.", e);
                }
            }
            counter++;
        }

    }

    /**
     * Method for getting all polygons from paper with given color.
     *
     * @param color Color that polygons should have.
     * @return Filtered set of polygons.
     */
    public Collection<Polygon> getPolygonsWithColor(Color color) {
        Set<Polygon> output = new HashSet<>();
        this.objectSet.stream().filter(p->p.getColor() == color).forEach(p->output.add(p.getPolygon()));
        return output;
    }
}
