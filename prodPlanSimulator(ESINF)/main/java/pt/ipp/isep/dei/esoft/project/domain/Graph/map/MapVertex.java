package pt.ipp.isep.dei.esoft.project.domain.Graph.map;

import pt.ipp.isep.dei.esoft.project.domain.Graph.Edge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @param <V>
 * @param <E>
 * @author DEI-ESINF
 */
public class MapVertex<V, E> {

    final private V element;                      // Vertex information
    final private Map<V, Edge<V, E>> outVerts;    // Adjacent vertices

    public MapVertex(V vert) {
        if (vert == null) throw new RuntimeException("Vertice information cannot be null!");
        element = vert;
        outVerts = new LinkedHashMap<>();
    }

    public V getElement() {
        return element;
    }

    public void addAdjVert(V vAdj, Edge<V, E> edge) {
        outVerts.put(vAdj, edge);
    }

    public void remAdjVert(V vAdj) {
        outVerts.remove(vAdj);
    }

    public Edge<V, E> getEdge(V vAdj) {
        return outVerts.get(vAdj);
    }

    public int numAdjVerts() {
        return outVerts.size();
    }

    public Collection<V> getAllAdjVerts() {
        return new ArrayList<>(outVerts.keySet());
    }

    public Collection<Edge<V, E>> getAllOutEdges() {
        return new ArrayList<>(outVerts.values());
    }

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
