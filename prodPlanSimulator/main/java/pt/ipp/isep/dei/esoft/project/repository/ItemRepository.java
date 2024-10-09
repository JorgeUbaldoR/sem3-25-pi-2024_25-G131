package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;

import java.util.*;

public class ItemRepository {
    private Map<ID, Item> itemList;

    public ItemRepository() {
        itemList = new HashMap<>();
    }

    //------------ Add Item ---------------
    public Optional<Item> addItem(Item item) {
        Optional<Item> newItem = Optional.empty();

        if(!this.itemList.containsKey(item.getItemID())) {
            this.itemList.put(item.getItemID(), item);
            newItem = Optional.of(item.clone());
        }

        return newItem;
    }
    //---------------------------------------------


    public List<Item> getItemList() {
        return new ArrayList<>(itemList.values());
    }
}
