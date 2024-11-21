package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testNodeCreation() {
        ID operationID = new ID(12, TypeID.OPERATION);
        ID operationID2 = new ID(15, TypeID.OPERATION);
        ID itemID = new ID(11,TypeID.ITEM);

        Map<ID, Float> operationMap = new HashMap<>();
        operationMap.put(operationID, 10.5f);
        operationMap.put(operationID2, 5.0f);

        Map<ID, Float> materialMap = new HashMap<>();
        materialMap.put(itemID, 3.0f);

        Node node = new Node(operationID, itemID, 100.0f, operationMap, materialMap);

        assertEquals(operationID, node.getOperationID());
        assertEquals(itemID, node.getItemID());
        assertEquals(100.0f, node.getItem_qtd());
        assertEquals(operationMap, node.getOperationMap());
        assertEquals(materialMap, node.getMaterialMap());
    }

    @Test
    void testSettersAndGetters() {
        Node node = new Node(null, null, 0, null, null);

        ID operationID = new ID(12, TypeID.OPERATION);
        ID itemID = new ID(11,TypeID.ITEM);

        Map<ID, Float> operationMap = new HashMap<>();
        operationMap.put(operationID, 10.5f);

        Map<ID, Float> materialMap = new HashMap<>();
        materialMap.put(itemID, 3.0f);

        node.setOperationMap(operationMap);
        node.setMaterialMap(materialMap);
        node.setHeigthInTree(2);

        assertEquals(operationMap, node.getOperationMap());
        assertEquals(materialMap, node.getMaterialMap());
        assertEquals(2, node.getHeigthInTree());
    }

    @Test
    void testToString() {
        ID operationID = new ID(12, TypeID.OPERATION);
        ID itemID = new ID(11,TypeID.ITEM);

        Map<ID, Float> operationMap = new HashMap<>();
        operationMap.put(operationID, 10.5f);

        Map<ID, Float> materialMap = new HashMap<>();
        materialMap.put(itemID, 3.0f);

        Node node = new Node(operationID, itemID, 100.0f, operationMap, materialMap);

        String string = node.toString();

        assertNotEquals("", node.toString());
    }

    @Test
    void testGetItemQtdByID() {
        Map<ID, Float> materialMap = new HashMap<>();
        ID itemID = new ID(11,TypeID.ITEM);
        materialMap.put(itemID, 3.0f);

        Node node = new Node(null, null, 0, null, materialMap);

        assertEquals(3.0f, node.getItemQtdByID(itemID));
    }
}
