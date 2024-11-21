package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Controller class that manages the interaction with the Item repository.
 * This class provides functionality to add items to the repository.
 */
public class ItemController {
    private ItemRepository itemRepository;

    /**
     * Constructs an ItemController instance.
     * Initializes the ItemRepository by retrieving it from the Repositories singleton.
     */
    public ItemController() {
        getItemRepository();
    }

    /**
     * Retrieves the ItemRepository instance.
     *
     * @return the ItemRepository instance
     */
    private ItemRepository getItemRepository() {
        if (itemRepository == null) {
            Repositories repositorie = Repositories.getInstance();
            itemRepository = repositorie.getItemRepository();
        }
        return itemRepository;
    }

    /**
     * Adds a new Item to the repository.
     *
     * @param item the Item to be added
     * @return an Optional containing the newly added Item if successful, or empty if the Item already exists
     */
    public Optional<Item> addItemController(Item item) {
        return getItemRepository().addItem(item);
    }

    public Map<ID, Item> getItemList() {
        return getItemRepository().getMapItemList();
    }

}
