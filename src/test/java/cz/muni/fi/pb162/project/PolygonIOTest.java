package cz.muni.fi.pb162.project;

import cz.muni.fi.pb162.project.geometry.LabeledPolygon;
import cz.muni.fi.pb162.project.geometry.Vertex2D;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIOException;


/**
 * @author Marek Sabo
 */
public class PolygonIOTest {

    private static final String POLYGON_OK_TXT = "polygon-ok.txt";
    private static final String POLYGON_ERROR_TXT = "polygon-error.txt";
    private static final String POLYGON_OUT_TXT = "polygon-out.txt";

    private LabeledPolygon filledPolygon;
    private LabeledPolygon.Builder builder;

    @Before
    public void setUp() {
        builder = new LabeledPolygon.Builder();
        filledPolygon = new LabeledPolygon.Builder()
                .addVertex("a", new Vertex2D(-100, 0))
                .addVertex("b", new Vertex2D(0, -100))
                .addVertex("c", new Vertex2D(0, 100))
                .addVertex("d", new Vertex2D(-100, 200))
                .addVertex("e", new Vertex2D(-100, 200))
                .build();
    }

    @Test
    public void readCorrectFileDoesNotThrowException() {
        assertThatCode(() -> builder.read(new File(POLYGON_OK_TXT))).doesNotThrowAnyException();
    }

    @Test
    public void readCorrectStreamDoesNotThrowException() {
        assertThatCode(() -> builder.read(new FileInputStream(POLYGON_OK_TXT))).doesNotThrowAnyException();
    }

    @Test
    public void readInvalidFileThrowsException() {
        assertThatIOException().isThrownBy(() -> builder.read(new File(POLYGON_ERROR_TXT)));
    }

    @Test
    public void readInvalidStreamThrowsException() {
        assertThatIOException().isThrownBy(() -> builder.read(new FileInputStream(POLYGON_ERROR_TXT)));
    }

    @Test
    public void readInvalidStreamWrongCoordinate() throws UnsupportedEncodingException {
        // given
        String errorString = "1 2a XXX";
        // when
        InputStream stream = new ByteArrayInputStream(errorString.getBytes(StandardCharsets.UTF_8.name()));
        // then
        assertThatIOException().isThrownBy(() -> builder.read(stream));
    }

    @Test
    public void readInvalidStreamMissingParameter() throws UnsupportedEncodingException {
        // given
        String errorString = "1 2";
        // when
        InputStream stream = new ByteArrayInputStream(errorString.getBytes(StandardCharsets.UTF_8.name()));
        // then
        assertThatIOException().isThrownBy(() -> builder.read(stream));
    }

    @Test
    public void readInvalidStreamInvalidNumberFormat() throws UnsupportedEncodingException {
        // given
        String errorString = "1,1 2,1 name";
        // when
        InputStream stream = new ByteArrayInputStream(errorString.getBytes(StandardCharsets.UTF_8.name()));
        // then
        assertThatIOException().isThrownBy(() -> builder.read(stream));
    }

    @Test
    public void readValidStream() throws UnsupportedEncodingException {
        // given
        String errorString = "1 2 name with spaces";
        // when
        InputStream stream = new ByteArrayInputStream(errorString.getBytes(StandardCharsets.UTF_8.name()));
        // then
        assertThatCode(() -> builder.read(stream))
                .doesNotThrowAnyException();
    }

    @Test
    public void readCorrectFile() throws IOException {
        // when
        LabeledPolygon polygon = builder.read(new FileInputStream(POLYGON_OK_TXT)).build();
        // then
        assertThat(polygon.getSortedVertices()).containsExactly(
                new Vertex2D(-100, 0),
                new Vertex2D(-100, 200),
                new Vertex2D(0, -100),
                new Vertex2D(0, 100)
        );
    }

    @Test
    public void writeAndReadData() throws IOException {
        // when
        filledPolygon.write(new File(POLYGON_OUT_TXT));
        LabeledPolygon polygon = builder.read(new FileInputStream(POLYGON_OK_TXT)).build();
        // then
        assertThat(polygon.getSortedVertices())
                .containsExactly(
                        new Vertex2D(-100, 0),
                        new Vertex2D(-100, 200),
                        new Vertex2D(0, -100),
                        new Vertex2D(0, 100)
                );
    }
}
