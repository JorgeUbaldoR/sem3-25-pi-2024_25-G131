package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.util.*;

public class Simulator {

    private final ID simulatorID;
    private Map<Operation, Queue<Item>> itemList;
    private Map<Operation, Queue<Machine>> machineList;


    public Simulator(List<Item> itemList, List<Machine> machineList, ID id) {
        checkInformation(itemList, machineList);

        organizeItems(itemList);
        organizeMachines(machineList);
        simulatorID = id;
    }




    public boolean startSimulation() {

        return false;
    }


    private void organizeItems(List<Item> itemList) {
        this.itemList = new HashMap<>();

        for (Item item : itemList) {
            if (this.itemList.containsKey(item.showNextOperation())) {
                this.itemList.get(item.showNextOperation()).add(item);
            } else {
                this.itemList.put(item.showNextOperation(), new LinkedList<>());
                this.itemList.get(item.showNextOperation()).add(item);
            }
        }
    }
    private void organizeMachines(List<Machine> machineList) {
        this.machineList = new HashMap<>();

        for(Machine machine : machineList) {
            if(this.machineList.containsKey(machine.getOperation())){
               this.machineList.get(machine.getOperation()).add(machine);
            }else{
                this.machineList.put(machine.getOperation(), new PriorityQueue<>());
                this.machineList.get(machine.getOperation()).add(machine);
            }
        }
    }


    private void checkInformation(List<Item> itemList, List<Machine> machineList) {
        if (itemList == null || itemList.isEmpty())
            throw new IllegalArgumentException("The passed item list can't be null or empty");

        if (machineList == null || machineList.isEmpty())
            throw new IllegalArgumentException("The passed machine list can't be null or empty");
    }
}
