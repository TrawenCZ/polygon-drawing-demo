package cz.muni.fi.pb162.project.geometry;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Interface for data export.
 * <p>
 * Data is in the format <code>x y name</code>.
 *
 * @author Radek Oslejsek, Marek Sabo
 */
public interface PolygonWritable {

    /**
     * Write the data into the output stream.
     *
     * @param os output stream
     * @throws IOException on write error
     */
    void write(OutputStream os) throws IOException;

    /**
     * Write data to the file.
     *
     * @param file output file
     * @throws IOException on write error
     */
    void write(File file) throws IOException;

}
