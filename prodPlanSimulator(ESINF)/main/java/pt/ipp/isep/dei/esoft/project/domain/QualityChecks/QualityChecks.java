package pt.ipp.isep.dei.esoft.project.domain.QualityChecks;


import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;

import java.util.*;

public class QualityChecks {

    private final PriorityQueue<Map<Integer, List<ID>>> priorityQueue;

    public QualityChecks() {
        priorityQueue = fillOperationsPriorityQueue();
    }

    public PriorityQueue<Map<Integer, List<ID>>> fillOperationsPriorityQueue() {
        ProductionTree pdt = new ProductionTree();
        pdt.getInformations("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/boo.csv");
        Map<Integer, List<Node>> heightMap = pdt.getHeightMap();

        PriorityQueue<Map<Integer, List<ID>>> operationsPriorityQueue =
                new PriorityQueue<>((map1, map2) ->
                        Integer.compare(map2.keySet().iterator().next(), map1.keySet().iterator().next()));


        for (Map.Entry<Integer, List<Node>> entry : heightMap.entrySet()) {
            int height = entry.getKey();
            List<Node> nodesAtHeight = entry.getValue();

            Map<Integer, List<ID>> pqNode = new HashMap<>();
            List<ID> operations = new ArrayList<>();

            for (Node node : nodesAtHeight) {
                operations.add(node.getOperationID());
            }

            pqNode.put(height, operations);

            operationsPriorityQueue.add(pqNode);
        }

        return operationsPriorityQueue;
    }


}
