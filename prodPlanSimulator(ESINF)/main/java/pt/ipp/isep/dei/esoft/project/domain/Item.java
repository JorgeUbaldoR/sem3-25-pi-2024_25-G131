package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;

import java.util.*;

/**
 * Represents an item that has a unique ID, a priority level,
 * and a list of operations it needs to undergo.
 */
public class Item implements Comparable<Item> {

    private final String name;
    private final ID itemID;
    private final Priority priority;
    private Queue<Operation> operationList;
    private Operation currentOperation;


    /**
     * Constructs an Item with the specified ID, priority, and list of operations.
     *
     * @param itemID        the unique identifier for the item.
     * @param priority      the priority level of the item.
     * @param operationList the queue of operations that the item will undergo.
     * @throws IllegalArgumentException if itemID, priority, or operationList is null.
     */
    public Item(ID itemID, Priority priority, Queue<Operation> operationList) {
        checkInfomation(itemID, priority, operationList);
        this.name = "Without Name";
        this.itemID = itemID;
        this.priority = priority;
        this.operationList = new LinkedList<>(operationList);
        this.currentOperation = this.operationList.peek();
    }

    public Item(ID itemID,String name, Priority priority, Queue<Operation> operationList) {
        checkInfomation(itemID, priority, operationList);
        this.name = name;
        this.itemID = itemID;
        this.priority = priority;
        this.operationList = new LinkedList<>(operationList);
        this.currentOperation = this.operationList.peek();
    }

    /**
     * Constructs an Item with the specified ID,name, default priority, and list of operations.
     *
     * @param itemID        the unique identifier for the item.
     * @param name          the name of the item.
     * @throws IllegalArgumentException if itemID is null.
     */
    public Item(ID itemID,String name) {
        checkInfomation(itemID, Priority.NORMAL, new LinkedList<>());
        this.name = name;
        this.itemID = itemID;
        this.priority = Priority.LOW;
        this.operationList = new LinkedList<>();
        this.currentOperation = null;
    }

    /**
     * Checks the validity of the provided item information.
     *
     * @param itemID        the unique identifier for the item.
     * @param priority      the priority level of the item.
     * @param operationList the queue of operations that the item will undergo.
     * @throws IllegalArgumentException if itemID, priority, or operationList is null.
     */
    public void checkInfomation(ID itemID, Priority priority, Queue<Operation> operationList) {
        if (itemID == null) throw new IllegalArgumentException("Can't create Item because ID is null");
        if (priority == null) throw new IllegalArgumentException("Can't create Item because Priority is null");
        if (operationList == null) throw new IllegalArgumentException("Can't create Item because operation list is null");
    }


    /**
     * Gets the unique identifier for this item.
     *
     * @return the item's ID.
     */
    public ID getItemID() {
        return itemID;
    }

    /**
     * Gets the current operation that this item is undergoing.
     *
     * @return the current operation, or null if there is none.
     */
    public Operation getCurrentOperation() {
        return currentOperation;
    }

    /**
     * Gets the priority of operation.
     *
     * @return the priority.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Gets the next operation in the queue for this item.
     *
     * @return the next operation, or null if there are no more operations.
     */
    public Operation getNextOperation() {
        if (!operationList.isEmpty() ) {
            operationList.poll();
            this.currentOperation = operationList.peek();
            return this.currentOperation;
        }
        return null;
    }

    /**
     * Gets the item name.
     *
     * @return the item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Creates a clone of this item.
     *
     * @return a new Item object that is a copy of this item.
     */
    public Item clone() {
        return new Item(itemID,name,priority, new LinkedList<>(operationList));
    }

    /**
     * Compares this item to the specified object for equality.
     * Two items are considered equal if they have the same ID.
     *
     * @param o the object to compare this item against.
     * @return true if the specified object is equal to this item; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;
        return itemID.equals(item.itemID);
    }

    /**
     * Returns a hash code value for this item.
     * The hash code is based on the item's unique ID.
     *
     * @return a hash code value for this item.
     */
    @Override
    public int hashCode() {
        return itemID.hashCode();
    }

    /**
     * Returns a string representation of this item.
     * The string includes the item's ID, priority, and the next operation in the queue.
     *
     * @return a string representation of the item.
     */
    @Override
    public String toString() {
        return String.format("%s -> %s -> %s ", itemID, priority, operationList.peek());
    }

    public List<Operation> getOperationList() {
        return List.copyOf(operationList);
    }

    @Override
    public int compareTo(Item o) {
        int priorityComparison = this.priority.compareTo(o.priority);
        if(priorityComparison != 0)
            return priorityComparison;
        return this.itemID.compareTo(o.itemID);
    }
}