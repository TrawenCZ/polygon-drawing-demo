package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.EmptyDrawableException;
import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.exception.TransparentColorException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests implementation of drawable interface, Paper.
 *
 * @author Marek Sabo
 */
public class PaperTest {

    public static final List<Vertex2D> VERTICES_1 = LList.of(
            new Vertex2D(0, 0),
            new Vertex2D(10, 10),
            new Vertex2D(-5, 5)
    );

    public static final List<Vertex2D> VERTICES_2 = LList.of(
            new Vertex2D(0, 0),
            new Vertex2D(15, 11),
            new Vertex2D(-4, -9),
            new Vertex2D(14, -1)
    );
    private Paper paper;
    private Polygon polygon1 = new CollectionPolygon(VERTICES_1);
    private Polygon polygon2 = new CollectionPolygon(VERTICES_2);

    @Before
    public void setUp() {
        paper = new Paper();
    }

    @Test
    public void constructorCopiesCollection() throws TransparentColorException, EmptyDrawableException {
        paper.drawPolygon(polygon1);
        Paper paper2 = new Paper(paper);
        paper.eraseAll();
        assertThat(paper2.getAllDrawnPolygons()).containsExactly(new ColoredPolygon(polygon1, Color.BLACK));
    }

    @Test
    public void drawPolygonWhiteColor() {
        paper.changeColor(Color.WHITE);
        assertThatThrownBy(() -> paper.drawPolygon(polygon1))
                .isInstanceOf(TransparentColorException.class)
                .hasMessageContaining("white");
    }

    @Test
    public void drawPolygon() throws TransparentColorException {
        paper.drawPolygon(polygon1);
        paper.drawPolygon(polygon2);
        assertThat(paper.getAllDrawnPolygons()).containsOnly(
                new ColoredPolygon(polygon1, Color.BLACK),
                new ColoredPolygon(polygon2, Color.BLACK)
        );
    }

    @Test
    public void changeColor() throws TransparentColorException {
        paper.changeColor(Color.RED);
        paper.drawPolygon(polygon1);
        assertThat(paper.getAllDrawnPolygons()).containsExactly(new ColoredPolygon(polygon1, Color.RED));
    }

    @Test
    public void drawSamePolygonTwice() throws TransparentColorException {
        paper.drawPolygon(polygon1);
        paper.drawPolygon(polygon1);
        assertThat(paper.getAllDrawnPolygons()).containsExactly(new ColoredPolygon(polygon1, Color.BLACK));
    }

    @Test
    public void drawSamePolygonTwiceColorSame() throws TransparentColorException {
        paper.changeColor(Color.YELLOW);
        paper.drawPolygon(polygon1);
        paper.drawPolygon(polygon1);
        assertThat(paper.getAllDrawnPolygons()).containsExactly(new ColoredPolygon(polygon1, Color.YELLOW));
    }

    @Test
    public void erasePolygon() throws TransparentColorException {
        paper.drawPolygon(polygon1);
        paper.erasePolygon(new ColoredPolygon(polygon1, Color.BLACK));
        assertThat(paper.getAllDrawnPolygons()).isEmpty();
    }

    @Test
    public void eraseAll() throws TransparentColorException, EmptyDrawableException {
        paper.drawPolygon(polygon1);
        paper.drawPolygon(polygon2);
        paper.eraseAll();
        assertThat(paper.getAllDrawnPolygons()).isEmpty();
    }

    @Test
    public void eraseAllEmpty() {
        assertThatThrownBy(() -> paper.eraseAll())
                .isInstanceOf(EmptyDrawableException.class);
    }

