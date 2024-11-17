package pt.ipp.isep.dei.esoft.project.domain;


import java.util.*;

public class BOO {


    private final TreeMap<Integer, List<ID>> boo;


    public BOO() {
        boo = treeMapFilling();
    }

    private TreeMap<Integer, List<ID>> treeMapFilling() {
        TreeMap<Integer, List<ID>> boo = new TreeMap<>(Comparator.reverseOrder());
        QualityChecks qc = new QualityChecks();
        PriorityQueue<Map<Integer, List<ID>>> pq = qc.fillOperationsPriorityQueue();

        while (!pq.isEmpty()) {
            Map<Integer, List<ID>> entry = pq.poll();
            for (Map.Entry<Integer, List<ID>> e : entry.entrySet()) {
                Integer priority = e.getKey();
                List<ID> ids = e.getValue();
                boo.computeIfAbsent(priority, k -> new ArrayList<>()).addAll(ids);
            }
        }

        return boo;
    }

}
