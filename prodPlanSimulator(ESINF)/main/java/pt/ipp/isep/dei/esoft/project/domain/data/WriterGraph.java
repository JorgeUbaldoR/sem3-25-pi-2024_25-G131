package pt.ipp.isep.dei.esoft.project.domain.data;

import pt.ipp.isep.dei.esoft.project.domain.Activity;
import pt.ipp.isep.dei.esoft.project.domain.Graph.Edge;
import pt.ipp.isep.dei.esoft.project.domain.Graph.map.MapGraph;
import pt.ipp.isep.dei.esoft.project.domain.Graph.map.MapVertex;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;


/**
 * This class is responsible for writing the graph data to a PlantUML format (.puml) file.
 * It generates a visual representation of the graph using edges and vertices.
 * The graph is written as either a directed or undirected graph, depending on the input.
 */
public class WriterGraph {
    static private PrintWriter treePrintWriter;
    private static final int START_ID_DEFAULT = 7777;
    private static final int FINISH_ID_DEFAULT = 7778;


    /**
     * Constructs a new instance of WriterGraph.
     * It does not require any parameters as the methods are static.
     */
    public WriterGraph() {}

    /**
     * Writes the graph in PlantUML format to a file.
     * The generated file will visualize the graph as a directed or undirected graph.
     * It includes edges with their respective weights and duration units.
     *
     * @param idGraph The identifier for the graph, used in naming the output file.
     * @param graph The graph to be written to the file.
     */
    public static void writePETRGraph(String idGraph, MapGraph<Activity, Double> graph) {
        StringBuilder fileName = new StringBuilder();
        fileName.append("MapGraph_").append(idGraph);

        // Determine if the graph is directed or undirected and append the appropriate suffix
        if (graph.isDirected()) {
            fileName.append("_Directed").append(".puml");
        } else {
            fileName.append("_NotDirected").append(".puml");
        }

        try {
            // Create a PrintWriter to write the PlantUML file
            treePrintWriter = new PrintWriter("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/output/" + fileName);
            treePrintWriter.println("@startuml");

            // If the graph is directed, use the "digraph" keyword
            if (graph.isDirected()) {
                treePrintWriter.println("digraph Diagram {");
                for (MapVertex<Activity, Double> mv : graph.getMapVertices().values()) {
                    for (Edge<Activity, Double> edge : mv.getAllOutEdges()) {
                        if(edge.getVOrig().getId().getSerial() != START_ID_DEFAULT && edge.getVDest().getId().getSerial() != FINISH_ID_DEFAULT) {
                            treePrintWriter.printf("\"%s\" -> \"%s\" [label = \"%.1f (%s)    \"]%n",
                                    edge.getVOrig().getId(),
                                    edge.getVDest().getId(),
                                    edge.getWeight(),
                                    edge.getVOrig().getDurationUnit());
                        }else if(edge.getVOrig().getId().getSerial() != START_ID_DEFAULT){
                            treePrintWriter.printf("\"%s\" -> \"%s\"%n",
                                    edge.getVOrig().getId(),
                                    edge.getVDest().getDescription());

                        }else{
                            treePrintWriter.printf("\"%s\" -> \"%s\"%n",
                                    edge.getVOrig().getDescription(),
                                    edge.getVDest().getId());
                        }

                    }
                }
            } else {
                // If the graph is undirected, use the "graph" keyword
                treePrintWriter.println("graph Diagram {");
                Set<String> processedEdges = new HashSet<>(); // To track unique edges
                for (MapVertex<Activity, Double> mv : graph.getMapVertices().values()) {
                    for (Edge<Activity, Double> edge : mv.getAllOutEdges()) {
                        // Create a unique representation of the edge
                        String edgeKey = createEdgeKey(edge.getVOrig().getId().toString(), edge.getVDest().getId().toString());
                        if (!processedEdges.contains(edgeKey)) {
                            if(edge.getVOrig().getId().getSerial() != START_ID_DEFAULT && edge.getVDest().getId().getSerial() != FINISH_ID_DEFAULT) {
                                processedEdges.add(edgeKey);
                                treePrintWriter.printf("\"%s\" -- \"%s\" [label = \"%.1f (%s)    \"]%n",
                                        edge.getVOrig().getId(),
                                        edge.getVDest().getId(),
                                        edge.getWeight(),
                                        edge.getVOrig().getDurationUnit());
                            }else if(edge.getVOrig().getId().getSerial() != START_ID_DEFAULT){
                                treePrintWriter.printf("\"%s\" -- \"%s\"%n",
                                        edge.getVOrig().getId(),
                                        edge.getVDest().getDescription());

                            }else{
                                treePrintWriter.printf("\"%s\" -- \"%s\"%n",
                                        edge.getVOrig().getDescription(),
                                        edge.getVDest().getId());
                            }
                        }
                    }
                }
            }

            // End the PlantUML diagram
            treePrintWriter.printf("}%n");
            treePrintWriter.println("@enduml");
            treePrintWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File write error: " + exception.getMessage());
        }
    }

    /**
     * Helper method to create a unique key for an edge.
     * This ensures that undirected edges are represented only once, regardless of the order of the vertices.
     *
     * @param id1 The ID of the first vertex.
     * @param id2 The ID of the second vertex.
     * @return A unique string representing the edge between the two vertices.
     */
    private static String createEdgeKey(String id1, String id2) {
        // Ensure the key is the same regardless of order
        return id1.compareTo(id2) < 0 ? id1 + "-" + id2 : id2 + "-" + id1;
    }
}

