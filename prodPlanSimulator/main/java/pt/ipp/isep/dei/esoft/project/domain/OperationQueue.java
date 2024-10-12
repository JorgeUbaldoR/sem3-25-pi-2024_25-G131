package pt.ipp.isep.dei.esoft.project.domain;

import java.util.LinkedList;
import java.util.Objects;
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
     * Adds an item to the queue if it meets certain conditions.
     *
     * @param item the Item to be added to the queue.
     * @throws IllegalArgumentException if the item is null, if the item's current operation does not match the queue's operation,
     *                                  or if the item is already present in the queue.
     */
    public void addItemToQueue(Item item) {
        checkAdd(item);
        itemList.add(item);
    }

    /**
     * Checks whether an item can be added to the queue.
     *
     * @param item the Item to check.
     * @throws IllegalArgumentException if the item is null, if the item's current operation does not match the queue's operation,
     *                                  or if the item is already present in the queue.
     */
    private void checkAdd(Item item) {
        if (item == null) throw new IllegalArgumentException("Item passed can't be null");
        if (!checkOperation(item)) throw new IllegalArgumentException("Can't add item (" + item.getItemID() + ") to queue - check next operation of item it must be: " + operation);
        if (itemList.contains(item)) throw new IllegalArgumentException("Can't add item (" + item.getItemID() + ") to queue - item already exists in queue for: " + operation);

    }

    /**
     * Checks if the item's current operation matches the operation associated with the queue.
     *
     * @param item the Item to check.
     * @return true if the item's current operation is not null and matches the queue's operation; false otherwise.
     */
    private boolean checkOperation(Item item) {
        return item.getCurrentOperation() != null && item.getCurrentOperation().equals(operation);
    }


    /**
     * Retrieves and removes the next item from the queue.
     *
     * @return the next item in the queue, or null if the queue is empty.
     */
    public Item getNextItem() {
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

    /**
     * Compares this OperationQueue to the specified object for equality.
     * <p>
     * Two OperationQueue objects are considered equal if they are the same instance,
     * or if they are of the same class and both their operation and itemList fields are equal.
     * </p>
     *
     * @param o the object to be compared for equality with this OperationQueue
     * @return {@code true} if the specified object is equal to this OperationQueue;
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationQueue that = (OperationQueue) o;

        return Objects.equals(operation, that.operation) && Objects.equals(itemList, that.itemList);
    }

    /**
     * Returns a hash code value for this OperationQueue.
     * <p>
     * The hash code is computed based on the operation and itemList fields.
     * This is important for using this class in hash-based collections like HashMap.
     * </p>
     *
     * @return a hash code value for this OperationQueue
     */
    @Override
    public int hashCode() {
        return Objects.hash(operation, itemList);
    }

    /**
     * Retorna uma representação em formato de string da fila de operações (OperationQueue).
     * A string contém o nome da operação e a lista de IDs dos itens presentes na fila.
     * O formato é: "Operation: {nome da operação} - Items: [{IDs dos itens}]".
     *
     * @return Uma string representando a operação e os itens na fila.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Operation: ").append(operation).append(" - Items: [");
        for (Item item : itemList) {
            sb.append(item.getItemID().getKeyID()).append(" ");
        }
        sb.append("]\n");
        return sb.toString();
    }
}
