package pt.ipp.isep.dei.esoft.project.domain.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import pt.ipp.isep.dei.esoft.project.domain.Activity;
import pt.ipp.isep.dei.esoft.project.domain.Graph.map.MapGraph;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    private static final int START_OF_PREDECESSORS = 6;

    public static MapGraph<Activity, Double> readCSV(String filePath) {
        MapGraph<Activity, Double> graph = new MapGraph<>(true);

        try {
            Reader reader = new FileReader(filePath);

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("act_id", "act_descr", "duration", "duration_unit", "cost", "cost_unit", "prev_act_id1", "prev_act_id2")
                    .withSkipHeaderRecord()
                    .parse(reader);

            Map<ID, Activity> activityMap = new HashMap<>();

            for (CSVRecord record : records) {
                String id = record.get("act_id");
                String description = record.get("act_descr");
                String duration = (record.get("duration")); //
                String durationUnit = record.get("duration_unit");
                String cost = (record.get("cost")); //
                String costUnit = record.get("cost_unit");
                List<ID> predecessors = new ArrayList<>();

                validateParametersUnits(id, duration, cost);


                for (int i = START_OF_PREDECESSORS; i < record.size(); i++) { // Assuming dependencies start from column 6
                    if (!record.get(i).isEmpty()) {
                        String idPredecessor = record.get(i);
                        checkString(idPredecessor,"PREDECESSOR");
                        checkConversionAndNegativeDouble(idPredecessor,"PREDECESSOR");
                        predecessors.add(new ID(Integer.parseInt(idPredecessor), TypeID.ACTIVITY));
                    }
                }

                Activity activity = new Activity(new ID(Integer.parseInt(id.trim()), TypeID.ACTIVITY), description, Double.parseDouble(duration.trim()), durationUnit, Double.parseDouble(cost.trim()), costUnit, predecessors);
                activityMap.put(activity.getId(), activity);
                graph.addVertex(activity);

                for (ID predID : predecessors) {
                    Activity predecessor = activityMap.get(predID);
                    if (predecessor != null) {
                        graph.addEdge(predecessor, activity, predecessor.getDuration());
                    }
                }
            }
            return graph;

        } catch (Exception e) {
            throw new IllegalArgumentException("Error processing CSV file: " + e.getMessage());
        }
    }

    private static void validateParametersUnits(String id, String duration, String cost) {
        checkString(id,"ID");
        checkString(duration,"DURATION");
        checkString(cost,"COST");
        checkConverssion(id, duration, cost);
    }

    private static void checkString(String param, String token) {
        if (!validateString(param)) {
            throw new IllegalArgumentException("Invalid "+token+" - make sure is not Empty or Null");
        }
    }

    private static void checkConverssion(String id, String duration, String cost) {
        checkConversionAndNegativeInt(id,"ID");
        checkConversionAndNegativeDouble(duration,"DURATION");
        checkConversionAndNegativeDouble(cost,"COST");
    }

    private static void checkConversionAndNegativeInt(String param,String token){
        try {
            if(Integer.parseInt(param) < 0){
                sendNegativeError(token,param);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid "+token+" -> [" + param + "]  is not a number.");
        }
    }

    private static void checkConversionAndNegativeDouble(String param,String token){
        try {
            if(Double.parseDouble(param) < 0){
                sendNegativeError(token,param);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid "+token+" -> [" + param + "]  is not a number.");
        }
    }

    private static void sendNegativeError(String token, String param) {
        throw new IllegalArgumentException("Invalid "+token+" -> [" + param + "]  cannot be negative.");
    }

    //-------------------------- String Validations ---------------------------------------------

    /**
     * Validates if a string is not null or empty.
     *
     * @param string The string to be validated.
     * @return true if the string is not null or empty, false otherwise.
     */
    public static boolean validateString(String string) {
        return (string != null && !string.trim().isEmpty());
    }
}
