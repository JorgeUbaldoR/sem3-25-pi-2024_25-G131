package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.ItemController;

import java.util.*;

public class BOO {

    private Map<Integer, List<ID>> itemsList;
    private TreeMap<Integer, Queue<Map<Item, Float>>> boo;


    public BOO() {
        itemsList = new LinkedHashMap<>();
        boo = new TreeMap<>(Collections.reverseOrder());
        associateItemsWithBoo();

    }

    public Map<Integer, List<ID>> getTreeMap() {
        itemsList = initializeTreeMap();
        return itemsList;
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
            Map<Item, Float> associatedItems = new LinkedHashMap<>();
            Queue<Map<Item, Float>> itemQueue = new LinkedList<>();
            Integer height = entry.getKey();
            List<ID> idList = entry.getValue();

            for (ID id : idList) {
                for (Map.Entry<ID, Item> itemEntry : itemList.entrySet()) {
                    Item item = itemEntry.getValue();
                    if (item.hasOperationWithID(id)) {
                        float quantity = item.getQuantity();

                        if (!associatedItems.containsKey(item)) {
                            associatedItems.put(item, quantity);
                        }
                    }
                }
            }

            itemQueue.add(new LinkedHashMap<>(associatedItems));
            boo.put(height, itemQueue);
        }
    }


    public void printItemsAtHeight() {
        System.out.println("Items grouped by height (Priority Levels):");
        System.out.println("=========================================");
        for (Map.Entry<Integer, Queue<Map<Item, Float>>> entry : boo.entrySet()) {
            Integer height = entry.getKey();
            Queue<Map<Item, Float>> itemQueue = entry.getValue();

            System.out.printf("Height (Priority Level): %d%n", height);

            for (Map<Item, Float> itemMap : itemQueue) {
                for (Map.Entry<Item, Float> itemEntry : itemMap.entrySet()) {
                    Item item = itemEntry.getKey();
                    Float quantity = itemEntry.getValue();
                    System.out.printf("  -> Item: %s, Quantity: %.2f%n", item, quantity);
                }
            }
        }

        System.out.println("=========================================");
    }

    public TreeMap<Integer, Queue<Map<Item, Float>>> getBoo() {
        return boo;
    }
}