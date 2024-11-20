package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.ItemController;

import java.util.*;

public class BOO {

    private TreeMap<Integer, List<ID>> boo;
    private Map<Integer, Queue<Item>> itemsAtHeight;


    public BOO() {
        boo = new TreeMap<>(Collections.reverseOrder());
        itemsAtHeight = new TreeMap<>(Collections.reverseOrder());
        associateItemsWithBoo();

    }

    public TreeMap<Integer, List<ID>> getTreeMap() {
        boo = initializeTreeMap();
        return boo;
    }

    private TreeMap<Integer, List<ID>> initializeTreeMap() {
        TreeMap<Integer, List<ID>> treeMap = new TreeMap<>(Collections.reverseOrder());
        QualityChecks qc = new QualityChecks();
        PriorityQueue<Map<Integer, List<ID>>> pq = qc.fillOperationsPriorityQueue();

        while (!pq.isEmpty()) {
            Map<Integer, List<ID>> entry = pq.poll();
            for (Map.Entry<Integer, List<ID>> e : entry.entrySet()) {
                Integer priority = e.getKey();
                List<ID> ids = e.getValue();
                treeMap.computeIfAbsent(priority, k -> new ArrayList<>()).addAll(ids);
            }
        }
        return treeMap;
    }

    public void associateItemsWithBoo() {
        ItemController itemController = new ItemController();
        TreeMap<Integer, List<ID>> treeMap = initializeTreeMap();
        Map<ID, Item> itemList = itemController.getItemList();

        for (Map.Entry<Integer, List<ID>> entry : treeMap.entrySet()) {

            Queue<Item> associatedItems = new LinkedList<>();
            Integer height = entry.getKey();
            List<ID> idList = entry.getValue();

            for (Map.Entry<ID, Item> itemEntry : itemList.entrySet()) {
                Item item = itemEntry.getValue();
                for (ID id : idList) {
                    if (item.hasOperationWithID(id)) {
                        associatedItems.add(item);
                    }

                }
                itemsAtHeight.put(height, associatedItems);
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

    public void printItemsAtHeight() {
        System.out.println("Items grouped by height (Priority Levels):");
        System.out.println("=========================================");

        for (Map.Entry<Integer, Queue<Item>> entry : itemsAtHeight.entrySet()) {
            Integer height = entry.getKey();
            Queue<Item> items = entry.getValue();

            System.out.printf("Height (Priority Level): %d%n", height);
            for (Item item : items) {
                System.out.printf("  -> %s%n", item);
            }
        }

        System.out.println("=========================================");
    }


    public List<Item> getItemsAtHeightList() {
        List<Item> items = new ArrayList<>();
        for (Map.Entry<Integer, Queue<Item>> entry : itemsAtHeight.entrySet()) {
            Queue<Item> queue = entry.getValue();
            while (!queue.isEmpty()) {
                Item item = queue.poll();
                if (!items.contains(item)) {
                    items.add(item);
                }
            }
        }
        return items;
    }

}