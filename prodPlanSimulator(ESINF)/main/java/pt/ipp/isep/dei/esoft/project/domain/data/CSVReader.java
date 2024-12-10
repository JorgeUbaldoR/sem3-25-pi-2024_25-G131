package pt.ipp.isep.dei.esoft.project.domain.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import pt.ipp.isep.dei.esoft.project.domain.Activity;
import pt.ipp.isep.dei.esoft.project.domain.Graph.map.MapGraph;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.io.FileReader;
import java.io.Reader;
import java.util.*;

public class CSVReader {
    private static final int START_OF_PREDECESSORS = 6;

    public static MapGraph<Activity, Double> readCSV(String filePath) {
        MapGraph<Activity, Double> graph = new MapGraph<>(true);
        Set<String> uniqueIds = new HashSet<>();

        try (Reader reader = new FileReader(filePath)){
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
                List<ID> predecessors = new LinkedList<>();

                long row = record.getRecordNumber();
                validateParametersUnits(id, duration, cost,row);

                if (!uniqueIds.add(id.trim())) {
                    throw new IllegalArgumentException("Error in row " + row + ": " +"Duplicate activity ID found: " + id);
                }

                for (int i = START_OF_PREDECESSORS; i < record.size(); i++) { // Assuming dependencies start from column 6
                    if (!record.get(i).isEmpty()) {
                        String idPredecessor = record.get(i);
                        checkString(idPredecessor,"PREDECESSOR",row);
                        checkConversionAndNegativeDouble(idPredecessor,"PREDECESSOR",row);
                        predecessors.add(new ID(Integer.parseInt(idPredecessor), TypeID.ACTIVITY));
                    }
                }

                Activity activity = new Activity(new ID(Integer.parseInt(id.trim()), TypeID.ACTIVITY), description, Double.parseDouble(duration.trim()), durationUnit, Double.parseDouble(cost.trim()), costUnit, predecessors);
                activityMap.put(activity.getId(), activity);
                graph.addVertex(activity);
            }

            for(Activity activity : graph.vertices()){
                for(ID idPred : activity.getPredecessors()){
                    Activity predAct = activityMap.get(idPred);

                    if(predAct == null){
                        throw new IllegalArgumentException("ERROR IN FILE: For activity -> ["+activity.getId()+"] whose predecessor is -> ["+idPred+"] was not found in vertex of the graph (make sure is defined in file).");
                    }
                    graph.addEdge(predAct,activity, predAct.getDuration());

                }
            }
            return graph;

        } catch (Exception e) {
            throw new IllegalArgumentException("Error processing CSV file: " + e.getMessage());
        }
    }

    private static void validateParametersUnits(String id, String duration, String cost,long row) {
        checkString(id,"ID",row);
        checkString(duration,"DURATION",row);
        checkString(cost,"COST",row);
        checkConverssion(id, duration, cost,row);
    }

    private static void checkString(String param, String token,long row) {
        if (!validateString(param)) {
            throw new IllegalArgumentException("Error in row " + row + ": " +"Invalid "+token+" - make sure is not Empty or Null");
        }
    }

    private static void checkConverssion(String id, String duration, String cost,long row) {
        checkConversionAndNegativeInt(id,"ID",row);
        checkConversionAndNegativeDouble(duration,"DURATION",row);
        checkConversionAndNegativeDouble(cost,"COST",row);
    }

    private static void checkConversionAndNegativeInt(String param,String token,long row){
        try {
            if(Integer.parseInt(param) < 0){
                sendNegativeError(token,param);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error in row " + row + ": " +"Invalid "+token+" -> [" + param + "]  is not a number.");
        }
    }

    private static void checkConversionAndNegativeDouble(String param,String token, long row){
        try {
            if(Double.parseDouble(param) < 0){
                sendNegativeError(token,param);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error in row " + row + ": " +"Invalid "+token+" -> [" + param + "]  is not a number.");
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
