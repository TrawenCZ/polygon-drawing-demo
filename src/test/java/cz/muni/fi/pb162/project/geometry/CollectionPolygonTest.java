package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests polygon stored in a collection.
 *
 * @author Marek Sabo
 */
public class CollectionPolygonTest {

    private Vertex2D[] vertices = {
            new Vertex2D(-3, -1),
            new Vertex2D(2, 2),
            new Vertex2D(3, 4),
            new Vertex2D(-3, -1),
            new Vertex2D(-5, -3),
            new Vertex2D(-1, 1)
    };

    private CollectionPolygon collectionPolygon;

    @Before
    public void setUp() {
        collectionPolygon = new CollectionPolygon(vertices);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullInConstructor1() {
        new CollectionPolygon((Vertex2D[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullInConstructor1Array() {
        new CollectionPolygon(new Vertex2D[]{
                new Vertex2D(-3, -1),
                new Vertex2D(-2, -2),
                null
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullInConstructor2Array() {
        new CollectionPolygon(Arrays.asList(
                new Vertex2D(-3, -1),
                new Vertex2D(-2, -2),
                null
        ));
    }

    @Test
    public void constructor1CopiesArray() {
        Vertex2D oldVertex = vertices[0];
        vertices[0] = new Vertex2D(42, 42);
        assertThat(collectionPolygon.getVertex(0)).isEqualTo(oldVertex);
    }

    @Test
    public void constructor2CopiesList() {
        CollectionPolygon collectionPolygon2 = new CollectionPolygon(Arrays.asList(vertices));
        Vertex2D oldVertex = vertices[0];
        vertices[0] = new Vertex2D(42, 42);
        assertThat(collectionPolygon2.getVertex(0)).isEqualTo(oldVertex);
    }

    @Test
    public void getNumVertices() {
        assertThat(collectionPolygon.getNumVertices()).isEqualTo(vertices.length);
    }

    @Test
    public void gettersPositive() {
        for (int i = 0; i < 7; i++) {
            assertThat(collectionPolygon.getVertex(i)).isEqualTo(vertices[i % vertices.length]);
        }
    }

    @Test
    public void gettersOutOfRangeNegative() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> collectionPolygon.getVertex(-5))
                .withMessageContaining("index");
    }

    @Test
    public void withoutLeftMostVertices() {
        CollectionPolygon collectionPolygon;
        collectionPolygon = this.collectionPolygon.withoutLeftmostVertices();
        assertThat(collectionPolygon.getNumVertices()).isEqualTo(5);
        collectionPolygon = collectionPolygon.withoutLeftmostVertices();
        assertThat(collectionPolygon.getNumVertices()).isEqualTo(3);
        assertThatThrownBy(collectionPolygon::withoutLeftmostVertices)
                .isInstanceOf(MissingVerticesException.class);

        assertThat(this.collectionPolygon.getNumVertices())
                .as("Original polygon should not be changed")
                .isEqualTo(6);
    }

}
