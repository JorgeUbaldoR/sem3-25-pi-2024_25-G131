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
    private final Map<Item, Float> waitingTime;
    private final Map<Operation, Float> avgExecutionTime;
    private final LinkedList<Item> itemLinkedList;
    public final Map<Item, LinkedList<ID>> itemLinkedListMap;
    private final Map<Machine, Float> machineUsage;
    private final Map<Operation, Integer> executionPerOperation;

    /**
     * Constructs a Simulator instance with the provided machines, items, and operations.
     * Initializes the machine list, operation queue list, and operation time map.
     *
     * @param machines     a map of operations to queues of machines available for processing.
     * @param items        a list of items to be processed in the simulation.
     * @param operations   a list of operations to be executed during the simulation.
     * @param priorityFlag a flag indicating whether priority should be considered in queue processing.
     * @throws IllegalArgumentException if any of the provided lists are null or empty.
     */
    public Simulator(Map<Operation, Queue<Machine>> machines, List<Item> items, List<Operation> operations, boolean priorityFlag) {
        checkInformation(machines, operations, items);
        this.machineList = machines;
        this.operationQueueList = new ArrayList<>();
        this.operationTime = new HashMap<>();
        this.waitingTime = new HashMap<>();
        this.itemLinkedList = new LinkedList<>();
        this.itemLinkedListMap = new HashMap<>();
        this.machineUsage = new HashMap<>();
        this.avgExecutionTime = new HashMap<>();
        this.executionPerOperation = new HashMap<>();
        addOperationToQueue(operations, priorityFlag);
        createQueues(items);
    }


    /**
     * Constructs a Simulator instance with default settings.
     * Initializes an empty map for machine operations and an empty list for operation queues.
     */
    public Simulator() {
        this.machineList = new HashMap<>();
        this.operationQueueList = new ArrayList<>();
        this.operationTime = new HashMap<>();
        this.waitingTime = new HashMap<>();
        this.itemLinkedList = new LinkedList<>();
        this.itemLinkedListMap = new HashMap<>();
        this.machineUsage = new HashMap<>();
        this.avgExecutionTime = new HashMap<>();
        this.executionPerOperation = new HashMap<>();
    }


    /**
     * Adds a list of operations to the operation queue list, creating
     * an OperationQueue for each operation.
     *
     * @param operations   the list of operations to be added to the operation queue.
     * @param priorityFlag a flag indicating whether priority should be applied to the queues.
     */
    private void addOperationToQueue(List<Operation> operations, boolean priorityFlag) {
        for (Operation operation : operations) {
            operationQueueList.add(new OperationQueue(operation, priorityFlag));
        }
    }


    /**
     * Creates operation queues for the given items based on their current operation.
     * Items are assigned to the appropriate operation queue according to their current operation.
     *
     * @param items a list of items to be added to the operation queues.
     */
    private void createQueues(List<Item> items) {
        for (OperationQueue operationQueue : operationQueueList) {
            Operation currentOperation = operationQueue.getOperation();
            for (Item item : items) {
                if (currentOperation.equals(item.getCurrentOperation())) {
                    operationQueue.addItemToQueue(item);
                }
            }
        }
    }


    /**
     * Starts the simulation process, incrementing time and processing items
     * until all operations and machine processes are completed.
     */
    public void startSimulation() {
        int time = 0;
        while (checkOperationQueue() || checkTimeOperations()) {
            System.out.printf("%s===========================================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
            System.out.printf("%s||%s              %sSIMULATION - TIME: %d%s                     %s||%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, time, ANSI_RESET, ANSI_BRIGHT_BLACK, ANSI_RESET);
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

            if (time > 0) {
                fillWaitingTime(operationQueueList);
            }

            System.out.printf("%n%s===========================================================%s%n%n%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
            time++;
            //sleep(1000);
        }
        System.out.printf("%s✅ All operations completed! %s%n", ANSI_GREEN, ANSI_RESET);
        printStatistics();

    }

    /**
     * Print statistical information.
     */
    private void printStatistics() {
        printExecutionTimesMachine();
        printExecutionTimesOperation();
        printAverageExecutionTime();
        printWaitingTime();
        printItemRoute();
        printMachineRoute();
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
            System.out.printf("%s%n", operationQueue.toString());
        }
    }


    /**
     * Assigns the next item from the operation queue to an available machine.
     *
     * @param operationQueue the operation queue from which the item is retrieved.
     * @param machineList    the queue of machines available for processing the item.
     */
    private void assignItemToMachine(OperationQueue operationQueue, Queue<Machine> machineList) {
        if (machineList != null && !machineList.isEmpty()) {
            for (Machine currentMachine : machineList) {
                if (currentMachine.isAvailable() && !operationQueue.isEmpty()) {
                    currentMachine.processItem(operationQueue.getNextItem());
                    fillExecutionPerOperation(currentMachine.getOperation());
                }
            }
        } else {
            operationQueue.getNextItem();
        }
    }


    /**
     * Updates the status of the machines and assigns the next operation
     * for any items that have finished processing.
     */
    private void updateMachines() {
        for (Operation operation : machineList.keySet()) {
            Queue<Machine> machines = machineList.get(operation);
            for (Machine machine : machines) {
                boolean finished = machine.updateMachine();
                if (finished) {
                    addExecutionTimesOperation(operation, machine.getProcessingSpeed());
                    addExecutionTimesMachine(machine, machine.getProcessingSpeed());
                    Item currentItem = machine.getCurrentProcessingItem();
                    Operation newOperation = currentItem.getNextOperation();
                    itemLinkedListMap.putIfAbsent(currentItem, new LinkedList<>());
                    itemLinkedListMap.get(currentItem).add(machine.getId_machine());
                    if (newOperation != null) {
                        OperationQueue operationQueue = findOperationInQueue(newOperation);
                        if (operationQueue != null) {
                            operationQueue.addItemToQueue(currentItem);
                        }
                    } else {
                        itemLinkedList.add(currentItem);
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
    private boolean checkTimeOperations() {
        for (Queue<Machine> machines : machineList.values()) {
            for (Machine machine : machines) {
                if (machine.getTimeLeftToFinish() != 0)
                    return true;
            }
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
     * Adds or updates the execution time for a specific machine.
     *
     * @param m    The machine for which the execution time is being added or updated.
     * @param time The time (in minutes) to be added to the machine's total execution time.
     */
    private void addExecutionTimesMachine(Machine m, float time) {
        if (!this.machineUsage.containsKey(m)) {
            machineUsage.put(m, time);
        } else {
            float currentTime = machineUsage.get(m) + time;
            machineUsage.put(m, currentTime);
        }
    }


    /**
     * Prints the execution times for all operations and percentages relative to the total time to the console.
     * Each operation and its corresponding execution time is printed.
     */
    public void printExecutionTimesOperation() {
        List<Map.Entry<Operation, Float>> list = ascendingOrderOperationTimes();
        float totalTime = sumTotalTime();

        System.out.printf("%n%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s%s%s%s %-13s %6s %15s %s%s%3s%s%n",
                ANSI_BRIGHT_BLACK, "||", ANSI_RESET,
                ANSI_BRIGHT_WHITE,
                "Operation",
                " Time(min)",
                "Percentages", ANSI_RESET,
                ANSI_BRIGHT_BLACK, "||", ANSI_RESET);
        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);

        for (Map.Entry<Operation, Float> entry : list) {
            float percentage = (entry.getValue() / totalTime) * 100;
            System.out.printf("%s%s%s  %-14s %-13.2f %.2f %s%n",
                    ANSI_BRIGHT_BLACK, "||", ANSI_RESET,
                    entry.getKey().getOperationName(),
                    entry.getValue(),
                    percentage, "%");
        }

        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
    }

    /**
     * Prints the execution times of each machine along with their percentages of total execution time.
     * It displays the data in a formatted table.
     */
    private void printExecutionTimesMachine() {
        List<Map.Entry<Machine, Float>> list = ascendingOrderMachineTimes();
        float totalTime = sumTotalTime();


        System.out.println("\n\n═══════════════════════════════════════════════");
        System.out.print(ANSI_BRIGHT_WHITE + "                  Statistics                 " + ANSI_RESET + "\n");

        System.out.printf("%n%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s%s%s%s %-13s %6s %15s %s%s%3s%s%n",
                ANSI_BRIGHT_BLACK, "||", ANSI_RESET,
                ANSI_BRIGHT_WHITE,
                "Workstation",
                " Time(min)",
                "Percentages", ANSI_RESET,
                ANSI_BRIGHT_BLACK, " ||", ANSI_RESET);
        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);

        for (Map.Entry<Machine, Float> entry : list) {
            float percentage = (entry.getValue() / totalTime) * 100;
            System.out.printf("%s%s%s  %-14s %-15.2f %.2f %s%n",
                    ANSI_BRIGHT_BLACK, "||", ANSI_RESET,
                    entry.getKey().getId_machine(),
                    entry.getValue(),
                    percentage, "%");
        }

        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);

    }

    /**
     * Returns a list where all operations are sorted by their time, in descending order.
     *
     * @return A list of entries (key-value pairs) where the key is an operation (Operation)
     * and the value is the operation time (Float), sorted in descending order of time.
     */
    private List<Map.Entry<Operation, Float>> ascendingOrderOperationTimes() {
        List<Map.Entry<Operation, Float>> list = new ArrayList<>(getExecutionTimesOperation().entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return list;
    }

    /**
     * Returns a list where all machines are sorted by their execution time, in descending order.
     *
     * @return A list of entries (key-value pairs), where the key is the machine (Machine)
     * and the value is the execution time (Float), sorted in descending order of time.
     */
    private List<Map.Entry<Machine, Float>> ascendingOrderMachineTimes() {
        List<Map.Entry<Machine, Float>> list = new ArrayList<>(getExecutionTimesMachine().entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return list;
    }

    /**
     * Sorts the average execution time entries in descending order.
     *
     * @return A list of entries sorted by their average execution time in descending order.
     */
    List<Map.Entry<Operation, Float>> ascendingOrderAvgExecutionTime() {
        List<Map.Entry<Operation, Float>> list = new ArrayList<>(getAvgExecutionTime().entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return list;
    }

    /**
     * Sorts the waiting time entries in descending order.
     *
     * @return A list of entries sorted by their waiting time in descending order.
     */
    List<Map.Entry<Item, Float>> ascendingOrderWaitingTime() {
        List<Map.Entry<Item, Float>> list = new ArrayList<>(getWaitingTime().entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return list;
    }


    /**
     * @return the sum of all operation's times.
     */
    private float sumTotalTime() {
        float sum = 0;
        List<Map.Entry<Operation, Float>> list = ascendingOrderOperationTimes();
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

    /**
     * Retrieves the map that contains the execution times for each machine.
     *
     * @return A map where the keys are Machine objects and the values are the execution times (in minutes) for each machine.
     */
    public Map<Machine, Float> getExecutionTimesMachine() {
        return this.machineUsage;
    }

    /**
     * Retrieves the map containing the average execution times for each operation.
     *
     * @return A map where the keys are Operations and the values are their average execution times in minutes.
     */
    public Map<Operation, Float> getAvgExecutionTime() {
        return this.avgExecutionTime;
    }

    /**
     * Retrieves the map containing the waiting times for each item.
     *
     * @return A map where the keys are Items and the values are their waiting times in minutes.
     */
    public Map<Item, Float> getWaitingTime() {
        return this.waitingTime;
    }

    /**
     * Retrieves the map containing the number of executions for each operation.
     *
     * @return A map where the keys are Operations and the values are their execution counts.
     */
    public Map<Operation, Integer> getExecutionPerOperation() {
        return executionPerOperation;
    }

    /**
     * Retrieves the map containing the total execution time for each operation.
     *
     * @return A map where the keys are Operations and the values are their total execution times in minutes.
     */
    public Map<Operation, Float> getPpk() {
        return operationTime;
    }

    /**
     * Prints the route of a item
     */
    public void printItemRoute() {
        System.out.printf("%n%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s%s%s%s %-13s %17s %s%s%3s%n",
                ANSI_BRIGHT_BLACK, "||", ANSI_RESET,
                ANSI_BRIGHT_WHITE,
                "Item",
                "Item Route"
                , ANSI_RESET,
                ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        for (Map.Entry<Item, LinkedList<ID>> entry : itemLinkedListMap.entrySet()) {
            for (Item it : itemLinkedList) {
                if (entry.getKey().equals(it)) {
                    System.out.printf("%s%s%s  %-17s %s %n", ANSI_BRIGHT_BLACK, "||", ANSI_RESET, entry.getKey().getItemID(), entry.getValue());
                }
            }
        }
        System.out.printf("%n%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
    }

    /**
     * Populates the executionPerOperation map with the number of executions for each operation.
     * If the operation is already present, increments its count by 1.
     *
     * @param operation The operation whose execution count is being tracked.
     */
    void fillExecutionPerOperation(Operation operation) {
        if (!executionPerOperation.containsKey(operation)) {
            executionPerOperation.put(operation, 1);
        } else {
            executionPerOperation.put(operation, executionPerOperation.get(operation) + 1);
        }
    }

    /**
     * Calculates the average execution time for each operation and stores it in avgExecutionTime.
     * Divides the total execution time of each operation by its number of executions.
     */
    void calculateAverageExecutionTimes() {
        float totalTime;
        int qtdOperations;

        for (Map.Entry<Operation, Float> entry : operationTime.entrySet()) {
            Operation operation = entry.getKey();
            totalTime = entry.getValue();
            qtdOperations = executionPerOperation.get(operation);

            avgExecutionTime.put(operation, (totalTime / qtdOperations));
        }
    }


    /**
     * Prints the average execution time for each operation in a formatted output.
     * Sorts the list of operations by their average execution time before printing.
     */
    private void printAverageExecutionTime() {
        calculateAverageExecutionTimes();
        List<Map.Entry<Operation, Float>> sortedListAvgExecutionTime = ascendingOrderAvgExecutionTime();

        System.out.printf("%n%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s%s%s%s %-13s %26s %s%s%3s%s%n",
                ANSI_BRIGHT_BLACK, "||", ANSI_RESET,
                ANSI_BRIGHT_WHITE,
                "Operation",
                "Average Time (min)"
                , ANSI_RESET,
                ANSI_BRIGHT_BLACK, "||", ANSI_RESET);
        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);

        for (Map.Entry<Operation, Float> entry : sortedListAvgExecutionTime) {
            System.out.printf("%s%s%s  %-14s %17.2f %n",
                    ANSI_BRIGHT_BLACK, "||", ANSI_RESET,
                    entry.getKey().getOperationName(),
                    entry.getValue());
        }

        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
    }

    /**
     * Fills the waitingTime map with the waiting time of each item in the operation queue list.
     * If an item is already present, increments its waiting time by 1 minute.
     *
     * @param operationQueueList The list of operation queues to process for waiting time.
     */
    void fillWaitingTime(List<OperationQueue> operationQueueList) {
        for (OperationQueue operationQueue : operationQueueList) {
            if (!operationQueue.isEmpty()) {
                Queue<Item> items = operationQueue.getItemList();

                for (Item item : items) {
                    if (!waitingTime.containsKey(item)) {
                        waitingTime.put(item, 1f);
                    } else {
                        waitingTime.put(item, waitingTime.get(item) + 1f);
                    }
                }
            }
        }
    }

    /**
     * Prints the waiting time for each item in a formatted output.
     * Sorts the list of items by their waiting time before printing.
     */
    private void printWaitingTime() {
        List<Map.Entry<Item, Float>> sortedListWaitingTime = ascendingOrderWaitingTime();

        System.out.printf("%n%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s%s%s%s %-13s %26s %s%s%3s%s%n",
                ANSI_BRIGHT_BLACK, "||", ANSI_RESET,
                ANSI_BRIGHT_WHITE,
                "Item",
                "Waiting Time (min)"
                , ANSI_RESET,
                ANSI_BRIGHT_BLACK, "||", ANSI_RESET);
        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);

        for (Map.Entry<Item, Float> entry : sortedListWaitingTime) {
            System.out.printf("%s%s%s  %-14s %17.2f %n",
                    ANSI_BRIGHT_BLACK, "||", ANSI_RESET,
                    entry.getKey().getItemID(),
                    entry.getValue());
        }

        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
    }

    /**
     * Prints the transitions of a machine to another.
     */
    public void printMachineRoute() {

        Map<ID, Map<ID, Integer>> transitionMap = getMachineRoute();

        System.out.printf("%n%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s%s%s%s %-13s %17s %s%s%3s%n",
                ANSI_BRIGHT_BLACK, "||", ANSI_RESET,
                ANSI_BRIGHT_WHITE,
                "Machine ID",
                "Machine Route"
                , ANSI_RESET,
                ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        for (Map.Entry<ID, Map<ID, Integer>> transitionEntry : transitionMap.entrySet()) {
            ID fromMachine = transitionEntry.getKey();
            Map<ID, Integer> toMachines = transitionEntry.getValue();

            List<String> transitionStrings = new ArrayList<>();
            for (Map.Entry<ID, Integer> toMachineEntry : toMachines.entrySet()) {
                ID toMachine = toMachineEntry.getKey();
                int count = toMachineEntry.getValue();
                transitionStrings.add("(" + toMachine.getKeyID()+ "," + count + ")");
            }
            System.out.printf("%s%s%s  %-17s %s %n", ANSI_BRIGHT_BLACK, "||", ANSI_RESET, fromMachine.getKeyID(), String.join(", ", transitionStrings));
        }
        System.out.printf("%s===============================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
    }

    /**
     *
     * @return a Map that stores all the transitions of the machines
     */
    public Map<ID, Map<ID, Integer>> getMachineRoute() {
        Map<ID, Map<ID, Integer>> transitionMap = new HashMap<>();

        for (Map.Entry<Item, LinkedList<ID>> entry : itemLinkedListMap.entrySet()) {
            LinkedList<ID> machineIds = entry.getValue();

            for (int i = 0; i < machineIds.size() - 1; i++) {
                ID fromMachine = machineIds.get(i);
                ID toMachine = machineIds.get(i + 1);

                transitionMap.putIfAbsent(fromMachine, new HashMap<>());

                Map<ID, Integer> toMachineCount = transitionMap.get(fromMachine);

                toMachineCount.put(toMachine, toMachineCount.getOrDefault(toMachine, 0) + 1);
            }
        }
        return transitionMap;
    }

}
