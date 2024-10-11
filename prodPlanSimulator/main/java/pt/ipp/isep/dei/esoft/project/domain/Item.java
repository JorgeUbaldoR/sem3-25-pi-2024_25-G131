package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.util.*;

/**
 * Represents an item that has a unique ID, a priority level,
 * and a list of operations it needs to undergo.
 */
public class Item {
    private ID itemID;
    private Priority priority;
    private Queue<Operation> operationList;
    private Operation currentOperation;


    /**
     * Constructs an Item with the specified ID, priority, and list of operations.
     *
     * @param itemID        the unique identifier for the item.
     * @param priority      the priority level of the item.
     * @param operationList the queue of operations that the item will undergo.
     */
    public Item(ID itemID, Priority priority, Queue<Operation> operationList) {
        this.itemID = itemID;
        this.priority = priority;
        this.operationList = operationList;
        this.currentOperation = operationList.peek();
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
     * Gets the next operation in the queue for this item.
     *
     * @return the next operation, or null if there are no more operations.
     */
    public Operation getNextOperation() {
        if (!operationList.isEmpty()) {
            operationList.poll();
            this.currentOperation = operationList.peek();
            return this.currentOperation;
        }
        return null;
    }

    /**
     * Creates a clone of this item.
     *
     * @return a new Item object that is a copy of this item.
     */
    public Item clone() {
        return new Item(itemID, priority, new LinkedList<>(operationList));
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
}