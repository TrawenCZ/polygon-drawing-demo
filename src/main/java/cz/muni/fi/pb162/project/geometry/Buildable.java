package cz.muni.fi.pb162.project.geometry;

/**
 * This interface represents a builder pattern.
 * In other words, it separates the complex construction of an object from its representation.
 * When the object is ready to be built, {@link #build()} method is called.
 * <p>
 * Every implementation of this interface should contain at least one method to fill up the builder with the data.
 *
 * @param <T> represents a type which should be built.
 * @author Marek Sabo
 */

public interface Buildable<T> {

    /**
     * Method is called when the builder object is filled with data.
     *
     * @return new built object
     */
    T build();
}
