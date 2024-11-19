package pt.ipp.isep.dei.esoft.project.domain;

import java.util.*;

public class BOO {

    private final TreeMap<Integer, List<ID>> boo;
    private boolean isInitialized;

    public BOO() {
        boo = new TreeMap<>(Collections.reverseOrder());
        isInitialized = false;
    }

    public TreeMap<Integer, List<ID>> getTreeMap() {
        if (!isInitialized) {
            initializeTreeMap();
            isInitialized = true;
        }
        return boo;
    }

    private void initializeTreeMap() {
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
    }

    public void printTreeMap(TreeMap<Integer, List<ID>> treeMap) {
        System.out.println("Operations sorted by priority (Highest to Lowest):");
        System.out.println("==================================================");

        for (Map.Entry<Integer, List<ID>> entry : treeMap.entrySet()) {
            Integer priority = entry.getKey();
            List<ID> ids = entry.getValue();

            System.out.printf("Priority: %d%n", priority);
            for (ID id : ids) {
                System.out.printf("  -> %s%n", id);
            }
        }

        System.out.println("==================================================");
    }
}