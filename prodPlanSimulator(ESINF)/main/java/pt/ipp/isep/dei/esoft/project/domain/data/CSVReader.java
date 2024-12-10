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

    public static MapGraph<Activity,Double> readCSV(String filePath) throws Exception {
        MapGraph<Activity,Double> graph = new MapGraph<>(true);
        Reader reader = new FileReader(filePath);

        Iterable<CSVRecord> records = CSVFormat.DEFAULT
            .withHeader("act_id", "act_descr", "duration", "duration_unit", "cost", "cost_unit", "prev_act_id1", "prev_act_id2")
            .withSkipHeaderRecord()
            .parse(reader);

        Map<ID,Activity> activityMap = new HashMap<>();

        for (CSVRecord record : records) {
            String id = record.get("act_id");
            String description = record.get("act_descr");
            double duration = Double.parseDouble(record.get("duration"));
            String durationUnit = record.get("duration_unit");
            double cost = Double.parseDouble(record.get("cost"));
            String costUnit = record.get("cost_unit");
            List<ID> predecessors = new ArrayList<>();

            for (int i = START_OF_PREDECESSORS; i < record.size(); i++) { // Assuming dependencies start from column 6
                if (!record.get(i).isEmpty()) {
                    predecessors.add( new ID(Integer.parseInt(record.get(i)), TypeID.ACTIVITY) );
                }
            }

            Activity activity = new Activity(new ID(Integer.parseInt(id), TypeID.ACTIVITY), description, duration, durationUnit, cost, costUnit, predecessors);
            activityMap.put(activity.getId(),activity);
            graph.addVertex(activity);

            for(ID predID :predecessors){
                Activity predecessor = activityMap.get(predID);
                if(predecessor != null){
                    graph.addEdge(predecessor,activity,predecessor.getDuration());
                }
            }
        }
        return graph;
    }

}
