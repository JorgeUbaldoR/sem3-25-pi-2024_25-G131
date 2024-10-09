package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.util.*;

public class Item {
    private ID itemID;
    private Priority priority;
    private Queue<Operation> operationList;
    private Operation currentOperation;



    public Item(ID itemID, Priority priority, Queue<Operation> operationList) {
        this.itemID = itemID;
        this.priority = priority;
        this.operationList = operationList;
        this.currentOperation = null;
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

    public Operation getNextOperation() {
        if(!operationList.isEmpty()){
            this.currentOperation = operationList.poll();
            return this.currentOperation;
        }
        return null;
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
