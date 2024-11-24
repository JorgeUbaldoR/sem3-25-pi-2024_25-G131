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

/**
 * Unit tests for the {@link ObjectBST} class.
 */
class ObjectBSTTest {

    private ObjectBST objectBST1;
    private ObjectBST objectBST2;
    private ObjectBST objectBST3;
    private Map<ID, Item> itemMap1;
    private Map<ID, Item> itemMap2;

    /**
     * Set up the initial conditions for the test cases.
     * <p>
     * Initializes the {@code ObjectBST} instances and the item maps to be used
     * in the tests. This method runs before each test case to ensure a clean setup.
     * </p>
     */
    @BeforeEach
    void setUp() {
        itemMap1 = new HashMap<>();
        itemMap2 = new HashMap<>();

        itemMap1.put(new ID(1, TypeID.ITEM), new Item(new ID(1, TypeID.ITEM), "Item1", new LinkedList<>()));
        itemMap1.put(new ID(2, TypeID.ITEM), new Item(new ID(2, TypeID.ITEM), "Item2", new LinkedList<>()));

        itemMap2.put(new ID(3, TypeID.ITEM), new Item(new ID(3, TypeID.ITEM), "Item3", new LinkedList<>()));

        objectBST1 = new ObjectBST(itemMap1, 10.0f);
        objectBST2 = new ObjectBST(itemMap2, 20.0f);
        objectBST3 = new ObjectBST(itemMap1, 10.0f); // Same values as objectBST1
    }

    /**
     * Test the getter and setter methods of the {@link ObjectBST} class.
     * <p>
     * Verifies that the getters and setters for the properties of the ObjectBST
     * class work as expected. This includes checking the {@code quantityOfElements}
     * and {@code itemsWithQuantity}.
     * </p>
     */
    @Test
    void testGettersAndSetters() {
        System.out.println("Test getters and setters");

        // Verifying initial values
        assertEquals(10.0f, objectBST1.getQuantityOfElements());
        assertEquals(itemMap1, objectBST1.getItemsWithQuantity());

        // Testing setter for quantityOfElements
        objectBST1.setQuantityOfElements(15.0f);
        assertEquals(15.0f, objectBST1.getQuantityOfElements());

        // Testing setter for itemsWithQuantity
        Map<ID, Item> newMap = new HashMap<>();
        objectBST1.setItemsWithQuantity(newMap);
        assertEquals(newMap, objectBST1.getItemsWithQuantity());
    }

    /**
     * Test the {@link ObjectBST#addItem(Item)} method.
     * <p>
     * Verifies that the addItem method correctly adds an item to the {@code itemsWithQuantity}
     * map and that the item is accessible by its ID.
     * </p>
     */
    @Test
    void testAddItem() {
        System.out.println("Test addItem");

        // Creating a new Item
        Item newItem = new Item(new ID(4, TypeID.ITEM), "NewItem", new LinkedList<>());

        // Adding the new item to objectBST1
        objectBST1.addItem(newItem);

        // Verifying that the item is added to the map
        assertTrue(objectBST1.getItemsWithQuantity().containsKey(newItem.getItemID()));
        assertEquals(newItem, objectBST1.getItemsWithQuantity().get(newItem.getItemID()));
    }

    /**
     * Test the {@link ObjectBST#equals(Object)} method.
     * <p>
     * Verifies that the equals method behaves correctly by checking comparisons between
     * two ObjectBST instances with the same and different properties. This also tests
     * comparisons with {@code null} and objects of different types.
     * </p>
     */
    @Test
    void testEquals() {
        System.out.println("Test equals");

        // Comparing objectBST1 with objectBST3 (same quantityOfElements)
        assertTrue(objectBST1.equals(objectBST3)); // Same quantityOfElements

        // Comparing objectBST1 with objectBST2 (different quantityOfElements)
        assertFalse(objectBST1.equals(objectBST2)); // Different quantityOfElements

        // Comparing with null and different type
        assertFalse(objectBST1.equals(null)); // Comparison with null
        assertFalse(objectBST1.equals(new Object())); // Comparison with another type
    }

    /**
     * Test the {@link ObjectBST#hashCode()} method.
     * <p>
     * Verifies that the hashCode method returns consistent and correct hash codes
     * for ObjectBST instances with the same and different properties.
     * </p>
     */
    @Test
    void testHashCode() {
        System.out.println("Test hashCode");

        // Comparing hash codes of two ObjectBST instances with the same properties
        assertEquals(objectBST1.hashCode(), objectBST3.hashCode()); // Same hash

        // Comparing hash codes of two ObjectBST instances with different properties
        assertNotEquals(objectBST1.hashCode(), objectBST2.hashCode()); // Different hashes
    }

    /**
     * Test the {@link ObjectBST#compareTo(ObjectBST)} method.
     * <p>
     * Verifies that the compareTo method correctly compares two ObjectBST instances
     * based on their {@code quantityOfElements}. It tests both greater-than, less-than,
     * and equal comparisons.
     * </p>
     */
    @Test
    void testCompareTo() {
        System.out.println("Test compareTo");

        // Comparing objectBST1 and objectBST2
        assertTrue(objectBST1.compareTo(objectBST2) < 0); // objectBST1 < objectBST2

        // Comparing objectBST2 and objectBST1
        assertTrue(objectBST2.compareTo(objectBST1) > 0); // objectBST2 > objectBST1

        // Comparing objectBST1 and objectBST3
        assertEquals(0, objectBST1.compareTo(objectBST3)); // objectBST1 == objectBST3
    }

    /**
     * Test the {@link ObjectBST#toString()} method.
     * <p>
     * Verifies that the toString method returns a non-empty string representation of the
     * ObjectBST instance.
     * </p>
     */
    @Test
    void testToString() {
        System.out.println("Test toString");

        // Verifying that the toString method returns a non-empty string
        String result = objectBST1.toString();
        assertNotEquals("", result);
    }
}
