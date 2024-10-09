package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.FileDataReader;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.io.IOException;
import java.util.*;

public class ItemRepository {
    private Map<ID, Item> itemList;

    public ItemRepository() {
        itemList = new HashMap<>();
        fillInventory();
    }

    //------------ Add Item ---------------
    public Optional<Item> addItem(Item item) {
        Optional<Item> newItem = Optional.empty();

        if (!this.itemList.containsKey(item.getItemID())) {
            this.itemList.put(item.getItemID(), item);
            newItem = Optional.of(item.clone());
        }

        return newItem;
    }

    private void fillInventory() {
        try {

            List<String[]> importedItems = FileDataReader.getItemsDetails();
            for (String[] importedItem : importedItems) {
                ID newId = new ID(Integer.parseInt(importedItem[0]), TypeID.MACHINE);
                Priority priority = Priority.fromString(importedItem[1]);
                for (int i = 2; i < importedItem.length; i++) {
                    Queue<Operation> operationQueue = new LinkedList<>();
                    Operation operation = new Operation(importedItem[i]);
                    operationQueue.add(operation);
                    itemList.put(newId, new Item(newId, priority, operationQueue));

                }
            }

        } catch (IOException e) {
            System.out.println("Error reading items details");
        }
    }


    //---------------------------------------------


    public List<Item> getItemList() {
        return new ArrayList<>(itemList.values());
    }
}
