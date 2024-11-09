package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Queue;

class MachineTest {

    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;

    private ID idItem1;
    private ID idItem2;

    private ID idMachineCutting;
    private ID idMachinePainting;
    private ID idMachineWelding;
    private ID idMachineCutting03;

    private Machine machineCutting;
    private Machine machinePainting;
    private Machine machineWelding;
    private Machine machineCutting02;
    private Machine machineCutting03;
    private Machine machineCutting04;

    private Operation opCutting;
    private Operation opWelding;
    private Operation opPainting;

    @BeforeEach
    void setUp() {
        // Define the operations
        opCutting = new Operation("Cutting", "Cutting raw materials", 2.5f);
        opWelding = new Operation("Welding", "Welding metal parts", 3.0f);
        opPainting = new Operation("Painting", "Painting the surface", 1.5f);

        // Create item IDs
        idItem1 = new ID(101, TypeID.ITEM);
        idItem2 = new ID(102, TypeID.ITEM);

        // Create operation lists for items
        Queue<Operation> operationListItem1 = new LinkedList<>();
        operationListItem1.add(opCutting);
        operationListItem1.add(opWelding);
        operationListItem1.add(opPainting);

        Queue<Operation> operationListItem2 = new LinkedList<>();
        operationListItem2.add(opPainting);
        operationListItem2.add(opCutting);

        Queue<Operation> operationListItem4 = new LinkedList<>();
        operationListItem2.add(opPainting);
        operationListItem2.add(opCutting);

        // Create items
        item1 = new Item(idItem1, Priority.HIGH, operationListItem1);
        item2 = new Item(idItem2, Priority.HIGH, operationListItem2);
        item3 = new Item(idItem2, Priority.HIGH, new LinkedList<>());
        item4 = new Item(idItem2, Priority.HIGH, operationListItem4);

        // Create machine IDs
        idMachineCutting = new ID(100, TypeID.MACHINE);
        idMachinePainting = new ID(101, TypeID.MACHINE);
        idMachineWelding = new ID(102, TypeID.MACHINE);
        idMachineCutting03 = new ID(103, TypeID.MACHINE);

        // Create machines
        machineCutting = new Machine(idMachineCutting, opCutting, 2.5f);
        machineWelding = new Machine(idMachineWelding, opWelding, 3.0f);
        machinePainting = new Machine(idMachinePainting, opPainting, 1.0f);
        machineCutting02 = new Machine(idMachineCutting, opPainting, 1.0f);
        machineCutting03 = new Machine(idMachineCutting03, opPainting, 2.5f);
        machineCutting04 = new Machine(idMachineCutting, opPainting, 2.5f);


    }

    @Test
    void processItem() {
        System.out.println("Test Process Item");

        machineCutting.processItem(item1);
        assertEquals(item1, machineCutting.getCurrentProcessingItem());
        assertFalse(machineCutting.isAvailable());
        assertEquals(machineCutting.getTimeLeftToFinish(), machineCutting.getProcessingSpeed());

        machinePainting.processItem(item2);
        assertEquals(item2, machinePainting.getCurrentProcessingItem());
        assertFalse(machinePainting.isAvailable());
        assertEquals(machinePainting.getTimeLeftToFinish(), machinePainting.getProcessingSpeed());

        machineWelding.processItem(item3);
        assertNotEquals(item3, machineWelding.getCurrentProcessingItem());
        assertTrue(machineWelding.isAvailable());
        assertNotEquals(machineWelding.getTimeLeftToFinish(), machineWelding.getProcessingSpeed());
    }

    @Test
    void updateMachine() {
        machinePainting.processItem(item2);
        boolean result = machinePainting.updateMachine();
        assertNotEquals(machinePainting.getTimeLeftToFinish(), machinePainting.getProcessingSpeed());
        assertEquals(machinePainting.getTimeLeftToFinish(), 0);
        assertTrue(machinePainting.isAvailable());
        assertTrue(result);
        boolean result2 = machineCutting.updateMachine();
        assertNotEquals(machineCutting.getTimeLeftToFinish(), machineCutting.getProcessingSpeed());
        assertFalse(result2);
    }


    @Test
    void setCurrentProcessingItem() {
        machineWelding.setCurrentProcessingItem(item4);
        assertEquals(item4, machineWelding.getCurrentProcessingItem());
    }

    @Test
    void setNotAvailable() {
        machineWelding.setNotAvailable();
        assertFalse(machineWelding.isAvailable());
    }

    @Test
    void getOperation() {
        assertEquals(opCutting, machineCutting.getOperation());
        assertEquals(opPainting, machinePainting.getOperation());
        assertEquals(opWelding, machineWelding.getOperation());
    }

    @Test
    void getId_machine() {
        assertEquals(machineCutting.getId_machine(), idMachineCutting);
        assertEquals(machinePainting.getId_machine(), idMachinePainting);
        assertEquals(machineWelding.getId_machine(), idMachineWelding);
    }

    @Test
    void getProcessingSpeed() {
        assertEquals(machineCutting.getProcessingSpeed(), 2.5f);
        assertEquals(machinePainting.getProcessingSpeed(), 1.0f);
        assertEquals(machineWelding.getProcessingSpeed(), 3.0f);
    }

    @Test
    void isAvailable() {
        assertTrue(machineCutting.isAvailable());
        assertFalse(!machinePainting.isAvailable());
        assertFalse(!machineWelding.isAvailable());
    }

    @Test
    void getTimeLeftToFinish() {
        assertEquals(machinePainting.getTimeLeftToFinish(), 0);
    }

    @Test
    void getCurrentProcessingItem() {
        machinePainting.processItem(item2);
        assertEquals(machineCutting.getCurrentProcessingItem(), null);
        assertEquals(machinePainting.getCurrentProcessingItem(), item2);
    }

    @Test
    void testEquals() {
        assertEquals(machineCutting, machineCutting);
        assertNotEquals(machinePainting, machineCutting);
        assertNotEquals(null, machineWelding);
        assertNotEquals(machinePainting, new Object());
        assertEquals(machineCutting02, machineCutting);

    }

    @Test
    void testHashCode() {
        assertNotEquals(machineCutting.hashCode(), machinePainting.hashCode());
        assertEquals(machineCutting.hashCode(), machineCutting02.hashCode());

    }

    @Test
    void compareTo() {
        int result = machineCutting.compareTo(machinePainting);
        assertEquals(1, result);
        result = machinePainting.compareTo(machineCutting);
        assertEquals(-1, result);

        result = machineCutting.compareTo(machineCutting03);
        assertEquals(-1,result);
        result = machineCutting03.compareTo(machineCutting);
        assertEquals(1, result);
        result = machineCutting.compareTo(machineCutting04);
        assertEquals(0,result);
    }

    @Test
    void testClone() {
        Machine cloneMachine = machineCutting.clone();
        assertEquals(machineCutting, cloneMachine);
    }

    @Test
    void testToString() {
        assertEquals(machineCutting.toString(), machineCutting.toString());
    }

    @Test
    void testPrintStatus(){
        assertTrue(machinePainting.printStatus());
        machinePainting.processItem(item2);
        assertFalse(machinePainting.printStatus());
    }
}