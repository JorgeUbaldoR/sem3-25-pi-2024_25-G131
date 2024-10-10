package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.util.LinkedList;
import java.util.Queue;

public class OperationQueue {
    private Operation operation;
    private Queue<Item> itemList;

    public OperationQueue(Operation operation) {
        this.operation = operation;
        itemList = new LinkedList<>();
    }

    public Operation getOperation() {
        return operation;
    }

    public void addItemToQueue(Item item) {
        itemList.add(item);
    }

    public Item getNextItem(){
        return itemList.poll();
    }

    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    public Queue<Item> getItemList() {
        return itemList;
    }
}
