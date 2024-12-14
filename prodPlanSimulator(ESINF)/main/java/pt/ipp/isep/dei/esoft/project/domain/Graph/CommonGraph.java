package pt.ipp.isep.dei.esoft.project.domain.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Abstract class implementing basic graph operations for handling vertices and edges.
 * This class is extended by specific graph implementations such as adjacency list or matrix.
 *
 * @param <V> Vertex value type (the type of vertices in the graph)
 * @param <E> Edge weight type (the type of edge weights in the graph)
 */
public abstract class CommonGraph <V,E> implements Graph<V,E> {
    protected int numVerts;           // Number of vertices in the graph
    protected int numEdges;           // Number of edges in the graph
    protected final boolean isDirected; // Whether the graph is directed or undirected
    protected ArrayList<V> vertices;  // List of vertices in the graph

    /**
     * Constructs a graph with specified directionality (directed or undirected).
     * Initializes the number of vertices and edges to zero.
     *
     * @param directed Indicates whether the graph is directed (true) or undirected (false)
     */
    public CommonGraph(boolean directed) {
        numVerts = 0;
        numEdges = 0;
        isDirected = directed;
        vertices = new ArrayList<>();
    }

    /**
     * Checks if the graph is directed (has edges with a direction).
     *
     * @return true if the graph is directed, false if undirected
     *
     * Time Complexity: O(1)
     */
    @Override
    public boolean isDirected() {
        return isDirected;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return The number of vertices in the graph
     *
     * Time Complexity: O(1)
     */
    @Override
    public int numVertices() {
        return numVerts;
    }

    /**
     * Returns a list of vertices in the graph.
     *
     * @return A new ArrayList containing all vertices in the graph
     *
     * Time Complexity: O(n) where n is the number of vertices
     */
    @Override
    public ArrayList<V> vertices() {
        return new ArrayList<>(vertices);  // Return a copy of the list of vertices
    }

    /**
     * Checks if the given vertex is valid (exists in the graph).
     *
     * @param vert The vertex to check
     * @return true if the vertex exists in the graph, false otherwise
     *
     * Time Complexity: O(n) where n is the number of vertices
     */
    @Override
    public boolean validVertex(V vert) {
        return vertices.contains(vert);  // Check if the vertex exists in the graph
    }

    /**
     * Returns the index (key) of the given vertex in the graph.
     *
     * @param vert The vertex to get the index for
     * @return The index of the vertex in the graph, or -1 if the vertex is not found
     *
     * Time Complexity: O(n) where n is the number of vertices (because of indexOf search)
     */
    @Override
    public int key(V vert) {
        return vertices.indexOf(vert);  // Return the index of the vertex, or -1 if not found
    }

    /**
     * Returns the vertex at the given key (index).
     *
     * @param key The index of the vertex to retrieve
     * @return The vertex at the given key, or null if the key is out of bounds
     *
     * Time Complexity: O(1) since ArrayList provides constant time access by index
     */
    @Override
    public V vertex(int key) {
        if ((key < 0) || (key >= numVerts)) return null;  // Return null if the key is out of bounds
        return vertices.get(key);  // Return the vertex at the specified key
    }

    /**
     * Returns the first vertex in the graph that satisfies the given predicate.
     *
     * @param p The predicate to test each vertex against
     * @return The first vertex that satisfies the predicate, or null if no vertex satisfies it
     *
     * Time Complexity: O(n) where n is the number of vertices, as we need to check each vertex
     */
    @Override
    public V vertex(Predicate<V> p) {
        for (V v : vertices) {
            if (p.test(v)) return v;  // Return the first vertex that satisfies the predicate
        }
        return null;  // Return null if no vertex satisfies the predicate
    }

    /**
     * Returns the number of edges in the graph.
     *
     * @return The number of edges in the graph
     *
     * Time Complexity: O(1)
     */
    @Override
    public int numEdges() {
        return numEdges;  // Return the number of edges in the graph
    }

    /**
     * Copies all vertices and edges from one graph to another.
     * This method can be used to clone a graph or to replicate one graph into another.
     *
     * @param from The source graph to copy from
     * @param to The destination graph to copy to
     *
     * Time Complexity: O(n + m) where n is the number of vertices and m is the number of edges
     */
    protected void copy(Graph <V,E> from, Graph <V,E> to) {
        // Insert all vertices
        for (V v : from.vertices()) {
            to.addVertex(v);
        }

        // Insert all edges
        for (Edge<V, E> e : from.edges()) {
            to.addEdge(e.getVOrig(), e.getVDest(), e.getWeight());
        }
    }

    /**
     * Compares this graph with another graph for equality.
     * Two graphs are considered equal if they have the same vertices, edges, and directionality.
     *
     * @param otherObj The object to compare with
     * @return true if the two graphs are equal, false otherwise
     *
     * Time Complexity: O(n + m) where n is the number of vertices and m is the number of edges
     */
    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) return true;  // Check if both references are the same
        if (!(otherObj instanceof Graph<?, ?>)) return false;  // Ensure the object is a Graph

        @SuppressWarnings("unchecked") Graph<V, E> otherGraph = (Graph<V, E>) otherObj;

        // Check if number of vertices, edges, and directionality are the same
        if (numVerts != otherGraph.numVertices() || numEdges != otherGraph.numEdges() || isDirected() != otherGraph.isDirected())
            return false;

        // Graph must have the same vertices
        Collection<V> tvc = this.vertices();
        tvc.removeAll(otherGraph.vertices());
        if (tvc.size() > 0) return false;  // If any vertices are missing, graphs are not equal

        // Graph must have the same edges
        Collection<Edge<V, E>> tec = this.edges();
        tec.removeAll(otherGraph.edges());
        return (tec.size() == 0);  // If any edges are missing, graphs are not equal
    }

    /**
     * Creates a clone of the graph.
     * This method needs to be implemented in the subclass (specific graph implementation).
     *
     * @return A clone of the graph
     */
    public abstract Graph<V, E> clone();

    /**
     * Computes the hash code for the graph.
     * The hash code is based on the number of vertices, edges, directionality, and vertices list.
     *
     * @return The hash code of the graph
     *
     * Time Complexity: O(n) where n is the number of vertices (due to hashCode calculation of vertices)
     */
    @Override
    public int hashCode() {
        return Objects.hash(numVerts, numEdges, isDirected, vertices);
    }
}
