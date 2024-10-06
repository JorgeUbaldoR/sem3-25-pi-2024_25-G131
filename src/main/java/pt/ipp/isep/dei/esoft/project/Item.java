package pt.ipp.isep.dei.esoft.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item {
    private static final Priority DEFAULT_PRIORITY = Priority.LOW;

    private ID id_item;
    private Priority priority;
    private List<Operation> operationList;

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



    public Item(ID id_item, Priority priority, List<Operation> operationList) {
        this.id_item = id_item;
        this.priority = priority;
        this.operationList = operationList;
    }

    public Item(ID id_item) {
        this.id_item = id_item;
        this.priority = DEFAULT_PRIORITY;
        this.operationList = new ArrayList<>();
    }


    public void setId_item(ID id_item) {
        this.id_item = id_item;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    public ID getId_item() {
        return id_item;
    }

    public Priority getPriority() {
        return priority;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public boolean addOperation(Operation operation) {
        return operationList.add(operation);
    }

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


}
