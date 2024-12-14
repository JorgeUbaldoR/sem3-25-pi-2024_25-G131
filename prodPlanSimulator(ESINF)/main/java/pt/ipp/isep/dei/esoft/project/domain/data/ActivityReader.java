package pt.ipp.isep.dei.esoft.project.domain.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import pt.ipp.isep.dei.esoft.project.domain.Activity;
import pt.ipp.isep.dei.esoft.project.domain.Graph.Algorithms;
import pt.ipp.isep.dei.esoft.project.domain.Graph.map.MapGraph;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.io.FileReader;
import java.io.Reader;
import java.util.*;

public class ActivityReader {
    private static final int START_OF_PREDECESSORS = 5;  // Index for the 'predecessors' column in the new format

    public static MapGraph<Activity, Double> readCSV(String filePath, boolean isDirected) {
        MapGraph<Activity, Double> graph = new MapGraph<>(isDirected);
        Set<String> uniqueIds = new HashSet<>();

        try (Reader reader = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("ActivKey", "descr", "duration", "duration-unit", "tot-cost", "predecessors")
                    .withSkipHeaderRecord()
                    .parse(reader);

            Map<ID, Activity> activityMap = new HashMap<>();

            for (CSVRecord record : records) {
                String id = record.get("ActivKey");
                String description = record.get("descr");
                String duration = record.get("duration");
                String durationUnit = record.get("duration-unit");
                String cost = record.get("tot-cost");
                String predecessors = record.get("predecessors");

                List<ID> predecessorIds = new LinkedList<>();
                long row = record.getRecordNumber();
                int idValid = validateParametersUnits(id, duration, cost, row);

                if (!uniqueIds.add(id.trim())) {
                    throw new IllegalArgumentException("Error in row " + row + ": Duplicate activity ID found: " + id);
                }

                // Parse the comma-separated list of predecessor IDs
                if (!predecessors.isEmpty()) {
                    String[] predecessorList = predecessors.split(",");
                    for (String pred : predecessorList) {
                        String validID = getFinalID(pred);
                        checkString(validID, "PREDECESSOR", row);
                        checkConversionAndNegativeInt(validID, "PREDECESSOR", row);
                        predecessorIds.add(new ID(Integer.parseInt(validID), TypeID.ACTIVITY));
                    }
                }

                // Create the Activity object
                Activity activity = new Activity(
                        new ID(idValid, TypeID.ACTIVITY),
                        description,
                        Double.parseDouble(duration.trim()),
                        durationUnit,
                        Double.parseDouble(cost.trim()),
                        "unit", // Assuming cost doesn't have a unit; adjust as necessary
                        predecessorIds
                );

                activityMap.put(activity.getId(), activity);
                graph.addVertex(activity);
            }

            // Add edges for dependencies
            for (Activity activity : graph.vertices()) {
                for (ID predId : activity.getPredecessors()) {
                    Activity predAct = activityMap.get(predId);
                    if (predAct == null) {
                        throw new IllegalArgumentException("ERROR IN FILE: For activity [" + activity.getId() +
                                "] whose predecessor is [" + predId + "] was not found in vertex of the graph.");
                    }
                    graph.addEdge(predAct, activity, predAct.getDuration());
                }
            }

            // Check for circular dependencies
            if (Algorithms.hasCircularDependencies(graph)) {
                throw new IllegalArgumentException("ERROR IN FILE: Graph has Circular Dependencies.");
            }

            return graph;

        } catch (Exception e) {
            throw new IllegalArgumentException("Error processing CSV file: " + e.getMessage());
        }
    }

    private static String getFinalID(String id) {
        StringBuilder num = new StringBuilder();
        id.chars().forEach(c -> {
            if (c >= '0' && c <= '9') {
                num.append((char)c);
            }
        });
        return num.toString();
    }

    //-------------------------- Validations Input ---------------------------------------------

    public static boolean validateString(String string) {
        return (string != null && !string.trim().isEmpty());
    }

    private static int validateParametersUnits(String id, String duration, String cost, long row) {
        checkString(id, "ID", row);
        checkString(duration, "DURATION", row);
        checkString(cost, "COST", row);
        return checkConversion(id, duration, cost, row);
    }

    private static void checkString(String param, String token, long row) {
        if (!validateString(param)) {
            throw new IllegalArgumentException("Error in row " + row + ": Invalid " + token + " - make sure it is not empty or null");
        }
    }

    private static int checkConversion(String id, String duration, String cost, long row) {
        String num = getFinalID(id);
        checkConversionAndNegativeInt(num.toString(), "ID", row);
        checkConversionAndNegativeDouble(duration, "DURATION", row);
        checkConversionAndNegativeDouble(cost, "COST", row);
        return Integer.parseInt(num.toString());
    }

    private static void checkConversionAndNegativeInt(String param, String token, long row) {
        try {
            if (Integer.parseInt(param) < 0) {
                sendNegativeError(token, param);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error in row " + row + ": Invalid " + token + " -> [" + param + "] is not a number.");
        }
    }

    private static void checkConversionAndNegativeDouble(String param, String token, long row) {
        try {
            if (Double.parseDouble(param) < 0) {
                sendNegativeError(token, param);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error in row " + row + ": Invalid " + token + " -> [" + param + "] is not a number.");
        }
    }

    private static void sendNegativeError(String token, String param) {
        throw new IllegalArgumentException("Invalid " + token + " -> [" + param + "] cannot be negative.");
    }
}
