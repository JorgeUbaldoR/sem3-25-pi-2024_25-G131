package pt.ipp.isep.dei.esoft.project.domain;

import java.util.*;

/**
 * Represents a queue of items for a specific operation.
 * This class manages the queue of items that need to be processed for a given operation.
 * It supports both a priority-based queue (using {@link PriorityQueue}) and a regular queue (using {@link LinkedList}),
 * depending on the priority flag.
 */
public class OperationQueue {
    private final Operation operation;
    private final Queue<Item> itemList;
    private final boolean priority;

    /**
     * Constructs an OperationQueue for a specified operation.
     * <p>
     * If the priority flag is set to true, the queue will be a PriorityQueue,
     * which will order items based on their PRIORITY order.
     * Otherwise, it will be a standard FIFO queue (LinkedList).
     *
     * @param operation the operation associated with this queue.
     * @param priority  if true, the queue will be a priority queue; otherwise, a FIFO queue.
     */
    public OperationQueue(Operation operation, boolean priority) {
        this.operation = operation;
        this.priority = priority;
        if (this.priority) {
            itemList = new PriorityQueue<>();
        } else {
            itemList = new LinkedList<>();
        }
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
     * <p>
     * Conditions:
     * 1. The item cannot be null.
     * 2. The item's current operation must match the operation of this queue.
     * 3. The item must not already be present in the queue.
     *
     * @param item the Item to be added to the queue.
     * @throws IllegalArgumentException if the item is null, if the item's current operation does not match the queue's operation,
     *                                  or if the item is already present in the queue.
     */
    public void addItemToQueue(Item item) {
        checkAdd(item);
        itemList.offer(item);
    }

    /**
     * Checks whether an item can be added to the queue.
     * It ensures that the item is valid and meets the queue's criteria.
     *
     * @param item the Item to check.
     * @throws IllegalArgumentException if the item is null, if the item's current operation does not match the queue's operation,
     *                                  or if the item is already present in the queue.
     */
    private void checkAdd(Item item) {
        if (item == null) throw new IllegalArgumentException("Item passed can't be null");
        if (!checkOperation(item))
            throw new IllegalArgumentException("Can't add item (" + item.getItemID() + ") to queue - check next operation of item it must be: " + operation);
        if (itemList.contains(item))
            throw new IllegalArgumentException("Can't add item (" + item.getItemID() + ") to queue - item already exists in queue for: " + operation);

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
     *
     * @param o the object to be compared for equality with this OperationQueue.
     * @return {@code true} if the specified object is equal to this OperationQueue;
     *         {@code false} otherwise.
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
     *
     * @return a hash code value for this OperationQueue.
     */
    @Override
    public int hashCode() {
        return Objects.hash(operation, itemList);
    }


    /**
     * Returns a string representation of the OperationQueue.
     * <p>
     * The string includes the operation name and a list of item IDs currently in the queue.
     * Each item ID is separated by a comma. The format of the output is as follows:
     * <pre>
     *    📝 OperationName: [ItemID1, ItemID2, ..., ItemIDn]
     * </pre>
     *
     * @return a formatted string representing the operation and its associated items in the queue.
     */
    @Override
    public String toString() {
        if (priority) {
            List<Item> orderList = new ArrayList<>(itemList);
            orderList.sort(new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    return o1.compareTo(o2);
                }
            });

            return doString(orderList);
        }
        return doString(new ArrayList<>(itemList));
    }

    /**
     * Helper method to create the string representation of the item list.
     *
     * @param itemList the list of items to be represented as a string.
     * @return the string representation of the item list.
     */
    private String doString(List<Item> itemList) {
        int num = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("   📝 ").append(operation.getOperationName()).append(": [");
        for (Item item : itemList) {
            if (num != itemList.size() - 1) {
                sb.append(item.getItemID().getKeyID()).append(", ");
            } else {
                sb.append(item.getItemID().getKeyID());
            }
            num++;
        }
        sb.append("]");
        return sb.toString();
    }

    public Queue<Item> getItemList() {
        return itemList;
    }
}
