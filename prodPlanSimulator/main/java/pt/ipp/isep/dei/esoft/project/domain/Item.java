package pt.ipp.isep.dei.esoft.project.domain;

import java.util.*;

public class Item {
    private ID itemID;
    private Priority priority;
    private Queue<Operation> operationList;
    private Operation currentOperation;

    public enum Priority{
        HIGH{
            @Override
            public String toString() {
                return "High";
            }
        },
        MEDIUM{
            @Override
            public String toString() {
                return "Medium";
            }
        },
        LOW{
            @Override
            public String toString() {
                return "Low";
            }
        }
    }


    public Item(ID itemID, Priority priority, Queue<Operation> operationList) {
        this.itemID = itemID;
        this.priority = priority;
        this.operationList = operationList;
        this.currentOperation = operationList.poll();
    }


    //-- Set
    public void setItemID(ID itemID) {
        this.itemID = itemID;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    //-- Gets
    public ID getItemID() {
        return itemID;
    }
    public Priority getPriority() {
        return priority;
    }
    public Queue<Operation> getOperationList() {
        return operationList;
    }

    public Operation showNextOperation() {
        return operationList.peek();
    }


    //-- Methods of class Machine
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;
        return Objects.equals(itemID, item.itemID) && priority == item.priority && Objects.equals(operationList, item.operationList);
    }

    @Override
    public int hashCode() {
        return itemID.hashCode();
    }

    public Item clone(){
        return new Item(itemID, priority, operationList);
    }
}
