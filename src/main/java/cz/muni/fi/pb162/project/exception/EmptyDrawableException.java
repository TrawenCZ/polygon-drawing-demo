package cz.muni.fi.pb162.project.exception;

/**
 * Class for throwing Exceptions when Drawable object is empty.
 *
 * @author Adam Slíva
 */
public class EmptyDrawableException extends Exception {
    /**
     * Constructor.
     *
     * @param errMsg Message to be thrown.
     */
    public EmptyDrawableException(String errMsg) {
        super(errMsg);
    }

    /**
     * Constructor with cause param.
     *
     * @param errMsg Message to be thrown.
     * @param cause Cause that caused exception.
     */
    public EmptyDrawableException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }
}
