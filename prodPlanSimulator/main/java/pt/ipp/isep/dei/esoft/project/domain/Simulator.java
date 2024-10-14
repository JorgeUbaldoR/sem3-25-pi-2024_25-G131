package pt.ipp.isep.dei.esoft.project.domain;


import java.util.*;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class Simulator {
    /**
     * A Simulator class simulates a manufacturing process where items are processed
     * by machines according to specified operations.
     */
    private final Map<Operation, Queue<Machine>> machineList;
    private final List<OperationQueue> operationQueueList;

    private final Map<Operation, Float> operationTime;

    /**
     * Constructs a Simulator instance with the provided machines, items, and operations.
     *
     * @param machines   a map of operations to queues of machines available for processing.
     * @param items      a list of items to be processed in the simulation.
     * @param operations a list of operations to be executed during the simulation.
     * @throws IllegalArgumentException if any of the provided lists are null or empty.
     */
    public Simulator(Map<Operation, Queue<Machine>> machines, List<Item> items, List<Operation> operations) {
        checkInformation(machines, operations, items);
        this.machineList = machines;
        this.operationQueueList = new ArrayList<>();
        addOperationToQueue(operations);
        createQueues(items);
        this.operationTime = new HashMap<>();
    }


    /**
     * Constructs a Simulator instance with default settings.
     * Initializes an empty map for machine operations and an empty list for operation queues.
     */
    public Simulator() {
        this.machineList = new HashMap<>();
        this.operationQueueList = new ArrayList<>();
        this.operationTime = new HashMap<>();
    }


    /**
     * Adds a list of operations to the operation queue list, creating
     * an OperationQueue for each operation.
     *
     * @param operations the list of operations to be added to the operation queue.
     */
    public List<OperationQueue> addOperationToQueue(List<Operation> operations) {
        for (Operation operation : operations) {
            operationQueueList.add(new OperationQueue(operation));
        }
        return operationQueueList;
    }


    /**
     * Creates operation queues for the given items based on their current operation.
     *
     * @param items a list of items to be added to the operation queues.
     * @return a list of operation queues populated with items.
     */
    public List<OperationQueue> createQueues(List<Item> items) {
        for (OperationQueue operationQueue : operationQueueList) {
            Operation currentOperation = operationQueue.getOperation();
            for (Item item : items) {
                if (currentOperation.equals(item.getCurrentOperation())) {
                    operationQueue.addItemToQueue(item);
                }
            }
        }
        return operationQueueList;
    }


    /**
     * Starts the simulation process, incrementing time and processing items
     * until all operations are completed.
     */
    public void startSimulation() {
        int time = 0;
        while (checkOperationQueue() || checkTimeOperations()) {
            System.out.printf("%s===========================================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
            System.out.printf("%s||%s              %sSIMULATION - TIME: %d%s                    %s||%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, time, ANSI_RESET, ANSI_BRIGHT_BLACK, ANSI_RESET);
            System.out.printf("%s===========================================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
            System.out.printf("%n• Updates:%n");
            updateMachines();
            System.out.printf("%n• Queue:%n");
            printQueue();
            System.out.printf("%n• Status:%n");
            printMachineStatus();
            System.out.printf("%n• New Processing:%n");
            for (OperationQueue operationQueue : operationQueueList) {
                if (!operationQueue.isEmpty()) {
                    assignItemToMachine(operationQueue, machineList.get(operationQueue.getOperation()));
                }
            }

            System.out.printf("%n%s===========================================================%s%n%n%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
            time++;
//            sleep(1500);
        }
        System.out.printf("%s✅ All operations completed! %s%n", ANSI_GREEN, ANSI_RESET);
        printExecutionTimesOperation();
    }

    /**
     * Prints the current state of all operation queues to the console.
     * <p>
     * This method iterates through the list of operation queues and
     * prints the string representation of each queue using the
     * {@link OperationQueue#toString()} method. Each queue will be
     * displayed on a new line, providing a clear overview of the
     * items waiting to be processed in each operation queue.
     * </p>
     */
    private void printQueue() {
        for (OperationQueue operationQueue : operationQueueList) {
            System.out.printf("%s%n",operationQueue.toString());
        }
    }


    /**
     * Assigns the next item from the operation queue to an available machine.
     *
     * @param operationQueue the operation queue from which the item is retrieved.
     * @param machineList    the queue of machines available for processing the item.
     */
    public void assignItemToMachine(OperationQueue operationQueue, Queue<Machine> machineList) {
        if (!machineList.isEmpty()) {
            for (Machine currentMachine : machineList) {
                if (currentMachine.isAvailable() && !operationQueue.isEmpty()) {
                    currentMachine.processItem(operationQueue.getNextItem());
                }
            }
        }
    }


    /**
     * Updates the status of the machines and assigns the next operation
     * for any items that have finished processing.
     */
    private void updateMachines() {
        for (Operation operation : machineList.keySet()) {
            for (Machine machine : machineList.get(operation)) {
                boolean finished = machine.updateMachine();
                if (finished) {
                    addExecutionTimesOperation(operation, machine.getProcessingSpeed());
                    Item currentItem = machine.getCurrentProcessingItem();
                    Operation newOperation = currentItem.getNextOperation();
                    if (newOperation != null) {
                        OperationQueue operationQueue = findOperationInQueue(newOperation);
                        if (operationQueue != null) {
                            operationQueue.addItemToQueue(currentItem);
                        }
                    }
                }
            }
        }
    }

    /**
     * Finds the operation queue corresponding to the given operation.
     *
     * @param newOperation the operation to search for.
     * @return the operation queue associated with the operation, or null if not found.
     */
    private OperationQueue findOperationInQueue(Operation newOperation) {
        for (OperationQueue operationQueue : operationQueueList) {
            if (operationQueue.getOperation().equals(newOperation)) {
                return operationQueue;
            }
        }
        return null;
    }

    /**
     * Prints the status of all machines in the simulator.
     */
    private void printMachineStatus() {
        for (Operation operation : machineList.keySet()) {
            for (Machine machine : machineList.get(operation)) {
                machine.printStatus();
            }
        }
    }

    /**
     * Checks if there are any items left in the operation queues.
     *
     * @return true if there are items in any of the operation queues; false otherwise.
     */
    private boolean checkOperationQueue() {
        for (OperationQueue operationQueue : operationQueueList) {
            if (!operationQueue.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if any machines are still processing items.
     *
     * @return true if any machine has time left to finish processing; false otherwise.
     */
    public boolean checkTimeOperations() {
        List<Machine> machines = new ArrayList<>();
        for (Queue<Machine> machine : machineList.values()) {
            machines.addAll(machine);
        }
        for (Machine machine : machines) {
            if (machine.getTimeLeftToFinish() != 0)
                return true;
        }
        return false;
    }

    /**
     * Puts the current thread to sleep for the specified duration.
     *
     * @param milliseconds the duration in milliseconds for which the thread should sleep.
     */
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted", e);
        }
    }

    /**
     * Checks the provided lists for null or empty values and throws an exception if any are found.
     *
     * @param machines   the map of machines to check.
     * @param operations the list of operations to check.
     * @param items      the list of items to check.
     * @throws IllegalArgumentException if any of the lists are null or empty.
     */
    private void checkInformation(Map<Operation, Queue<Machine>> machines, List<Operation> operations, List<Item> items) {
        if (machines == null || machines.isEmpty()) {
            throw new IllegalArgumentException("Machine list is null or empty");
        }
        if (operations == null || operations.isEmpty()) {
            throw new IllegalArgumentException("Operations list is null or empty");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items list is null or empty");
        }
    }


    /**
     * Adds the execution time of a given operation to the map. If the operation already has recorded time,
     * the new time is added to the existing time; otherwise, the operation is added with the provided time.
     *
     * @param op   the operation for which the execution time is being recorded.
     * @param time the time to add to the execution time of the operation.
     */
    private void addExecutionTimesOperation(Operation op, float time) {
        if (!this.operationTime.containsKey(op)) {
            operationTime.put(op, time);
        } else {
            float currentTime = operationTime.get(op) + time;
            operationTime.put(op, currentTime);
        }
    }


    /**
     * Prints the execution times for all operations and percentages relative to the total time to the console.
     * Each operation and its corresponding execution time is printed.
     */
    private void printExecutionTimesOperation() {
        List<Map.Entry<Operation, Float>> list = ascendingOrder();
        float totalTime = sumTotalTime();

        System.out.println("\n\n═══════════════════════════════════════════════");
        System.out.print(ANSI_BRIGHT_WHITE + "               Statistics                 " + ANSI_RESET + "\n");
        System.out.printf("%n%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s%s%s  %-13s %6s %s%10s%s%n", ANSI_BRIGHT_BLACK, "||", ANSI_RESET, "Operation", " Time(min)",ANSI_BRIGHT_BLACK, "  Percentages" , ANSI_RESET);
        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);

        for (Map.Entry<Operation, Float> entry : list) {
            float percentage = (entry.getValue() / totalTime) * 100;
            System.out.printf("%s%s%s  %-15s %s %s%-7.2f%s %s(%5.2f%%) %s%s%n",
                    ANSI_BRIGHT_BLACK, "||", ANSI_RESET,
                    entry.getKey().getOperationName(),
                    "->",
                    ANSI_BRIGHT_WHITE, entry.getValue(), ANSI_RESET,
                    ANSI_BRIGHT_BLACK, percentage,
                    ANSI_RESET, ANSI_BRIGHT_BLACK, "||", ANSI_RESET);
        }

        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
    }

    /**
     *
     * @return a list where all operations are sorted by their time, in ascending order.
     */
    private List<Map.Entry<Operation, Float>> ascendingOrder() {
        List<Map.Entry<Operation, Float>> list = new ArrayList<>(getExecutionTimesOperation().entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return list;
    }

    /**
     *
     * @return the sum of all operation's times.
     */
    private float sumTotalTime() {
        float sum = 0;
        List<Map.Entry<Operation, Float>> list = ascendingOrder();
        for (Map.Entry<Operation, Float> entry : list) {
            sum += entry.getValue();
        }
        return sum;
    }


    /**
     * Retrieves the execution times for all operations.
     *
     * @return a map containing operations and their corresponding execution times.
     */
    public Map<Operation, Float> getExecutionTimesOperation() {
        return this.operationTime;
    }
}
