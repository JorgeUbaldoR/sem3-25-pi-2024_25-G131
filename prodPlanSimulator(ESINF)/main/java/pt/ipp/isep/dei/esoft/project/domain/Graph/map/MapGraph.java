package pt.ipp.isep.dei.esoft.project.domain.Graph.map;

import pt.ipp.isep.dei.esoft.project.domain.Activity;
import pt.ipp.isep.dei.esoft.project.domain.Graph.CommonGraph;
import pt.ipp.isep.dei.esoft.project.domain.Graph.Edge;
import pt.ipp.isep.dei.esoft.project.domain.Graph.Graph;
import pt.ipp.isep.dei.esoft.project.domain.ID;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;


/**
 * Represents a graph implemented using a map-based adjacency list.
 * Supports directed and undirected graphs.
 *
 * @param <V> Vertex value type
 * @param <E> Edge value type
 */
public class MapGraph<V, E> extends CommonGraph<V, E> {


    final private Map<V, MapVertex<V, E>> mapVertices;  // all the Vertices of the graph

    /**
     * Constructs an empty graph.
     *
     * @param directed Indicates whether the graph is directed.
     * Time Complexity: O(1)
     */
    public MapGraph(boolean directed) {
        super(directed);
        mapVertices = new LinkedHashMap<>();
    }

    /**
     * Constructs a graph by copying the given graph.
     *
     * @param g The graph to copy.
     * Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
     */
    public MapGraph(Graph<V,E> g) {
        this(g.isDirected());
        copy(g, this);
    }

    /**
     * Checks if a vertex is valid (exists in the graph).
     *
     * @param vert The vertex to validate.
     * @return True if the vertex exists, false otherwise.
     * Time Complexity: O(1)
     */
    @Override
    public boolean validVertex(V vert) {
        return (mapVertices.get(vert) != null);
    }


    /**
     * Returns the adjacent vertices of a given vertex.
     *
     * @param vert The vertex whose adjacent vertices are needed.
     * @return A collection of adjacent vertices, or null if the vertex is invalid.
     * Time Complexity: O(1)
     */
    @Override
    public Collection<V> adjVertices(V vert) {
        if(!validVertex(vert)) return null;

        return mapVertices.get(vert).getAllAdjVerts();
    }

    /**
     * Returns all edges of the graph.
     *
     * @return A collection of all edges in the graph.
     * Time Complexity: O(V + E)
     */
    @Override
    public Collection<Edge<V, E>> edges() {

        ArrayList<Edge<V, E>> le = new ArrayList<>(numEdges);

        for (MapVertex<V, E> mv : mapVertices.values())
            le.addAll(mv.getAllOutEdges());

        return le;
    }

