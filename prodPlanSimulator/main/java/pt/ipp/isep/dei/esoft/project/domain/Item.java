package pt.ipp.isep.dei.esoft.project.domain;

import java.util.*;

public class Item {
    private ID id_item;
    private Priority priority;
    private Queue<Operation> operationList;

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


    public Item(ID id_item, Priority priority, Queue<Operation> operationList) {
        this.id_item = id_item;
        this.priority = priority;
        this.operationList = operationList;
    }


    //-- Set
    public void setId_item(ID id_item) {
        this.id_item = id_item;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    //-- Gets
    public ID getId_item() {
        return id_item;
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
        return Objects.equals(id_item, item.id_item) && priority == item.priority && Objects.equals(operationList, item.operationList);
    }

    @Override
    public int hashCode() {
        return id_item.hashCode();
    }

    public Item clone(){
        return new Item(id_item, priority, operationList);
    }
}
