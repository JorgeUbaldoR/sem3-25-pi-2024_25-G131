package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.SimulatorController;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorTest {

    private Simulator simulator;
    private SimulatorController controller;
    private Map<Operation, Queue<Machine>> machineListMap = new HashMap<>();
    private OperationQueue operationQueue;
    private List<Item> itemList;
    private List<Operation> operationList;
    private List<Machine> machineList;
    private Map<Operation, Integer> executionPerOperation;

    @BeforeEach
    void setUp() {
        ID idMachine1 = new ID(10, TypeID.MACHINE);
        ID idMachine2 = new ID(11, TypeID.MACHINE);
        ID idMachine3 = new ID(12, TypeID.MACHINE);
        ID idMachine4 = new ID(13, TypeID.MACHINE);

        ID idItem1 = new ID(10, TypeID.ITEM);
        ID idItem2 = new ID(11, TypeID.ITEM);
        ID idItem3 = new ID(12, TypeID.ITEM);
        ID idItem4 = new ID(13, TypeID.ITEM);

        Operation cutting = new Operation("Cutting","",2);
        Operation welding = new Operation("welding","",7);
        Operation painting = new Operation("Painting","",5);

        Machine machineCutting = new Machine(idMachine1,cutting,2);
        Machine machineCutting2 = new Machine(idMachine1,cutting,5);
        Machine machineWelding = new Machine(idMachine2,welding,2);
        Machine machinePainting = new Machine(idMachine3,painting,5);

        Queue<Operation> queue1 = new LinkedList<>();
        queue1.add(cutting);
        queue1.add(welding);

        Queue<Operation> queue2 = new LinkedList<>();
        queue2.add(painting);
        queue2.add(welding);

        Queue<Operation> queue3 = new LinkedList<>();
        queue3.add(welding);
        queue3.add(painting);

        Item item1 = new Item(idItem1, Priority.HIGH,queue1);
        Item item2 = new Item(idItem2, Priority.HIGH,queue2);
        Item item3 = new Item(idItem3, Priority.HIGH,queue3);

        machineList = new ArrayList<>();
        machineList.add(machineCutting);
        machineList.add(machineCutting2);
        machineList.add(machinePainting);
        machineList.add(machineWelding);

        itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        operationList = new ArrayList<>();
        operationList.add(cutting);
        operationList.add(welding);
        operationList.add(painting);

        for (Operation operation : operationList) {
            if(!machineListMap.containsKey(operation)) {
                machineListMap.put(operation, new LinkedList<>());
            }
        }

        for (Machine machine : machineList){
            Operation currentOp = machine.getOperation();
            if (machineListMap.containsKey(currentOp)) {
                machineListMap.get(currentOp).add(machine);
            }
        }

        operationQueue = new OperationQueue(cutting, false);
        operationQueue.addItemToQueue(item1);

    }

    @Test
    void testConstructor() {
        System.out.println("Testing constructor");
        simulator = new Simulator();
        simulator = new Simulator(machineListMap,itemList,operationList,false);
        simulator = new Simulator(machineListMap,itemList,operationList,true);
        assertThrows(IllegalArgumentException.class , () -> new Simulator(null,itemList,operationList,true));
        assertThrows(IllegalArgumentException.class , () -> new Simulator(new HashMap<>(),itemList,operationList,true));
        assertThrows(IllegalArgumentException.class , () -> new Simulator(machineListMap,null,operationList,true));
        assertThrows(IllegalArgumentException.class , () -> new Simulator(machineListMap,new ArrayList<>(),operationList,true));
        assertThrows(IllegalArgumentException.class , () -> new Simulator(machineListMap,itemList,null,true));
        assertThrows(IllegalArgumentException.class , () -> new Simulator(machineListMap,itemList,new ArrayList<>(),true));
    }

    @Test
    void testStartSimulation(){
        System.out.println("Testing Start Simulation");
        simulator = new Simulator(machineListMap,itemList,operationList,false);
        simulator.startSimulation();
    }

    @Test
    void getMachineRouteTest() {
        simulator = new Simulator(machineListMap, itemList, operationList, false);
        simulator.startSimulation();
        simulator.printMachineRoute();

        Map<ID, Map<ID, Integer>> transitionMap = simulator.getMachineRoute();

        assertTrue(transitionMap.containsKey(new ID(10, TypeID.MACHINE)));
        assertEquals(1, (int) transitionMap.get(new ID(10, TypeID.MACHINE)).get(new ID(11, TypeID.MACHINE)));

        assertTrue(transitionMap.containsKey(new ID(11, TypeID.MACHINE)));
        assertEquals(1, (int) transitionMap.get(new ID(11, TypeID.MACHINE)).get(new ID(12, TypeID.MACHINE)));

        assertTrue(transitionMap.containsKey(new ID(12, TypeID.MACHINE)));
        assertEquals(1, (int) transitionMap.get(new ID(12, TypeID.MACHINE)).get(new ID(11, TypeID.MACHINE)));
    }

    @Test
    void testFillExecutionPerOperation() {
        System.out.println("Testing Fill Execution Per Operation");
        simulator = new Simulator(machineListMap,itemList,operationList,false);
        Operation operation = new Operation("Op1");


        simulator.fillExecutionPerOperation(operation);
        assertEquals(1, simulator.getExecutionPerOperation().get(operation).intValue());

        simulator.fillExecutionPerOperation(operation);
        assertEquals(2, simulator.getExecutionPerOperation().get(operation).intValue());
    }

    @Test
    void testCalculateAverageExecutionTimes() {
        System.out.println("Testing Calculate Average Execution Times");
        simulator = new Simulator(machineListMap,itemList,operationList,false);
        Operation operation = new Operation("Op1");

        simulator.getPpk().put(operation, 10f);
        simulator.getExecutionPerOperation().put(operation, 2);

        simulator.calculateAverageExecutionTimes();

        assertEquals(5f, simulator.getAvgExecutionTime().get(operation), 0.001);
    }

    @Test
    void testFillWaitingTime() {
        System.out.println("Testing Fill Waiting Time");
        simulator = new Simulator(machineListMap,itemList,operationList,false);

        operationQueue.addItem(itemList.get(1));
        List<OperationQueue> queues = Collections.singletonList(operationQueue);

        simulator.fillWaitingTime(queues);

        assertEquals(1f, simulator.getWaitingTime().get(itemList.get(0)), 0.001);
        assertEquals(1f, simulator.getWaitingTime().get(itemList.get(1)), 0.001);

        simulator.fillWaitingTime(queues);

        assertEquals(2f, simulator.getWaitingTime().get(itemList.get(0)), 0.001);
        assertEquals(2f, simulator.getWaitingTime().get(itemList.get(1)), 0.001);
    }

    @Test
    void testAscendingOrderAvgExecutionTime() {
        System.out.println("Testing Ascending Order Avg Execution Time");
        simulator = new Simulator(machineListMap,itemList,operationList,false);

        simulator.getAvgExecutionTime().put(operationList.get(0), 5f);
        simulator.getAvgExecutionTime().put(operationList.get(1), 10f);

        List<Map.Entry<Operation, Float>> result = simulator.ascendingOrderAvgExecutionTime();
        assertEquals(operationList.get(1), result.get(0).getKey());
        assertEquals(operationList.get(0), result.get(1).getKey());
    }

    @Test
    void testAscendingOrderWaitingTime() {
        System.out.println("Testing Ascending Order Waiting Time");
        simulator = new Simulator(machineListMap,itemList,operationList,false);

        simulator.getWaitingTime().put(itemList.get(0), 2f);
        simulator.getWaitingTime().put(itemList.get(1), 4f);

        List<Map.Entry<Item, Float>> result = simulator.ascendingOrderWaitingTime();
        assertEquals(itemList.get(1), result.get(0).getKey());
        assertEquals(itemList.get(0), result.get(1).getKey());
    }

    @Test
    public void testPrintExecutionTimesOperation() {
        simulator = new Simulator(machineListMap, itemList, operationList, false);
        simulator.startSimulation();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        simulator.printExecutionTimesOperation();
        String output = outputStream.toString();

        assertTrue(output.contains("Cutting"));
        assertTrue(output.contains("welding"));
        assertTrue(output.contains("Painting"));

        assertTrue(output.contains("2"));
        assertTrue(output.contains("7"));
        assertTrue(output.contains("5"));

        assertTrue(output.contains("55,56 %"));
        assertTrue(output.contains("33,33 %"));
        assertTrue(output.contains("11,11 %"));

        System.setOut(System.out);
    }

    @Test
    public void testPrintMachineRoute() {
        simulator = new Simulator(machineListMap, itemList, operationList, false);
        simulator.startSimulation();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        simulator.printMachineRoute();
        String printedOutput = outputStream.toString();

        assertTrue(printedOutput.contains("Machine ID"));
        assertTrue(printedOutput.contains("Machine Route"));

        assertTrue(printedOutput.contains("W-10"));
        assertTrue(printedOutput.contains("(W-11,1)"));
        assertTrue(printedOutput.contains("W-11"));
        assertTrue(printedOutput.contains("(W-12,1)"));

        System.setOut(System.out);
    }

    @Test
    public void testPrintItemRoute() {
        simulator = new Simulator(machineListMap, itemList, operationList, false);
        simulator.startSimulation();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        simulator.printItemRoute();
        String printedOutput = outputStream.toString();

        assertTrue(printedOutput.contains("Item"));
        assertTrue(printedOutput.contains("Item Route"));

        assertTrue(printedOutput.contains("I-10"));
        assertTrue(printedOutput.contains("[W-11, W-12]"));

        System.setOut(System.out);
    }
}