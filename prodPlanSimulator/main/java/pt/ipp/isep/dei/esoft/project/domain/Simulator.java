package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.util.*;

public class Simulator {
    private final Map<Operation,Queue<Machine>> machineList;
    private final List<OperationQueue> operationQueueList;


    public Simulator(Map<Operation,Queue<Machine>> machines, List<Item> items, List<Operation> operations) {
        checkInformation(machines, operations, items);
        this.machineList = machines;
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
                if(currentOperation.equals(item.getCurrentOperation())){
                    operationQueue.addItemToQueue(item);
                }
            }
        }
    }

    public void startSimulation() {
        int time = 0;
        while(checkOperationQueue()){
            System.out.println("----------------------------------------");
            System.out.println("| INICIO SIMULAÇÃO - Tempo: " + time +"|");
            System.out.println("----------------------------------------");
            for (OperationQueue operationQueue : operationQueueList) {
                if (!operationQueue.isEmpty())
                    assingItemToMachine(operationQueue, machineList.get(operationQueue.getOperation()));
            }
            updateMachines();
            printMachineStatus();
            System.out.println("----------------------------------------");
            time++;
            sleep(1000);
        }
        System.out.println("✅ All operations completed!");
    }


    private void assingItemToMachine(OperationQueue operationQueue, Queue<Machine> machineList) {
        if(!machineList.isEmpty()){
            for (Machine currentMachine : machineList) {
                if(currentMachine.isAvailable()){
                    currentMachine.processItem(operationQueue.getNextItem());
                }
            }
        }
    }

    private void updateMachines() {
        for (Operation operation : machineList.keySet() ) {
            for (Machine machine : machineList.get(operation)) {
                machine.updateMachine();
            }
        }
    }


    private void printMachineStatus() {
        for (Operation operation : machineList.keySet() ) {
            for (Machine machine : machineList.get(operation)) {
                machine.printStatus();
            }
        }
    }

    private boolean checkOperationQueue() {
        for (OperationQueue operationQueue : operationQueueList) {
            if(!operationQueue.isEmpty()){
                return true;
            }
        }
        return false;
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds); // Pausa a execução por x milissegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
