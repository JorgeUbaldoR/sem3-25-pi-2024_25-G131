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
 * @param <V> Vertex value type
 * @param <E> Edge value type
 * @author DEI-ESINF
 */
public class MapGraph<V, E> extends CommonGraph<V, E> {


    final private Map<V, MapVertex<V, E>> mapVertices;  // all the Vertices of the graph

    // Constructs an empty graph (either undirected or directed)
    public MapGraph(boolean directed) {
        super(directed);
        mapVertices = new LinkedHashMap<>();
    }

    public MapGraph(Graph<V,E> g) {
        this(g.isDirected());
        copy(g, this);
    }

    @Override
    public boolean validVertex(V vert) {
        return (mapVertices.get(vert) != null);
    }

    @Override
    public Collection<V> adjVertices(V vert) {
        if(!validVertex(vert)) return null;

        return mapVertices.get(vert).getAllAdjVerts();
    }

    @Override
    public Collection<Edge<V, E>> edges() {

        ArrayList<Edge<V, E>> le = new ArrayList<>(numEdges);

        for (MapVertex<V, E> mv : mapVertices.values())
            le.addAll(mv.getAllOutEdges());

        return le;
    }

    @Override
    public Edge<V, E> edge(V vOrig, V vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest))
            return null;

        MapVertex<V, E> mv = mapVertices.get(vOrig);

        return mv.getEdge(vDest);
    }

    @Override
    public Edge<V, E> edge(int vOrigKey, int vDestKey) {
        V vOrig = vertex(vOrigKey);
        V vDest = vertex(vDestKey);

        return edge(vOrig, vDest);
    }

    @Override
    public int outDegree(V vert) {

        if (!validVertex(vert))
            return -1;

        MapVertex<V, E> mv = mapVertices.get(vert);

        return mv.numAdjVerts();
    }

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

    @Override
    public Collection<Edge<V, E>> outgoingEdges(V vert) {

        if (!validVertex(vert))
            return null;

        MapVertex<V, E> mv = mapVertices.get(vert);

        return mv.getAllOutEdges();
    }

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

    //Returns a clone of the graph
    @Override
    public MapGraph<V, E> clone() {

        MapGraph<V, E> g = new MapGraph<>(this.isDirected);

        copy(this,g);

        return g;
    }


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