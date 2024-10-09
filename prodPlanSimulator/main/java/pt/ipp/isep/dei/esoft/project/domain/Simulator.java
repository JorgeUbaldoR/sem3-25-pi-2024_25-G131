package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.util.*;

public class Simulator {
    private final float DEFAULT_MINIMUM_TIME = -1;

    private Map<Operation,Queue<Machine>> machineList;
    private List<Item> itemList;
    private List<OperationQueue> operationQueueList;


    public Simulator(Map<Operation,Queue<Machine>> machines,List<Item> items,List<Operation> operations) {
        checkInformation(machines, operations, items);
        this.machineList = machines;
        this.itemList = items;
        this.operationQueueList = new ArrayList<>();

        for (Operation operation : operations) {
            operationQueueList.add(new OperationQueue(operation));
        }

        createQueues(items);
    }

    public void createQueues(List<Item> items) {
        for(OperationQueue operationQueue : operationQueueList) {
            Operation currentOperation = operationQueue.getOperation();
            for (Item item : items) {
                if(currentOperation.equals(item.getNextOperation())){
                    operationQueue.addItemToQueue(item);
                }
            }
        }
    }

    public void startSimulation() {
        boolean moreItemsToProcess = true;
        int time = 0;
        do{
            System.out.println("========================================");
            System.out.println("    INICIO SIMULAÇÃO - Tempo: " + time );
            System.out.println("========================================");
            for (OperationQueue operationQueue : operationQueueList) {

            }
            time++;
        }while(moreItemsToProcess);

    }



    //-------------
    private void checkInformation(Map<Operation,Queue<Machine>> machines, List<Operation> operations,List<Item> items) {
        if (machines == null || machines.isEmpty()) {
            throw new IllegalArgumentException("Machine list is null or empty");
        }
        if (operations == null || operations.isEmpty()) {
            throw new IllegalArgumentException("Items list is null or empty");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items list is null or empty");
        }
    }
}
