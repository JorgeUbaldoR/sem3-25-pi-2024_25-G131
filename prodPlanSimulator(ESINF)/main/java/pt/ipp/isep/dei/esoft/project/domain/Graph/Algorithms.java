package pt.ipp.isep.dei.esoft.project.domain.Graph;

import pt.ipp.isep.dei.esoft.project.domain.Graph.matrix.MatrixGraph;

import java.util.*;
import java.util.function.BinaryOperator;

/**
 * @author DEI-ISEP
 */
public class Algorithms {

    /**
     * Performs breadth-first search of a Graph starting in a vertex
     *
     * @param g    Graph instance
     * @param vert vertex that will be the source of the search
     * @return a LinkedList with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }

        Queue<V> queue = new LinkedList<>();
        LinkedList<V> result = new LinkedList<>();
        Set<V> visited = new HashSet<>();

        queue.add(vert);
        visited.add(vert);

        while (!queue.isEmpty()) {
            V currentVertex = queue.poll();
            result.add(currentVertex);
            for (V adjVert : g.adjVertices(currentVertex)) {
                if (!visited.contains(adjVert)) {
                    queue.add(adjVert);
                    visited.add(adjVert);
                }
            }
        }
        return result;
    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g       Graph instance
     * @param vOrig   vertex of graph g that will be the source of the search
     * @param visited set of previously visited vertices
     * @param qdfs    return LinkedList with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        qdfs.add(vOrig);
        visited[g.key(vOrig)] = true;

        for (V adjVert : g.adjVertices(vOrig)) {
            if (!visited[g.key(adjVert)]) {
                DepthFirstSearch(g, adjVert, visited, qdfs);
            }
        }
    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g    Graph instance
     * @param vert vertex of graph g that will be the source of the search
     * @return a LinkedList with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {

        if (!g.validVertex(vert)) {
            return null;
        }

        boolean[] visited = new boolean[g.numVertices()];
        LinkedList<V> qdfs = new LinkedList<>();

        DepthFirstSearch(g, vert, visited, qdfs);

        return qdfs;
    }


    public static <V, E> boolean hasCircularDependencies(Graph<V, E> g) {

        boolean[] visited = new boolean[g.numVertices()];
        boolean[] recStack = new boolean[g.numVertices()];

        for (V vertex : g.vertices()) {
            if (!visited[g.key(vertex)]) {
                if (hasCycleUtil(g, vertex, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static <V, E> boolean hasCycleUtil(Graph<V, E> g, V v, boolean[] visited, boolean[] recStack) {
        int vKey = g.key(v);

        visited[vKey] = true;
        recStack[vKey] = true;

        for (V adjVert : g.adjVertices(v)) {
            int adjKey = g.key(adjVert);

            if (recStack[adjKey]) {
                return true;
            }

            if (!visited[adjKey] && hasCycleUtil(g, adjVert, visited, recStack)) {
                return true;
            }
        }

        recStack[vKey] = false;
        return false;
    }


    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g       Graph instance
     * @param vOrig   Vertex that will be the source of the path
     * @param vDest   Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path    stack with vertices of the current path (the path is in reverse order)
     * @param paths   ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
                                        LinkedList<V> path, ArrayList<LinkedList<V>> paths) {

        path.add(vOrig);
        visited[g.key(vOrig)] = true;

        if (vOrig.equals(vDest)) {
            paths.add(new LinkedList<>(path));
        } else {
            for (V adjVert : g.adjVertices(vOrig)) {
                if (!visited[g.key(adjVert)]) {
                    allPaths(g, adjVert, vDest, visited, path, paths);
                }
            }
        }

        path.removeLast();
        visited[g.key(vDest)] = false;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g     Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from vOrig to vDest
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return null;
        }

        boolean[] visited = new boolean[g.numVertices()];
        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        LinkedList<V> path = new LinkedList<>();

        allPaths(g, vOrig, vDest, visited, path, paths);
        return paths;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non-negative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of previously visited vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    private static <V, E> void shortestPathDijkstra(Graph<V, E> g, V vOrig,
                                                    Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                                    boolean[] visited, V[] pathKeys, E[] dist) {

        dist[g.key(vOrig)] = zero;

        PriorityQueue<V> queue = new PriorityQueue<>((v1, v2) ->
                ce.compare(dist[g.key(v1)], dist[g.key(v2)]));

        queue.add(vOrig);

        while (!queue.isEmpty()) {
            V currentVert = queue.poll();
            visited[g.key(currentVert)] = true;

            for (V adjVert : g.adjVertices(currentVert)) {
                Edge<V, E> edge = g.edge(currentVert, adjVert);
                E newDist = sum.apply(dist[g.key(currentVert)], edge.getWeight());

                if (!visited[g.key(adjVert)] && ce.compare(dist[g.key(adjVert)], newDist) > 0) {
                    dist[g.key(adjVert)] = newDist;
                    pathKeys[g.key(adjVert)] = currentVert;

                    queue.add(adjVert);
                }
            }
        }


    }


    /**
     * Shortest-path between two vertices
     *
     * @param g         graph
     * @param vOrig     origin vertex
     * @param vDest     destination vertex
     * @param ce        comparator between elements of type E
     * @param sum       sum two elements of type E
     * @param zero      neutral element of the sum in elements of type E
     * @param shortPath returns the vertices which make the shortest path
     * @return if vertices exist in the graph and are connected, true, false otherwise
     */
    @SuppressWarnings("unchecked")
    public static <V, E> E shortestPath(Graph<V, E> g, V vOrig, V vDest,
                                        Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                        LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return null;
        }

