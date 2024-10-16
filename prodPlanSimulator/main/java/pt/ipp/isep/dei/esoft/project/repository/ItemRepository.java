package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.FileDataReader;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.io.IOException;
import java.util.*;

/**
 * Repository class for managing Item objects.
 * This class allows adding items to the repository and retrieving them.
 */
public class ItemRepository {
    private Map<ID, Item> itemList;

    /**
     * Constructs a new ItemRepository instance.
     * Initializes an empty HashMap to store Item objects and fills the inventory
     * with items from a data source.
     */
    public ItemRepository() {
        itemList = new HashMap<>();
        fillInventory();
    }

    /**
     * Adds a new item to the repository if it does not already exist.
     *
     * @param item the Item to be added to the repository
     * @return an Optional containing a clone of the added item if successful,
     *         or an empty Optional if the item already exists
     */
    public Optional<Item> addItem(Item item) {
        Optional<Item> newItem = Optional.empty();

        if (!this.itemList.containsKey(item.getItemID())) {
            this.itemList.put(item.getItemID(), item);
            newItem = Optional.of(item.clone());
        }

        return newItem;
    }

    /**
     * Fills the inventory with items from a data source.
     * This method reads item details from a file and populates the item list.
     * It handles IOExceptions that may occur during file reading.
     */
    private void fillInventory() {
        try {

            List<String[]> importedItems = FileDataReader.getItemsDetails();
            for (String[] importedItem : importedItems) {
                ID newId = new ID(Integer.parseInt(importedItem[0]), TypeID.ITEM);
                Priority priority = Priority.fromString(importedItem[1]);
                Queue<Operation> operationQueue = new LinkedList<>();

                for (int i = 2; i < importedItem.length; i++) {
                    Operation operation = new Operation(importedItem[i]);
                    operationQueue.add(operation);

                }

                itemList.put(newId, new Item(newId, priority, operationQueue));
            }

        } catch (IOException e) {
            System.out.println("Error reading items details");
        }
    }

    /**
     * Retrieves all items from the repository.
     *
     * @return a List containing all Item objects in the repository
     */
    public List<Item> getItemList() {
        return new ArrayList<>(itemList.values());
    }
}
