package pt.ipp.isep.dei.esoft.project.domain.Graph.map;

import pt.ipp.isep.dei.esoft.project.domain.Graph.Edge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a vertex in a graph with adjacency information.
 *
 * @param <V> Type of the vertex element.
 * @param <E> Type of the edge weight or value.
 */
public class MapVertex<V, E> {

    // Vertex information
    private final V element;

    // Map of adjacent vertices and their corresponding edges
    private final Map<V, Edge<V, E>> outVerts;

    /**
     * Constructor to initialize a vertex with the given element.
     *
     * @param vert The vertex element.
     * @throws RuntimeException If the vertex element is null.
     * @complexity O(1)
     */
    public MapVertex(V vert) {
        if (vert == null) throw new RuntimeException("Vertex information cannot be null!");
        this.element = vert;
        this.outVerts = new LinkedHashMap<>();
    }

    /**
     * Returns the element associated with the vertex.
     *
     * @return The vertex element.
     * @complexity O(1)
     */
    public V getElement() {
        return element;
    }

    /**
     * Adds an adjacent vertex and its corresponding edge to the adjacency map.
     *
     * @param vAdj Adjacent vertex.
     * @param edge Edge connecting the current vertex to the adjacent vertex.
     * @complexity O(1) (map insertion).
     */
    public void addAdjVert(V vAdj, Edge<V, E> edge) {
        outVerts.put(vAdj, edge);
    }

    /**
     * Removes an adjacent vertex and its corresponding edge from the adjacency map.
     *
     * @param vAdj The adjacent vertex to remove.
     * @complexity O(1) (map removal).
     */
    public void remAdjVert(V vAdj) {
        outVerts.remove(vAdj);
    }

    /**
     * Retrieves the edge connecting the current vertex to the specified adjacent vertex.
     *
     * @param vAdj Adjacent vertex.
     * @return The edge if it exists; null otherwise.
     * @complexity O(1) (map lookup).
     */
    public Edge<V, E> getEdge(V vAdj) {
        return outVerts.get(vAdj);
    }

    /**
     * Returns the number of adjacent vertices.
     *
     * @return The number of adjacent vertices.
     * @complexity O(1)
     */
    public int numAdjVerts() {
        return outVerts.size();
    }

    /**
     * Returns a collection of all adjacent vertices.
     *
     * @return A collection of adjacent vertices.
     * @complexity O(k), where k is the number of adjacent vertices.
     */
    public Collection<V> getAllAdjVerts() {
        return new ArrayList<>(outVerts.keySet());
    }

    /**
     * Returns a collection of all outgoing edges.
     *
     * @return A collection of outgoing edges.
     * @complexity O(k), where k is the number of outgoing edges.
     */
    public Collection<Edge<V, E>> getAllOutEdges() {
        return new ArrayList<>(outVerts.values());
    }

    /**
     * Returns a string representation of the vertex, including its adjacent vertices and edges.
     *
     * @return A string representation of the vertex.
     * @complexity O(k), where k is the number of adjacent vertices.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertex: ").append(element).append("\n");
        sb.append("Adjacent Vertices and Edges:\n");

        if (!outVerts.isEmpty()) {
            for (Map.Entry<V, Edge<V, E>> entry : outVerts.entrySet()) {
                V adjacentVertex = entry.getKey();
                Edge<V, E> edge = entry.getValue();
                sb.append("  - To Vertex: ").append(adjacentVertex)
                        .append(" | Edge Details: ").append(edge)
                        .append("\n");
            }
        } else {
            sb.append("  No adjacent vertices.\n");
        }

        return sb.toString();
    }
}
