package cz.muni.fi.pb162.project.geometry;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests implementation of drawable interface, Paper.
 *
 * @author Marek Sabo
 */
public class PaperTest {

    public static final Vertex2D[] VERTICES_1 = {
            new Vertex2D(0, 0),
            new Vertex2D(10, 10),
            new Vertex2D(-5, 5),
    };

    public static final Vertex2D[] VERTICES_2 = {
            new Vertex2D(0, 0),
            new Vertex2D(15, 11),
            new Vertex2D(-4, -9),
            new Vertex2D(14, -1),
    };
    private Paper paper;
    private Polygon polygon1 = new Triangle(VERTICES_1[0], VERTICES_1[1], VERTICES_1[2]);
    private Polygon polygon2 = new ArrayPolygon(VERTICES_2);

    @Before
    public void setUp() {
        paper = new Paper();
    }

    @Test
    public void constructorCopiesCollection() {
        paper.drawPolygon(polygon1);
        Paper paper2 = new Paper(paper);
        paper.eraseAll();
        assertThat(paper2.getAllDrawnPolygons()).containsExactly(new ColoredPolygon(polygon1, Color.BLACK));
    }

    @Test
    public void drawPolygonWhiteColor() {
        paper.changeColor(Color.WHITE);
        paper.drawPolygon(polygon1);
        assertThat(paper.getAllDrawnPolygons()).isEmpty();
    }

    @Test
    public void drawPolygon() {
        paper.drawPolygon(polygon1);
        paper.drawPolygon(polygon2);
        assertThat(paper.getAllDrawnPolygons()).containsOnly(
                new ColoredPolygon(polygon1, Color.BLACK),
                new ColoredPolygon(polygon2, Color.BLACK)
        );
    }

    @Test
    public void changeColor() {
        paper.changeColor(Color.RED);
        paper.drawPolygon(polygon1);
        assertThat(paper.getAllDrawnPolygons()).containsExactly(new ColoredPolygon(polygon1, Color.RED));
    }

    @Test
    public void drawSamePolygonTwice() {
        paper.drawPolygon(polygon1);
        paper.drawPolygon(polygon1);
        assertThat(paper.getAllDrawnPolygons()).containsExactly(new ColoredPolygon(polygon1, Color.BLACK));
    }

    @Test
    public void drawSamePolygonTwiceColorSame() {
        paper.changeColor(Color.YELLOW);
        paper.drawPolygon(polygon1);
        paper.drawPolygon(polygon1);
        assertThat(paper.getAllDrawnPolygons()).containsExactly(new ColoredPolygon(polygon1, Color.YELLOW));
    }

    @Test
    public void erasePolygon() {
        paper.drawPolygon(polygon1);
        paper.erasePolygon(new ColoredPolygon(polygon1, Color.BLACK));
        assertThat(paper.getAllDrawnPolygons()).isEmpty();
    }

    @Test
    public void eraseAll() {
        paper.drawPolygon(polygon1);
        paper.drawPolygon(polygon2);
        paper.eraseAll();
        assertThat(paper.getAllDrawnPolygons()).isEmpty();
    }

    @Test
    public void unmodifiableCollection() {
        assertThatThrownBy(() -> paper.getAllDrawnPolygons().clear())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void uniqueVerticesAmount() {
        paper.drawPolygon(polygon1);
        paper.changeColor(Color.YELLOW);
        paper.drawPolygon(polygon2);

        assertThat(paper.uniqueVerticesAmount()).isEqualTo(6);
    }

}
