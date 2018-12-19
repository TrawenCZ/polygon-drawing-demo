package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.comparator.VertexInverseComparator;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Marek Sabo
 */
public class LabeledPolygonTest {

    private LabeledPolygon polygon;

    @Before
    public void setUp() {
        polygon = new LabeledPolygon.Builder()
                .addVertex("A", new Vertex2D(2, 5))
                .addVertex("B", new Vertex2D(3, 1))
                .addVertex("C", new Vertex2D(1, 3))
                .build();
    }

    @Test
    public void checkNumVertices() {
        assertThat(polygon.getNumVertices()).isEqualTo(3);
    }

    @Test
    public void puttingSamePolygonTwice() {
        LabeledPolygon polygon = new LabeledPolygon.Builder()
                .addVertex("A", new Vertex2D(2, 5))
                .addVertex("B", new Vertex2D(3, 1))
                .addVertex("A", new Vertex2D(2, 5))
                .addVertex("C", new Vertex2D(1, 3))
                .build();
        assertThat(polygon.getNumVertices()).isEqualTo(3);
    }

    @Test
    public void getVertexIndexInRange() {
        assertThat(polygon.getVertex(0)).isEqualTo(new Vertex2D(2, 5));
        assertThat(polygon.getVertex(1)).isEqualTo(new Vertex2D(3, 1));
        assertThat(polygon.getVertex(2)).isEqualTo(new Vertex2D(1, 3));
    }

    @Test
    public void getVertexIndexOutOfRangePositive() {
        assertThat(polygon.getVertex(3)).isEqualTo(new Vertex2D(2, 5));
        assertThat(polygon.getVertex(4)).isEqualTo(new Vertex2D(3, 1));
        assertThat(polygon.getVertex(5)).isEqualTo(new Vertex2D(1, 3));
    }

    @Test
    public void getVertexIndexOutOfRangeNegative() {
        assertThatThrownBy(() -> polygon.getVertex(-1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> polygon.getVertex(-523))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void addVertexWithNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LabeledPolygon.Builder().addVertex(null, null));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LabeledPolygon.Builder().addVertex("A", null))
                .withMessageContaining("vertex");
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LabeledPolygon.Builder().addVertex(null, new Vertex2D(1, 4)))
                .withMessageContaining("label");
    }

    @Test
    public void getVertexLabelNull() {
        assertThatThrownBy(() -> polygon.getVertex(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void getVertexLabel() {
        assertThat(polygon.getVertex("A")).isEqualTo(new Vertex2D(2, 5));
    }

    @Test
    public void checkGetAllLabels() {
        LabeledPolygon polygon = new LabeledPolygon.Builder()
                .addVertex("A", new Vertex2D(0, 0))
                .addVertex("C", new Vertex2D(1, 4))
                .addVertex("A", new Vertex2D(0, 2))
                .addVertex("C", new Vertex2D(1, 4))
                .addVertex("B", new Vertex2D(7, 3))
                .build();
        assertThat(polygon.getLabels()).containsExactly("A", "B", "C");
        assertNewOrUnmodifiableCollection(polygon, polygon.getLabels());
    }

    @Test
    public void checkGetLabelsWithNonExistingVertex() {
        assertThat(polygon.getLabels(new Vertex2D(0, 42))).isEmpty();
        assertThat(polygon.getLabels(new Vertex2D(351, 789))).isEmpty();
    }

    @Test
    public void checkGetLabelsWithExistingVertex() {
        LabeledPolygon polygon = new LabeledPolygon.Builder()
                .addVertex("B", new Vertex2D(1, 1))
                .addVertex("B", new Vertex2D(0, 0))
                .addVertex("C", new Vertex2D(1, 4))
                .addVertex("A", new Vertex2D(0, 0))
                .build();

        assertThat(polygon.getLabels(new Vertex2D(0, 0)))
                .containsExactly("A", "B");
        assertThat(polygon.getLabels(new Vertex2D(1, 4)))
                .containsExactly("C");
        assertNewOrUnmodifiableCollection(polygon, polygon.getLabels(new Vertex2D(0, 0)));
    }

    @Test
    public void getSortedVertices() {
        LabeledPolygon polygon = new LabeledPolygon.Builder()
                .addVertex("B", new Vertex2D(1, 0))
                .addVertex("C", new Vertex2D(1, 4))
                .addVertex("A", new Vertex2D(0, 0))
                .build();

        assertThat(polygon.getSortedVertices())
                .containsExactly(
                        new Vertex2D(0, 0),
                        new Vertex2D(1, 0),
                        new Vertex2D(1, 4)
                );
    }

    @Test
    public void getSortedVerticesInverseComparator() {
        Comparator<Vertex2D> comparator = new VertexInverseComparator();

        LabeledPolygon polygon = new LabeledPolygon.Builder()
                .addVertex("B", new Vertex2D(1, 0))
                .addVertex("C", new Vertex2D(1, 4))
                .addVertex("A", new Vertex2D(0, 0))
                .build();

        assertThat(polygon.getSortedVertices(comparator))
                .containsExactly(
                        new Vertex2D(1, 4),
                        new Vertex2D(1, 0),
                        new Vertex2D(0, 0)
                );
    }

    @Test
    public void getSortedVerticesOtherComparator() {
        Comparator<Vertex2D> comparator = Comparator.comparingDouble(Vertex2D::getX);

        LabeledPolygon polygon = new LabeledPolygon.Builder()
                .addVertex("B", new Vertex2D(1, 0))
                .addVertex("C", new Vertex2D(1, 4))
                .addVertex("A", new Vertex2D(0, 0))
                .build();

        assertThat(polygon.getSortedVertices(comparator))
                .containsExactly(
                        new Vertex2D(0, 0),
                        new Vertex2D(1, 0)
                        );
    }

    @Test
    public void duplicateVertices() {
        LabeledPolygon polygon = new LabeledPolygon.Builder()
                .addVertex("A", new Vertex2D(-20, 20))
                .addVertex("B", new Vertex2D(100, 100))
                .addVertex("C", new Vertex2D(-100, -100))
                .addVertex("D", new Vertex2D(-20, 20))
                .addVertex("E", new Vertex2D(-100, 100))
                .addVertex("F", new Vertex2D(100, -100))
                .addVertex("G", new Vertex2D(0, 0))
                .addVertex("H", new Vertex2D(0, 0))
                .build();
        assertThat(polygon.duplicateVertices())
                .containsExactlyInAnyOrder(
                        new Vertex2D(-20, 20),
                        new Vertex2D(0, 0)
                );
    }

    private void assertNewOrUnmodifiableCollection(Polygon polygon, Collection<?> collection) {
        int expectedSize = polygon.getNumVertices();
        try {
            collection.clear();
            assertThat(polygon.getNumVertices())
                    .as("Method returns modifiable collection - return new or unmodifiable.")
                    .isEqualTo(expectedSize);
        } catch (UnsupportedOperationException e) {
            // ok
        }
    }
}