    @Test
    public void unmodifiableCollection() {
        assertThatThrownBy(() -> paper.getAllDrawnPolygons().clear())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void uniqueVerticesAmount() throws TransparentColorException {
        paper.drawPolygon(polygon1);
        paper.changeColor(Color.YELLOW);
        paper.drawPolygon(polygon2);

        assertThat(paper.uniqueVerticesAmount()).isEqualTo(6);
    }

    @Test
    public void tryToCreateNull() {
        assertThatThrownBy(() -> paper.tryToCreatePolygon(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void tryToCreateCopiedCollection() {
        assertThatCode(
                () -> paper.tryToCreatePolygon(
                        Arrays.asList(
                                VERTICES_1.get(0),
                                VERTICES_1.get(1),
                                null,
                                VERTICES_1.get(2)
                        )
                ))
                .doesNotThrowAnyException();
    }

    @Test
    public void tryToCreateValidPolygons() {
        assertThat(paper.tryToCreatePolygon(VERTICES_1))
                .isEqualToComparingFieldByField(polygon1);
        assertThat(paper.tryToCreatePolygon(VERTICES_2))
                .isEqualToComparingFieldByField(polygon2);
    }

    @Test(expected = MissingVerticesException.class)
    public void tryToCreatePolygonWithNotEnoughVertices1() {
        List<Vertex2D> list = LList.of(new Vertex2D(0, 1));
        paper.tryToCreatePolygon(list);
    }

    @Test(expected = MissingVerticesException.class)
    public void tryToCreatePolygonWithNotEnoughVertices2() {
        List<Vertex2D> list = Arrays.asList(
                null,
                new Vertex2D(0, 1),
                null,
                null
        );
        paper.tryToCreatePolygon(list);
    }

    @Test
    public void tryToCreatePolygonWithTooManyNulls1() {
        List<Vertex2D> list = Arrays.asList(
                VERTICES_1.get(0),
                null,
                VERTICES_1.get(1),
                null,
                VERTICES_1.get(2),
                null
        );
        assertThat(paper.tryToCreatePolygon(list))
                .isEqualToComparingFieldByField(polygon1);
    }

    @Test
    public void filterPolygonsAccordingToColor() throws TransparentColorException {
        assertThat(paper.getPolygonsWithColor(Color.ORANGE))
                .as("Nothing has been drawn with orange color")
                .isEmpty();
        paper.changeColor(Color.RED);
        paper.drawPolygon(polygon1);
        paper.changeColor(Color.BLUE);
        paper.drawPolygon(polygon2);
        paper.changeColor(Color.RED);
        CollectionPolygon polygon3 =
                new CollectionPolygon(new Vertex2D[]{
                        new Vertex2D(0, 0),
                        new Vertex2D(1, 4),
                        new Vertex2D(8, 12)
                });
        paper.drawPolygon(polygon3);

        assertThat(paper.getPolygonsWithColor(Color.RED))
                .containsOnly(polygon1, polygon3);
        assertThat(paper.getPolygonsWithColor(Color.BLUE))
                .containsExactly(polygon2);
    }

    @Test
    public void tryToDrawCorrectPolygons() throws EmptyDrawableException {
        List<List<Vertex2D>> listOfLists = LList.of(VERTICES_1, VERTICES_2);
        paper.tryToDrawPolygons(listOfLists);

        assertThat(getPolygonsWithoutColor(paper))
                .containsOnly(polygon1, polygon2);
    }

    @Test
    public void tryToDrawWithWhiteColor() throws EmptyDrawableException {
        List<List<Vertex2D>> listOfLists = LList.of(VERTICES_1, VERTICES_2);
        paper.changeColor(Color.WHITE);
        paper.tryToDrawPolygons(listOfLists);

        assertThat(paper.getAllDrawnPolygons())
                .containsOnly(new ColoredPolygon(polygon2, Color.BLACK));
    }

    @Test
    public void tryToDrawOnlyWrongPolygons() {
        List<List<Vertex2D>> listOfLists = Arrays.asList(
                Arrays.asList(new Vertex2D(0, 1), null, null),
                Arrays.asList(null, null, null),
                null,
                Arrays.asList(null, new Vertex2D(-456, 11), null, new Vertex2D(-786, 134)),
                Arrays.asList(null, new Vertex2D(-456, 11))
        );

        assertThatThrownBy(() -> paper.tryToDrawPolygons(listOfLists))
                .isInstanceOf(EmptyDrawableException.class)
                .hasCauseExactlyInstanceOf(MissingVerticesException.class);
    }

    @Test
    public void tryToDrawOneCorrectPolygon() {
        Vertex2D v1 = new Vertex2D(-45, 11);
        Vertex2D v2 = new Vertex2D(26, 18);
        Vertex2D v3 = new Vertex2D(4, 5);

        List<List<Vertex2D>> listOfLists = LList.of(
                Arrays.asList(new Vertex2D(0, 1), null, null),
                Arrays.asList(v1, null, v2, v3, null),
                Arrays.asList(null, new Vertex2D(-456, 11), null)
        );

        assertThatCode(() -> paper.tryToDrawPolygons(listOfLists))
                .doesNotThrowAnyException();
        assertThat(getPolygonsWithoutColor(paper))
                .containsOnly(new CollectionPolygon(LList.of(v1, v2, v3)));
    }

    private List<Polygon> getPolygonsWithoutColor(Paper p) {
        return p.getAllDrawnPolygons()
                .stream()
                .map(ColoredPolygon::getPolygon)
                .collect(Collectors.toList());
    }

    private static class LList {
        @SafeVarargs
        static<E> List<E> of(E... elements) {
            return Arrays.asList(elements);
        }
    }
}
