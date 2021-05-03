package cz.muni.fi.pb162.project.exception;
/**
 * Class for throwing Exceptions when Color is the same as background.
 *
 * @author Adam Slíva
 */
public class TransparentColorException extends Exception {
    /**
     * Constructor.
     *
     * @param errMsg Error message to be printed.
     */
    public TransparentColorException(String errMsg) {
        super(errMsg);
    }
    /**
     * Constructor.
     *
     * @param errMsg Error message to be printed.
     * @param cause Cause that caused error.
     */
    public TransparentColorException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }
}
