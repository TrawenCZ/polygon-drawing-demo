package cz.muni.fi.pb162.project.exception;

/**
 * Class for throwing Exceptions when vertices are missing in structure.
 *
 * @author Adam Slíva
 */
public class MissingVerticesException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param errMsg Error message to be printed.
     */
    public MissingVerticesException(String errMsg) {
        super(errMsg);
    }

    /**
     * Constructor.
     *
     * @param errMsg Error message to be printed.
     * @param cause Cause that caused exception.
     */
    public MissingVerticesException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }
}
