package cz.muni.fi.pb162.project.geometry;

import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.Set;
import java.util.HashSet;

/**
 * Class for creating polygons with Labeled vertexes.
 *
 * @author Adam Slíva
 */
public final class LabeledPolygon extends SimplePolygon implements Labelable, Sortable {
    private SortedMap<String, Vertex2D> sortedVertices;
    /**
     * Constructor.
     */
    private LabeledPolygon(TreeMap<String, Vertex2D> newMap) {
        super(newMap.values().toArray(new Vertex2D[0]));
        this.sortedVertices = newMap;
    }

    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index should be greater or equal to zero.");
        }
        index = index % getNumVertices();
        Vertex2D output = null;
        for (Map.Entry<String, Vertex2D> current : this.sortedVertices.entrySet()) {
            if (index == 0) {
                output = current.getValue();
                break;
            }
            index--;
        }
        return output;
    }

    @Override
    public int getNumVertices() {
        return sortedVertices.size();
    }

    @Override
    public Vertex2D getVertex(String label) {
        return this.sortedVertices.get(label);
    }

    @Override
    public Collection<String> getLabels() {
        return new ArrayList<>(this.sortedVertices.keySet());
    }

    @Override
    public Collection<String> getLabels(Vertex2D vertex) {
        Set<String> output = new HashSet<>();
        for (Map.Entry<String, Vertex2D> current : this.sortedVertices.entrySet()) {
            if (current.getValue().equals(vertex)) {
                output.add(current.getKey());
            }
        }
        return output;
    }

    @Override
    public Collection<Vertex2D> getSortedVertices() {
        return this.sortedVertices.values();
    }

    @Override
    public Collection<Vertex2D> getSortedVertices(Comparator<Vertex2D> comparator) {
        ArrayList<Vertex2D> output = new ArrayList<>(this.sortedVertices.values());
        output.sort(comparator);
        return output;
    }

    /**
     * Method for getting duplicit key's values.
     *
     * @return Values of duplicit keys.
     */
    public Collection<Vertex2D> duplicateVertices() {
        Set<Vertex2D> output = new HashSet<>();
        for (Map.Entry<String, Vertex2D> current : this.sortedVertices.entrySet()) {
            for (Map.Entry<String, Vertex2D> next : this.sortedVertices.entrySet()) {
                if (!current.getKey().equals(next.getKey()) && current.getValue().equals(next.getValue())) {
                    output.add(current.getValue());
                }
            }
        }
        return output;
    }

    /**
     * Builder for class.
     *
     * @author Adam Slíva
     */
    public static class Builder implements Buildable {
        private TreeMap<String, Vertex2D> sortedVertices;

        /**
         * Constructor for builder.
         */
        public Builder() {
            this.sortedVertices = new TreeMap<>();
        }

        @Override
        public LabeledPolygon build() {
            return new LabeledPolygon(sortedVertices);
        }

        /**
         * Method for adding new Labeled vertex to the class.
         *
         * @param label Label of vertex.
         * @param vertex Vertex itself.
         *
         * @return Builder itself.
         */
        public Builder addVertex(String label, Vertex2D vertex) {
            if (label == null) {
                throw new IllegalArgumentException("label of the vertex cannot be null!");
            } else if (vertex == null) {
                throw new IllegalArgumentException("vertex cannot be null!");
            }
            this.sortedVertices.remove(label);
            this.sortedVertices.put(label, vertex);
            return this;
        }
    }
}