        if (vOrig.equals(vDest)) {
            shortPath.add(vOrig);
            return zero;
        }

        boolean[] visited = new boolean[g.numVertices()];
        V[] pathKeys = (V[]) new Object[g.numVertices()];
        E[] dist = (E[]) new Object[g.numVertices()];

        // Initialize distances
        Arrays.fill(dist, (E) Integer.valueOf(Integer.MAX_VALUE)); // Use a generic "infinity" value
        Arrays.fill(pathKeys, null);
        Arrays.fill(visited, false);

        dist[g.key(vOrig)] = zero; // Distance to the source is zero

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);

        // Debugging output
        System.out.print("Visited: ");
        for (boolean current : visited) {
            System.out.print(current + " ");
        }
        System.out.println("\n-----------------------1");

        System.out.print("Path Keys: ");
        for (V current : pathKeys) {
            System.out.printf("%s ", current);
        }
        System.out.println("\n-----------------------2");

        System.out.print("Distances: ");
        for (E current : dist) {
            System.out.printf("%s ", current.equals(Integer.valueOf(Integer.MAX_VALUE)) ? "inf" : current);
        }
        System.out.println("\n-----------------------FINISH");

        LinkedList<V> path = reconstructPath(g, vOrig, vDest, pathKeys);

        if (path.isEmpty() || dist[g.key(vDest)].equals(Integer.valueOf(Integer.MAX_VALUE))) {
            return null; // No path found
        }

        shortPath.addAll(path);
        return dist[g.key(vDest)];
    }


    private static <V> LinkedList<V> reconstructPath(Graph<V, ?> g, V vOrig, V vDest, V[] pathKeys) {
        LinkedList<V> path = new LinkedList<>();
        V current = vDest;

        while (current != null && !current.equals(vOrig)) {
            path.addFirst(current);
            current = pathKeys[g.key(current)];
        }

        if (current == null) {
            return new LinkedList<>(); // Return an empty path if no valid path exists
        }

        return path;
    }


    /**
     * Shortest-path between a vertex and all other vertices
     *
     * @param g     graph
     * @param vOrig start vertex
     * @param ce    comparator between elements of type E
     * @param sum   sum two elements of type E
     * @param zero  neutral element of the sum in elements of type E
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig,
                                               Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                               ArrayList<LinkedList<V>> paths, ArrayList<E> dists) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest,
                                       V[] pathKeys, LinkedList<V> path) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Calculates the minimum distance graph using Floyd-Warshall
     *
     * @param g   initial graph
     * @param ce  comparator between elements of type E
     * @param sum sum two elements of type E
     * @return the minimum distance graph
     */
    @SuppressWarnings("unchecked")
    public static <V, E> MatrixGraph<V, E> minDistGraph(Graph<V, E> g, Comparator<E> ce, BinaryOperator<E> sum) {

        MatrixGraph<V, E> minGraph = new MatrixGraph<>(g.isDirected(), g.numVertices());
        List<V> vertices = g.vertices();

        for (V vOrig : vertices) {
            minGraph.addVertex(vOrig);
        }

        E[][] dist = (E[][]) new Object[g.numVertices()][g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++) {
            for (int j = 0; j < g.numVertices(); j++) {
                if (i == j) {
                    dist[i][j] = null;
                } else {
                    Edge<V, E> edge = g.edge(vertices.get(i), vertices.get(j));
                    dist[i][j] = edge != null ? edge.getWeight() : null;
                }
            }
        }


        // T[i,j]^k = T[i,j]^(k-1) E ( T[i,k]^(K-1) and T[k,j]^(k-1) )

        for (int k = 0; k < g.numVertices(); k++) {
            for (int i = 0; i < g.numVertices(); i++) {
                if (k != i && dist[i][k] != null) {
                    for (int j = 0; j < g.numVertices(); j++) {
                        if (i != j && k != j && dist[k][j] != null) {
                            E newDist = sum.apply(dist[i][k], dist[k][j]);
                            if (dist[i][j] == null || ce.compare(newDist, dist[i][j]) < 0) {
                                dist[i][j] = newDist;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < g.numVertices(); i++) {
            for (int j = 0; j < g.numVertices(); j++) {
                if (dist[i][j] != null) {
                    minGraph.addEdge(vertices.get(i), vertices.get(j), dist[i][j]);
                }
            }
        }

        return minGraph;
    }

}