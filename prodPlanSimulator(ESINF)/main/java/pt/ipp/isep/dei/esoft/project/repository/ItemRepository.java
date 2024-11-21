package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.BOO;
import pt.ipp.isep.dei.esoft.project.domain.data.FileDataReader;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;
import static pt.ipp.isep.dei.esoft.project.domain.data.ReadTreeInfo.getOpOrItem;
import static pt.ipp.isep.dei.esoft.project.domain.data.ReadTreeInfo.readBoo;

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
        try {
            fillItems();
            //associateItemsWithBoo();
        } catch (Exception e) {
            System.out.println("Error during initialization: " + e.getMessage());
        }
    }


    private void fillItems() {
        try {
            String PATH_ITEM = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/items.csv";
            String PATH_BOO = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/boo.csv";

            List<String[]> itemsDetails = getOpOrItem(PATH_ITEM);
            for (String[] importedItem : itemsDetails) {

                ID itemID = new ID(Integer.parseInt(importedItem[0]), TypeID.ITEM);
                String itemName = importedItem[1].trim();

                addItem(new Item(itemID, itemName, new LinkedList<>()));
            }

            List<String[]> booItems = readBoo(PATH_BOO);
            for (int i = 0; i < booItems.size(); i += 3) {
                String[] firstThreeValues = booItems.get(i);

                ID idOp = new ID(Integer.parseInt(firstThreeValues[0]), TypeID.OPERATION);
                ID idItem = new ID(Integer.parseInt(firstThreeValues[1]), TypeID.ITEM);
                float quantity = Float.parseFloat(firstThreeValues[2].trim());

                if (getMapItemList().containsKey(idItem)) {
                    Item item = getMapItemList().get(idItem);
                    item.setQuantity(quantity);
                    item.setRawMaterial(false);
                    item.addOpToItem(new Operation(idOp));
                }

                String[] rawMaterials = booItems.get(i + 2);

                for (int j = 1; j < rawMaterials.length; j += 2) {
                    ID rawMaterial = new ID(Integer.parseInt(rawMaterials[j]), TypeID.ITEM);
                    if (getMapItemList().containsKey(rawMaterial)) {
                        Item item = getMapItemList().get(rawMaterial);
                        item.setQuantity(Float.parseFloat(rawMaterials[j + 1].replace(",", ".")));
                    }
                }


            }


        } catch (IOException e) {
            System.out.println("Error reading operations from file");
        }
    }

    /**
     * Adds a new item to the repository if it does not already exist.
     *
     * @param item the Item to be added to the repository
     * @return an Optional containing a clone of the added item if successful,
     * or an empty Optional if the item already exists
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


        } catch (Exception e) {
            System.out.println();
            System.out.println(ANSI_BRIGHT_RED + "Error reading items details from file." + ANSI_RESET);
            System.out.println(ANSI_INDIAN_RED + "Shutting down..." + ANSI_RESET);
            System.exit(1);
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

    /**
     * Retrieves the map of items from the repository.
     *
     * @return a Map containing all Item objects in the repository
     */
    public Map<ID, Item> getMapItemList() {
        return itemList;
    }

    /**
     * Retrieves the name of an item based on its ID.
     *
     * @param id the ID of the item
     * @return the name of the item
     *
     * Time Complexity: O(1), assuming the map lookup and item retrieval operations are constant time.
     */
    public String getItemNameByID(ID id) {
        Item item = getMapItemList().get(id);
        return item.getName();
    }

}
