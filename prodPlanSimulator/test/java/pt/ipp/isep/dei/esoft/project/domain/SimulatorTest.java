package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorTest {

    private Simulator simulator;

    private ID id1;
    private ID id2;
    private ID id3;
    private ID idMachineCutting;
    private ID idMachinePainting;
    private ID idMachineWelding;


    private Item item1;
    private Item item2;
    private Item item3;


    private Operation cutting;
    private Operation welding;
    private Operation painting;


    private Machine machineCutting;
    private Machine machinePainting;
    private Machine machineWelding;

    private OperationQueue operation1;
    private OperationQueue operation2;
    private OperationQueue operation3;

    List<Operation> operations;
    List<Item> items;
    List<Machine> machineList;
    Map<Operation, Queue<Machine>> machines;

    @BeforeEach
    void setUp() {
        cutting = new Operation("Cutting", "Cutting raw materials", 2.5f);
        painting = new Operation("Painting", "Welding metal parts", 3.0f);
        welding = new Operation("Welding", "Painting the surface", 1.5f);

        id1 = new ID(101, TypeID.ITEM);
        id2 = new ID(102, TypeID.ITEM);
        id3 = new ID(103, TypeID.ITEM);
        idMachineCutting = new ID(100, TypeID.MACHINE);
        idMachinePainting = new ID(101, TypeID.MACHINE);
        idMachineWelding = new ID(102, TypeID.MACHINE);

        Queue<Operation> operationListItem1 = new LinkedList<>();
        operationListItem1.add(cutting);
        operationListItem1.add(welding);
        operationListItem1.add(painting);

        Queue<Operation> operationListItem2 = new LinkedList<>();
        operationListItem2.add(painting);
        operationListItem2.add(cutting);

        Queue<Operation> operationListItem3 = new LinkedList<>();
        operationListItem3.add(welding);
        operationListItem3.add(cutting);

        item1 = new Item(id1, Priority.HIGH, operationListItem1);
        item2 = new Item(id2, Priority.HIGH, operationListItem2);
        item3 = new Item(id3, Priority.HIGH, operationListItem3);

        machineCutting = new Machine(idMachineCutting, cutting, 2.5f);
        machineWelding = new Machine(idMachineWelding, welding, 3.0f);
        machinePainting = new Machine(idMachinePainting, painting, 1.0f);

        operations = new LinkedList<>();
        operations.add(cutting);
        operations.add(welding);
        operations.add(painting);

        items = new LinkedList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        machineList = new LinkedList<>();
        machineList.add(machineCutting);
        machineList.add(machinePainting);
        machineList.add(machineWelding);


        machines = new HashMap<>();
        for (Machine machine : machineList) {
            if (!machines.containsKey(machine.getOperation())) {
                machines.put(machine.getOperation(), new PriorityQueue<>());
                machines.get(machine.getOperation()).add(machine);
            } else {
                machines.get(machine.getOperation()).add(machine);
            }
        }

        operation1 = new OperationQueue(cutting);
        operation1.addItemToQueue(item1);
        operation2 = new OperationQueue(welding);
        operation2.addItemToQueue(item3);
        operation3 = new OperationQueue(painting);
        operation3.addItemToQueue(item2);
    }

    @Test
    void testConstructor() {
        System.out.println("Test Constructor");
        simulator = new Simulator(machines, items, operations);
        assertNotNull(simulator);
        simulator = new Simulator();
        assertNotNull(simulator);
    }

    @Test
    void testAddOperationToQueue() {
        System.out.println("Test Add Operation To Queue");
        simulator = new Simulator();
        List<OperationQueue> result = simulator.addOperationToQueue(operations);
        List<OperationQueue> expected = new LinkedList<>();
        expected.add(new OperationQueue(cutting));
        expected.add(new OperationQueue(welding));
        expected.add(new OperationQueue(painting));
        assertEquals(expected, result);
    }

    @Test
    void createQueues() {
        System.out.println("Test Create Queues");
        simulator = new Simulator();
        simulator.addOperationToQueue(operations);
        List<OperationQueue> result = simulator.createQueues(items);
        List<OperationQueue> expected = new ArrayList<>();
        expected.add(operation1);
        expected.add(operation2);
        expected.add(operation3);
        assertEquals(expected, result);
    }

    @Test
    void startSimulation() {
        simulator = new Simulator();
        assertNotNull(simulator);
    }


}