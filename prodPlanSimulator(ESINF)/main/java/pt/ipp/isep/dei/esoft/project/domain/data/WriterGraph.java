package pt.ipp.isep.dei.esoft.project.domain.data;

import pt.ipp.isep.dei.esoft.project.domain.Activity;
import pt.ipp.isep.dei.esoft.project.domain.Graph.Edge;
import pt.ipp.isep.dei.esoft.project.domain.Graph.map.MapGraph;
import pt.ipp.isep.dei.esoft.project.domain.Graph.map.MapVertex;
import pt.ipp.isep.dei.esoft.project.domain.ID;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class WriterGraph {
    static private PrintWriter treePrintWriter;

    public WriterGraph() {
    }

    public static void writePETRGraph(String idGraph, MapGraph<Activity, Double> graph) {
        StringBuilder fileName = new StringBuilder();
        fileName.append("MapGraph_").append(idGraph);
        if (graph.isDirected()) {
            fileName.append("_Directed").append(".puml");
        } else {
            fileName.append("_NotDirected").append(".puml");
        }
        try {
            treePrintWriter = new PrintWriter("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/output/" + fileName);
            treePrintWriter.println("@startuml");

            if (graph.isDirected()) {
                treePrintWriter.println("digraph Diagram {");
                for (MapVertex<Activity, Double> mv : graph.getMapVertices().values()) {
                    for (Edge<Activity, Double> edge : mv.getAllOutEdges()) {
                        treePrintWriter.printf("\"%s\" -> \"%s\" [label = \"%.1f (%s)\"]%n",
                                edge.getVOrig().getId(),
                                edge.getVDest().getId(),
                                edge.getWeight(),
                                edge.getVOrig().getDurationUnit());
                    }
                }
            } else {
                treePrintWriter.println("graph Diagram {");
                Set<String> processedEdges = new HashSet<>(); // To track unique edges
                for (MapVertex<Activity, Double> mv : graph.getMapVertices().values()) {
                    for (Edge<Activity, Double> edge : mv.getAllOutEdges()) {
                        // Create a unique representation of the edge
                        String edgeKey = createEdgeKey(edge.getVOrig().getId().toString(), edge.getVDest().getId().toString());
                        if (!processedEdges.contains(edgeKey)) {
                            processedEdges.add(edgeKey);
                            treePrintWriter.printf("\"%s\" -- \"%s\" [label = \"%.1f (%s)\"]%n",
                                    edge.getVOrig().getId(),
                                    edge.getVDest().getId(),
                                    edge.getWeight(),
                                    edge.getVOrig().getDurationUnit());
                        }
                    }
                }
            }

            treePrintWriter.printf("}%n");
            treePrintWriter.println("@enduml");
            treePrintWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File write error: " + exception.getMessage());
        }
    }

    // Helper method to create a unique key for an edge
    private static String createEdgeKey(String id1, String id2) {
        // Ensure the key is the same regardless of order
        return id1.compareTo(id2) < 0 ? id1 + "-" + id2 : id2 + "-" + id1;
    }

}
