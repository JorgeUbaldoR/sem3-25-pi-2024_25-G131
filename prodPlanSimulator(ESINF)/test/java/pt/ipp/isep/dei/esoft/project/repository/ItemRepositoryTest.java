package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
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
        Item item1 = new Item(new ID(100, TypeID.ITEM), Priority.HIGH, new LinkedList<>());
        Item item2 = new Item(new ID(101, TypeID.ITEM), Priority.NORMAL, new LinkedList<>());
        Item item3 = new Item(new ID(102, TypeID.ITEM), Priority.LOW, new LinkedList<>());

        Optional<Item> result1 = itemRepository.addItem(item1);
        Optional<Item> result2 = itemRepository.addItem(item2);
        Optional<Item> result3 = itemRepository.addItem(item3);
        Optional<Item> result4 = itemRepository.addItem(item1); // Adding duplicate

        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
        assertTrue(result3.isPresent());
        assertFalse(result4.isPresent());
    }

    @Test
    void getItemList() {
        Item item1 = new Item(new ID(100, TypeID.ITEM), Priority.HIGH, new LinkedList<>());
        Item item2 = new Item(new ID(101, TypeID.ITEM), Priority.NORMAL, new LinkedList<>());
        Item item3 = new Item(new ID(102, TypeID.ITEM), Priority.LOW, new LinkedList<>());
        itemRepository.addItem(item1);
        itemRepository.addItem(item2);
        itemRepository.addItem(item3);

        List<Item> itemList = itemRepository.getItemList();
        assertEquals(27, itemList.size());
        assertTrue(itemList.contains(item1));
        assertTrue(itemList.contains(item2));
        assertTrue(itemList.contains(item3));
    }

    @Test
    void getMapItemList() {
        Item item1 = new Item(new ID(100, TypeID.ITEM), Priority.HIGH, new LinkedList<>());
        Item item2 = new Item(new ID(101, TypeID.ITEM), Priority.NORMAL, new LinkedList<>());
        itemRepository.addItem(item1);
        itemRepository.addItem(item2);

        assertTrue(itemRepository.getMapItemList().containsKey(item1.getItemID()));
        assertTrue(itemRepository.getMapItemList().containsKey(item2.getItemID()));
    }

    @Test
    void getItemNameByID() {
        ID id1 = new ID(100, TypeID.ITEM);
        ID id2 = new ID(101, TypeID.ITEM);
        Item item1 = new Item(id1, "Item A", new LinkedList<>());
        Item item2 = new Item(id2, "Item B", new LinkedList<>());

        itemRepository.addItem(item1);
        itemRepository.addItem(item2);

        assertEquals("Item A", itemRepository.getItemNameByID(id1));
        assertEquals("Item B", itemRepository.getItemNameByID(id2));
    }

    @Test
    void fillInventory() {
        // Assuming `fillInventory` is reading valid mock data from files
        // Validate that the inventory is populated correctly
        List<Item> items = itemRepository.getItemList();
        itemRepository.fillItems();
        assertFalse(items.isEmpty());
        assertNotNull(items.get(0)); // At least one item should exist
    }

    @Test
    void fillItems() {
        // Assuming `fillItems` is reading from predefined files for testing
        itemRepository = new ItemRepository(); // Calls `fillItems` during initialization
        List<Item> items = itemRepository.getItemList();

        assertNotNull(items);
        assertTrue(items.size() > 0, "The item list should be populated after calling fillItems");
    }

    @Test
    void addDuplicateItem() {
        Item item = new Item(new ID(100, TypeID.ITEM), Priority.HIGH, new LinkedList<>());
        itemRepository.addItem(item);
        Optional<Item> duplicateItem = itemRepository.addItem(item);

        assertFalse(duplicateItem.isPresent());
        assertEquals(25, itemRepository.getItemList().size());
    }

    @Test
    void testOperationAssociation() {
        ID itemId = new ID(200, TypeID.ITEM);
        ID operationId = new ID(300, TypeID.OPERATION);

        Item item = new Item(itemId, "Test Item", new LinkedList<>());
        itemRepository.addItem(item);

        // Simulate associating an operation with the item
        Operation operation = new Operation(operationId);
        item.addOpToItem(operation);

        assertEquals(1, item.getOperationList().size());
        assertEquals(operation, item.getOperationList().get(0));
    }
}