    /**
     * Returns the edge between two vertices.
     *
     * @param vOrig The origin vertex.
     * @param vDest The destination vertex.
     * @return The edge connecting the vertices, or null if it doesn't exist.
     * Time Complexity: O(1)
     */
    @Override
    public Edge<V, E> edge(V vOrig, V vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest))
            return null;

        MapVertex<V, E> mv = mapVertices.get(vOrig);

        return mv.getEdge(vDest);
    }

    /**
     * Returns the edge between two vertices identified by their keys.
     *
     * @param vOrigKey The key of the origin vertex.
     * @param vDestKey The key of the destination vertex.
     * @return The edge connecting the vertices, or null if it doesn't exist.
     * Time Complexity: O(1) (after mapping keys to vertices)
     */
    @Override
    public Edge<V, E> edge(int vOrigKey, int vDestKey) {
        V vOrig = vertex(vOrigKey);
        V vDest = vertex(vDestKey);

        return edge(vOrig, vDest);
    }


    /**
     * Returns the out-degree (number of outgoing edges) of a vertex.
     *
     * @param vert The vertex whose out-degree is to be calculated.
     * @return The out-degree of the vertex, or -1 if the vertex is invalid.
     * Time Complexity: O(1)
     */
    @Override
    public int outDegree(V vert) {

        if (!validVertex(vert))
            return -1;

        MapVertex<V, E> mv = mapVertices.get(vert);

        return mv.numAdjVerts();
    }

    /**
     * Returns the in-degree (number of incoming edges) of a vertex.
     *
     * @param vert The vertex whose in-degree is to be calculated.
     * @return The in-degree of the vertex, or -1 if the vertex is invalid.
     * Time Complexity: O(V)
     */
    @Override
    public int inDegree(V vert) {

        if (!validVertex(vert))
            return -1;

        int degree = 0;
        for (V otherVert : mapVertices.keySet())
            if (edge(otherVert, vert) != null)
                degree++;

        return degree;
    }

    /**
     * Returns all outgoing edges of a vertex.
     *
     * @param vert The vertex whose outgoing edges are needed.
     * @return A collection of outgoing edges, or null if the vertex is invalid.
     * Time Complexity: O(1)
     */
    @Override
    public Collection<Edge<V, E>> outgoingEdges(V vert) {

        if (!validVertex(vert))
            return null;

        MapVertex<V, E> mv = mapVertices.get(vert);

        return mv.getAllOutEdges();
    }

    /**
     * Returns all incoming edges of a vertex.
     *
     * @param vert The vertex whose incoming edges are needed.
     * @return A collection of incoming edges, or null if the vertex is invalid.
     * Time Complexity: O(V)
     */
    @Override
    public Collection<Edge<V, E>> incomingEdges(V vert) {
        ArrayList<Edge<V, E>> incomingEdges = new ArrayList<>();

        if(!validVertex(vert))
            return null;

        for (V otherVert : mapVertices.keySet()){
            if(edge(otherVert, vert) != null){
                incomingEdges.add(edge(otherVert, vert));}
        }
        return incomingEdges;
    }


    /**
     * Adds a new vertex to the graph.
     *
     * @param vert The vertex to add.
     * @return True if the vertex was added, false if it already exists.
     * @throws RuntimeException if the vertex is null.
     * Time Complexity: O(1)
     */
    @Override
    public boolean addVertex(V vert) {

        if (vert == null) throw new RuntimeException("Vertices cannot be null!");
        if (validVertex(vert))
            return false;

        MapVertex<V, E> mv = new MapVertex<>(vert);
        vertices.add(vert);
        mapVertices.put(vert, mv);
        numVerts++;

        return true;
    }



    public Map<V, MapVertex<V, E>> getMapVertices() {
        return mapVertices;
    }

    /**
     * Adds a new edge to the graph.
     *
     * @param vOrig  The origin vertex.
     * @param vDest  The destination vertex.
     * @param weight The weight of the edge.
     * @return True if the edge was added, false if it already exists.
     * @throws RuntimeException if any vertex is null.
     * Time Complexity: O(1) (amortized for vertex addition)
     */
    @Override
    public boolean addEdge(V vOrig, V vDest, E weight) {

        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");
        if (edge(vOrig, vDest) != null)
            return false;

        if (!validVertex(vOrig))
            addVertex(vOrig);

        if (!validVertex(vDest))
            addVertex(vDest);

        MapVertex<V, E> mvo = mapVertices.get(vOrig);
        MapVertex<V, E> mvd = mapVertices.get(vDest);

        Edge<V, E> newEdge = new Edge<>(mvo.getElement(), mvd.getElement(), weight);
        mvo.addAdjVert(mvd.getElement(), newEdge);
        numEdges++;

        //if graph is not direct insert other edge in the opposite direction 
        if (!isDirected)
            // if vDest different vOrig
            if (edge(vDest, vOrig) == null) {
                Edge<V, E> otherEdge = new Edge<>( mvd.getElement(), mvo.getElement(), weight);
                mvd.addAdjVert(mvo.getElement(), otherEdge);
                numEdges++;
            }

        return true;
    }


    /**
     * Removes a vertex from the graph, along with all its associated edges.
     *
     * @param vert The vertex to remove.
     * @return True if the vertex was successfully removed, false if it does not exist.
     * @throws RuntimeException if the vertex is null.
     * Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
     */
    @Override
    public boolean removeVertex(V vert) {

        if (vert == null) throw new RuntimeException("Vertices cannot be null!");
        if (!validVertex(vert))
            return false;

        //remove all edges that point to vert
        for (Edge<V, E> edge : incomingEdges(vert)) {
            removeEdge(edge.getVOrig(), vert);
        }

        MapVertex<V, E> mv = mapVertices.get(vert);

        //The edges that live from vert are removed with the vertex
        numEdges -= mv.numAdjVerts();
        mapVertices.remove(vert);
        vertices.remove(vert);

        numVerts--;

        return true;
    }


    /**
     * Removes an edge between two vertices.
     *
     * @param vOrig The origin vertex.
     * @param vDest The destination vertex.
     * @return True if the edge was successfully removed, false if it does not exist.
     * @throws RuntimeException if any vertex is null.
     * Time Complexity: O(1) for directed graphs; O(1) + O(1) for undirected graphs.
     */
    @Override
    public boolean removeEdge(V vOrig, V vDest) {

        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");
        if (!validVertex(vOrig) || !validVertex(vDest))
            return false;

        Edge<V, E> edge = edge(vOrig, vDest);

        if (edge == null)
            return false;

        MapVertex<V, E> mvo = mapVertices.get(vOrig);

        mvo.remAdjVert(vDest);
        numEdges--;

        //if graph is not directed
        if (!isDirected) {
            edge = edge(vDest, vOrig);
            if (edge != null) {
                MapVertex<V, E> mvd = mapVertices.get(vDest);
                mvd.remAdjVert(vOrig);
                numEdges--;
            }
        }
        return true;
    }

    /**
     * Returns a deep copy (clone) of the current graph.
     *
     * @return A new instance of {@code MapGraph} that is a clone of the current graph.
     * Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
     */
    @Override
    public MapGraph<V, E> clone() {

        MapGraph<V, E> g = new MapGraph<>(this.isDirected);

        copy(this,g);

        return g;
    }



    /**
     * Returns a string representation of the graph.
     * Includes details about vertices, edges, and their connections, formatted for readability.
     *
     * @param id An identifier for the graph, displayed in the output.
     * @return A formatted string representation of the graph.
     * Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
     */
    public String toString(ID id) {
        StringBuilder sb = new StringBuilder();

        if (numVerts == 0) {
            sb.append("\nGraph not defined!!");
        } else {
            sb.append(ANSI_BRIGHT_BLACK+"\n╔═══════════════════════════╗\n"+ANSI_RESET);
            sb.append(ANSI_BRIGHT_WHITE+"     Graph Details: #").append(id).append(ANSI_RESET+ANSI_BRIGHT_BLACK+"\n╚═══════════════════════════╝\n"+ANSI_RESET)
                    .append(ANSI_BRIGHT_BLACK+" •"+ANSI_RESET+ " Directed: "+ANSI_BRIGHT_WHITE).append(isDirected).append(ANSI_RESET+"\n")
                    .append(ANSI_BRIGHT_BLACK+" •"+ANSI_RESET+ " Number of vertices: "+ANSI_BRIGHT_WHITE).append(numVerts).append(ANSI_RESET+"\n")
                    .append(ANSI_BRIGHT_BLACK+" •"+ANSI_RESET+ " Number of edges: "+ANSI_BRIGHT_WHITE).append(numEdges+ANSI_RESET).append(ANSI_BRIGHT_BLACK+"\n═════════════════════════════\n"+ANSI_RESET);

            sb.append("  Vertices and their edges:\n\n");
            int qtd = 1;
            for (MapVertex<V, E> mv : mapVertices.values()) {
                sb.append(ANSI_BRIGHT_BLACK+"(#").append(qtd).append(")"+ANSI_RESET).append(" 🔵 "+"Vertex ").append(mv.getElement()).append("\n");
                qtd++;

                if (mv.numAdjVerts() > 0) {
                    int len = mv.getAllAdjVerts().size();
                    for (Edge<V, E> edge : mv.getAllOutEdges()) {
                        if(edge.getVDest() instanceof Activity){
                            if(len == 1){
                                sb.append(ANSI_BRIGHT_BLACK+"                 └─>"+ANSI_RESET+" To: Vertex ["+ANSI_BRIGHT_WHITE).append(((Activity) edge.getVDest()).getId())
                                        .append(ANSI_RESET+"] | ⚖️"+" Edge Weight: ").append(edge.getWeight())
                                        .append("\n");
                            }else{
                                sb.append(ANSI_BRIGHT_BLACK+"                 ├─>"+ANSI_RESET+" To: Vertex ["+ANSI_BRIGHT_WHITE).append(((Activity) edge.getVDest()).getId())
                                        .append(ANSI_RESET+"] | ⚖️"+" Edge Weight: ").append(edge.getWeight())
                                        .append("\n");
                            }
                        }else{
                            sb.append(ANSI_BRIGHT_BLACK+"                 ├─>"+ANSI_RESET+" To: Vertex ["+ANSI_BRIGHT_WHITE).append(edge.getVDest())
                                    .append(ANSI_RESET+"] | ⚖️"+" Edge Weight: ").append(edge.getWeight())
                                    .append("\n");
                        }

                        len --;
                    }
                    sb.append("\n");
                } else {
                    sb.append(ANSI_BRIGHT_BLACK+"                 └─>"+ANSI_RESET+" To:"+ANSI_BRIGHT_YELLOW+" None outgoing edges.\n\n"+ANSI_RESET);
                }
            }
        }

        return sb.toString();
    }

}