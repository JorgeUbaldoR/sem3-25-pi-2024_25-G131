package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @Test
    void addItem() {
        Item item1 = new Item(new ID(100, TypeID.ITEM), Priority.HIGH,new LinkedList<>());
        Item item2 = new Item(new ID(101, TypeID.ITEM), Priority.HIGH,new LinkedList<>());
        Item item3 = new Item(new ID(102, TypeID.ITEM), Priority.HIGH,new LinkedList<>());

        Optional<Item> result1 = itemRepository.addItem(item1);
        Optional<Item> result2 = itemRepository.addItem(item2);
        Optional<Item> result3 = itemRepository.addItem(item3);
        Optional<Item> result4 = itemRepository.addItem(item1);

        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
        assertTrue(result3.isPresent());
        assertFalse(result4.isPresent());

    }

    @Test
    void getItemList() {
        Item item1 = new Item(new ID(100, TypeID.ITEM), Priority.HIGH,new LinkedList<>());
        Item item2 = new Item(new ID(101, TypeID.ITEM), Priority.HIGH,new LinkedList<>());
        Item item3 = new Item(new ID(102, TypeID.ITEM), Priority.HIGH,new LinkedList<>());
        itemRepository.addItem(item1);
        itemRepository.addItem(item2);
        itemRepository.addItem(item3);
        List<Item> itemList = itemRepository.getItemList();
        assertTrue(itemList.contains(item1));
        assertTrue(itemList.contains(item2));
        assertTrue(itemList.contains(item3));

    }
    void fillINventory() {

    }
}