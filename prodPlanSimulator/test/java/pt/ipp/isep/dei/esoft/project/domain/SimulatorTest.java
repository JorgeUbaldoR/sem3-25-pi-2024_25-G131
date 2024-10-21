package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.SimulatorController;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorTest {

    private Simulator simulator;
    private SimulatorController controller;
    private Map<Operation, Queue<Machine>> machineListMap = new HashMap<>();
    private List<OperationQueue> operationQueueList;
    private List<Item> itemList;
    private List<Operation> operationList;
    private List<Machine> machineList;

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



}