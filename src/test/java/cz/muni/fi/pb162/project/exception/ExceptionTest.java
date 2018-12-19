package cz.muni.fi.pb162.project.exception;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.fail;

/**
 * Class checking exceptions constructors.
 *
 * @author Marek Sabo
 */
public class ExceptionTest {

    @Test
    public void shouldInheritFromException() {
        assertThat(new EmptyDrawableException("a")).isInstanceOf(Exception.class);
        assertThat(new TransparentColorException("a")).isInstanceOf(Exception.class);
        assertThat(new MissingVerticesException("a")).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void checkConstructors() {
        try {
            EmptyDrawableException.class.getConstructor(String.class);
            EmptyDrawableException.class.getConstructor(String.class, Throwable.class);
            MissingVerticesException.class.getConstructor(String.class);
            MissingVerticesException.class.getConstructor(String.class, Throwable.class);
            TransparentColorException.class.getConstructor(String.class);
            TransparentColorException.class.getConstructor(String.class, Throwable.class);
        } catch (NoSuchMethodException e) {
            fail("Missing constructor: " + e.getMessage());
        }
    }
}

