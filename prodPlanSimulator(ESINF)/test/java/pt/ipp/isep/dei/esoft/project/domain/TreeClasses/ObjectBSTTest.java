package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ObjectBSTTest {

    private ObjectBST objectBST1;
    private ObjectBST objectBST2;
    private ObjectBST objectBST3;
    private Map<ID, Item> itemMap1;
    private Map<ID, Item> itemMap2;

    @BeforeEach
    void setUp() {
        itemMap1 = new HashMap<>();
        itemMap2 = new HashMap<>();

        itemMap1.put(new ID(1, TypeID.ITEM), new Item(new ID(1, TypeID.ITEM), "Item1", new LinkedList<>()));
        itemMap1.put(new ID(2, TypeID.ITEM), new Item(new ID(2, TypeID.ITEM), "Item2", new LinkedList<>()));

        itemMap2.put(new ID(3, TypeID.ITEM), new Item(new ID(3, TypeID.ITEM), "Item3", new LinkedList<>()));

        objectBST1 = new ObjectBST(itemMap1, 10.0f);
        objectBST2 = new ObjectBST(itemMap2, 20.0f);
        objectBST3 = new ObjectBST(itemMap1, 10.0f); // Mesmos valores de objectBST1
    }

    @Test
    void testGettersAndSetters() {
        System.out.println("Test getters and setters");
        assertEquals(10.0f, objectBST1.getQuantityOfElements());
        assertEquals(itemMap1, objectBST1.getItemsWithQuantity());

        objectBST1.setQuantityOfElements(15.0f);
        assertEquals(15.0f, objectBST1.getQuantityOfElements());

        Map<ID, Item> newMap = new HashMap<>();
        objectBST1.setItemsWithQuantity(newMap);
        assertEquals(newMap, objectBST1.getItemsWithQuantity());
    }

    @Test
    void testAddItem() {
        System.out.println("Test addItem");
        Item newItem = new Item(new ID(4, TypeID.ITEM), "NewItem", new LinkedList<>());
        objectBST1.addItem(newItem);
        assertTrue(objectBST1.getItemsWithQuantity().containsKey(newItem.getItemID()));
        assertEquals(newItem, objectBST1.getItemsWithQuantity().get(newItem.getItemID()));
    }

    @Test
    void testEquals() {
        System.out.println("Test equals");
        assertTrue(objectBST1.equals(objectBST3)); // Mesmo quantityOfElements
        assertFalse(objectBST1.equals(objectBST2)); // Diferente quantityOfElements
        assertFalse(objectBST1.equals(null)); // Comparação com null
        assertFalse(objectBST1.equals(new Object())); // Comparação com outro tipo
    }

    @Test
    void testHashCode() {
        System.out.println("Test hashCode");
        assertEquals(objectBST1.hashCode(), objectBST3.hashCode()); // Mesmo hash
        assertNotEquals(objectBST1.hashCode(), objectBST2.hashCode()); // Hashes diferentes
    }

    @Test
    void testCompareTo() {
        System.out.println("Test compareTo");
        assertTrue(objectBST1.compareTo(objectBST2) < 0); // objectBST1 < objectBST2
        assertTrue(objectBST2.compareTo(objectBST1) > 0); // objectBST2 > objectBST1
        assertEquals(0, objectBST1.compareTo(objectBST3)); // objectBST1 == objectBST3
    }

    @Test
    void testToString() {
        System.out.println("Test toString");
        String result = objectBST1.toString();
        assertNotEquals("", result);
    }
}
