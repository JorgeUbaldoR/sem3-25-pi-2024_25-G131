package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;

import java.util.*;

public class ItemRepository {
    private Map<ID, Item> itemList;

    public ItemRepository() {
        itemList = new HashMap<>();
    }

    //------------ Add Item ---------------
    public Optional<Item> addItem(Item item) {
        Optional newItem = Optional.empty();

        boolean operationSucess = !itemList.containsKey(item.getId_item());

        if(operationSucess) {
            newItem = Optional.of(item.clone());
            itemList.put(item.getId_item(), item);
        }else{
            return newItem;
        }
        return newItem;
    }
    //---------------------------------------------


    public List<Item> getItemList() {
        return new ArrayList<>(itemList.values());
    }
}
