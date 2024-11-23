package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.ItemController;

import java.util.*;

public class ItemLevelProcessor {

    private final TreeMap<Integer, Queue<Map<Item, Float>>> tree;


    public ItemLevelProcessor() {
        tree = new TreeMap<>(Collections.reverseOrder());
        associateItemsWithTree();

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

    public void associateItemsWithTree() {
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
            tree.put(height, itemQueue);
        }
    }


    public TreeMap<Integer, Queue<Map<Item, Float>>> getTree() {
        return tree;
    }


}