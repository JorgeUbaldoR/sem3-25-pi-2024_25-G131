package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.more.ID;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    private Item item5;

    private ID idItem1;
    private ID idItem2;

    private Operation opCutting;
    private Operation opWelding;
    private Operation opPainting;

    @BeforeEach
    void setUp() {
        opCutting = new Operation("Cutting", "Cutting raw materials", 2.5f);
        opWelding = new Operation("Welding", "Welding metal parts", 3.0f);
        opPainting = new Operation("Painting", "Painting the surface", 1.5f);

        Queue<Operation> operationListItem1 = new LinkedList<>();
        operationListItem1.add(opCutting);
        operationListItem1.add(opWelding);
        operationListItem1.add(opPainting);

        Queue<Operation> operationListItem2 = new LinkedList<>();
        operationListItem2.add(opPainting);
        operationListItem2.add(opCutting);

        idItem1 = new ID(101, TypeID.ITEM);
        idItem2 = new ID(102, TypeID.ITEM);


        item1 = new Item(idItem1, Priority.HIGH, operationListItem1);
        item2 = new Item(idItem2, Priority.HIGH, operationListItem2);
        item3 = item1;
        item4 = null;
        item5 = new Item(idItem1, Priority.LOW, operationListItem2);
    }

    @Test
    void getItemID() {
        System.out.println("Test Get IDs");
        assertEquals(idItem1, item1.getItemID());
        assertEquals(idItem2, item2.getItemID());
    }

    @Test
    void getCurrentOperation() {
        System.out.println("Test Get Current Operations");
        assertEquals(opCutting, item1.getCurrentOperation());
        assertEquals(opPainting, item2.getCurrentOperation());
    }

    @Test
    void getNextOperation() {
        System.out.println("Test Get Next Operations");
        assertEquals(opWelding, item1.getNextOperation());
        assertEquals(opCutting, item2.getNextOperation());
    }

    @Test
    void testClone() {
        System.out.println("Test Clone Operations");
        Item cloneItem1 = item1.clone();
        assertNotSame(item1, cloneItem1);
        assertEquals(item1, cloneItem1);
    }

    @Test
    void testEquals() {
        System.out.println("Test Equals");
        Item cloneItem1 = item1.clone();
        assertEquals(cloneItem1, item1);
        assertEquals(item3, item1);
        assertNotEquals(cloneItem1, item2);
        assertNotEquals(item2, item4);
        assertNotEquals(item3, idItem1);
    }

    @Test
    void testHashCode() {
//        assertEquals(item1.hashCode(), item5.hashCode());
        assertEquals(item1.hashCode(),item3.hashCode());
    }

    @Test
    void testToString() {
        System.out.println("Test ToString");
        assertEquals(item1.toString(),item3.toString());
    }
}