package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;

public class ItemController {
    private ItemRepository itemRepository;

    public ItemController() {
        getItemRepository();
    }

    private ItemRepository getItemRepository() {
        if (itemRepository == null) {
            Repositories repositorie = Repositories.getInstance();
            itemRepository = repositorie.getItemRepository();
        }
        return itemRepository;
    }


    public Optional<Item> addItemController(Item item) {
        return getItemRepository().addItem(item);
    }


}
