package cz.muni.fi.pb162.project.comparator;

import cz.muni.fi.pb162.project.geometry.Vertex2D;

import java.util.Comparator;

/**
 * Class for comparing Vertexes.
 *
 * @author Adam Slíva
 */
public class VertexInverseComparator implements Comparator<Vertex2D> {

    @Override
    public int compare(Vertex2D o1, Vertex2D o2) {
        return o2.compareTo(o1);
    }
}
