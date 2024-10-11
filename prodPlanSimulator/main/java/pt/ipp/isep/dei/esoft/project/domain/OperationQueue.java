package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a queue of items for a specific operation.
 * This class manages the queue of items that need to be processed for a given operation.
 */
public class OperationQueue {
    private final Operation operation;
    private final Queue<Item> itemList;

    /**
     * Constructs an OperationQueue for a specified operation.
     *
     * @param operation the operation associated with this queue.
     */
    public OperationQueue(Operation operation) {
        this.operation = operation;
        itemList = new LinkedList<>();
    }

    /**
     * Gets the operation associated with this queue.
     *
     * @return the operation associated with this queue.
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Adds an item to the end of the queue.
     *
     * @param item the item to be added to the queue.
     */
    public void addItemToQueue(Item item) {
        itemList.add(item);
    }

    /**
     * Retrieves and removes the next item from the queue.
     *
     * @return the next item in the queue, or null if the queue is empty.
     */
    public Item getNextItem(){
        return itemList.poll();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty; false otherwise.
     */
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

}
