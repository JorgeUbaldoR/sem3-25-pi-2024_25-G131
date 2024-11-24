package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Node} class.
 */
class NodeTest {

    /**
     * Test the creation of a Node object with the expected properties.
     * <p>
     * Verifies that the Node constructor correctly initializes the Node's
     * operation ID, item ID, item quantity, operation map, and material map.
     * </p>
     */
    @Test
    void testNodeCreation() {
        ID operationID = new ID(12, TypeID.OPERATION);
        ID operationID2 = new ID(15, TypeID.OPERATION);
        ID itemID = new ID(11, TypeID.ITEM);

        Map<ID, Float> operationMap = new HashMap<>();
        operationMap.put(operationID, 10.5f);
        operationMap.put(operationID2, 5.0f);

        Map<ID, Float> materialMap = new HashMap<>();
        materialMap.put(itemID, 3.0f);

        Node node = new Node(operationID, itemID, 100.0f, operationMap, materialMap);

        // Verifying that the properties are correctly set.
        assertEquals(operationID, node.getOperationID());
        assertEquals(itemID, node.getItemID());
        assertEquals(100.0f, node.getItem_qtd());
        assertEquals(operationMap, node.getOperationMap());
        assertEquals(materialMap, node.getMaterialMap());
    }

    /**
     * Test the setter and getter methods for the Node class.
     * <p>
     * Ensures that the setter methods for the operation map, material map,
     * and height in tree are correctly setting the values and the getter
     * methods return the expected results.
     * </p>
     */
    @Test
    void testSettersAndGetters() {
        Node node = new Node(null, null, 0, null, null);

        ID operationID = new ID(12, TypeID.OPERATION);
        ID itemID = new ID(11, TypeID.ITEM);

        Map<ID, Float> operationMap = new HashMap<>();
        operationMap.put(operationID, 10.5f);

        Map<ID, Float> materialMap = new HashMap<>();
        materialMap.put(itemID, 3.0f);

        // Setting values for the node
        node.setOperationMap(operationMap);
        node.setMaterialMap(materialMap);
        node.setHeigthInTree(2);

        // Verifying the setter functionality by checking the getter values
        assertEquals(operationMap, node.getOperationMap());
        assertEquals(materialMap, node.getMaterialMap());
        assertEquals(2, node.getHeigthInTree());
    }

    /**
     * Test the {@link Node#toString()} method.
     * <p>
     * Verifies that the string representation of the Node object is not empty.
     * This test assumes that the {@code toString} method provides a non-empty
     * description of the Node's state.
     * </p>
     */
    @Test
    void testToString() {
        ID operationID = new ID(12, TypeID.OPERATION);
        ID itemID = new ID(11, TypeID.ITEM);

        Map<ID, Float> operationMap = new HashMap<>();
        operationMap.put(operationID, 10.5f);

        Map<ID, Float> materialMap = new HashMap<>();
        materialMap.put(itemID, 3.0f);

        Node node = new Node(operationID, itemID, 100.0f, operationMap, materialMap);

        // Verifying that the toString() method returns a non-empty string
        String string = node.toString();
        assertNotEquals("", string);
    }

    /**
     * Test the {@link Node#getItemQtdByID(ID)} method.
     * <p>
     * Verifies that the method correctly retrieves the quantity of an item
     * given its ID from the material map. It ensures that the method works
     * properly when the item exists in the map.
     * </p>
     */
    @Test
    void testGetItemQtdByID() {
        Map<ID, Float> materialMap = new HashMap<>();
        ID itemID = new ID(11, TypeID.ITEM);
        materialMap.put(itemID, 3.0f);

        Node node = new Node(null, null, 0, null, materialMap);

        // Verifying that the method returns the correct quantity for an existing ID
        assertEquals(3.0f, node.getItemQtdByID(itemID));
    }
}
