package pt.ipp.isep.dei.esoft.project.domain.Graph;

import java.util.Objects;

/**
 * Represents an edge in a graph, connecting two vertices (vOrig and vDest) with an optional weight (weight).
 * This class is parameterized with the types of the vertices (V) and the edge's weight (E).
 *
 * @param <V> The type of the vertex values (could be any object type, e.g., Activity, Integer, etc.)
 * @param <E> The type of the edge weight (could be any numeric or custom object, e.g., Double, Integer, etc.)
 */
public class Edge<V, E> {
    final private V vOrig;        // Vertex origin
    final private V vDest;        // Vertex destination
    private E weight;             // Edge weight (optional, can be null)

    /**
     * Constructor to create an edge with specified origin and destination vertices, and a weight.
     * Throws an exception if either the origin or destination vertices are null.
     *
     * @param vOrig The origin vertex
     * @param vDest The destination vertex
     * @param weight The weight of the edge
     * @throws RuntimeException if vOrig or vDest is null
     */
    public Edge(V vOrig, V vDest, E weight) {
        if ((vOrig == null) || (vDest == null)) throw new RuntimeException("Edge vertices cannot be null!");
        this.vOrig = vOrig;
        this.vDest = vDest;
        this.weight = weight;
    }

    /**
     * Returns the origin vertex of this edge.
     *
     * @return The origin vertex (vOrig)
     */
    public V getVOrig() {
        return vOrig;
    }

    /**
     * Returns the destination vertex of this edge.
     *
     * @return The destination vertex (vDest)
     */
    public V getVDest() {
        return vDest;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return The weight of the edge (weight)
     */
    public E getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the edge.
     *
     * @param weight The new weight for the edge
     */
    public void setWeight(E weight) {
        this.weight = weight;
    }

    /**
     * Returns a string representation of the edge, displaying the origin, destination, and weight.
     *
     * @return A string representation of the edge
     */
    @Override
    public String toString() {
        return String.format("%s -> %s\nWeight: %s", vOrig, vDest, weight);
    }

    /**
     * Checks if two edges are equal by comparing their origin and destination vertices.
     * The weight is not considered in equality.
     *
     * @param o The object to compare with this edge
     * @return true if the edges have the same origin and destination vertices; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @SuppressWarnings("unchecked") Edge<V, E> edge = (Edge<V, E>) o;
        return vOrig.equals(edge.vOrig) && vDest.equals(edge.vDest);
    }

    /**
     * Returns a hash code for the edge based on the origin and destination vertices.
     * The weight is not included in the hash code computation.
     *
     * @return A hash code for the edge
     */
    @Override
    public int hashCode() {
        return Objects.hash(vOrig, vDest);
    }
}
