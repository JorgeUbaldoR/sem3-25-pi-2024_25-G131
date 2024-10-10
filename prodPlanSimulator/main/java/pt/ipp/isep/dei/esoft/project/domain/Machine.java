package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.util.Objects;

public class Machine implements Comparable<Machine> {
    private final Boolean DEFAULT_STATUS = true;
    private final float DEFAULT_TIME_LEFT = 0;
    private final Item DEFAULT_ITEM = null;

    private ID id_machine;
    private Operation operation;
    private float processingSpeed;
    private boolean available;
    private float timeLeftToFinish;
    private Item currentProcessingItem;



    public Machine(ID id_machine, Operation operation, float processingSpeed) {
        this.id_machine = id_machine;
        this.operation = operation;
        this.processingSpeed = processingSpeed;
        this.available = DEFAULT_STATUS;
        this.timeLeftToFinish = DEFAULT_TIME_LEFT;
        this.currentProcessingItem = DEFAULT_ITEM;
    }

    public void processItem(Item item) {
        Operation processingOperation = item.getCurrentOperation();
        if(processingOperation.equals(this.operation)) {
            setCurrentProcessingItem(item);
            setNotAvailable();
            setTimeToFinish();
            System.out.println("🔄 Machine " + id_machine + " Processing: 🟡 " + item.getItemID() + " [Operation: " + this.operation.getOperationName() + "]");
        }
    }



    public void updateMachine(){
        if(!available){
            this.timeLeftToFinish --;
            if(timeLeftToFinish <= 0){
                System.out.println("✅ Machine " + this.id_machine + " finished: " + this.currentProcessingItem.getItemID() + " [Operation: " + this.operation.getOperationName() + "]");
                this.timeLeftToFinish = DEFAULT_TIME_LEFT;
                this.available = DEFAULT_STATUS;
                this.currentProcessingItem.getNextOperation();
                this.currentProcessingItem = DEFAULT_ITEM;

            }
        }

    }

    public void printStatus() {
        if(available){
            System.out.println("🟢 Machine " + this.id_machine + " is AVAILABLE");
        }else{
            System.out.println("🟡 Machine " + this.id_machine + " is processing: 🟠 " + currentProcessingItem.getItemID() + " [Operation: " + this.operation.getOperationName() + "]");
        }
    }

    // Set
    public void setCurrentProcessingItem(Item item) {
        this.currentProcessingItem = item;
    }
    public void setNotAvailable() {this.available = false;}
    private void setTimeToFinish() {
        this.timeLeftToFinish = processingSpeed + 1;
    }

    //-- Gets
    public Operation getOperation() {
        return operation;
    }
    public ID getId_machine() {
        return id_machine;
    }
    public float getProcessingSpeed() {
        return processingSpeed;
    }
    public boolean isAvailable() {
        return available;
    }
    public float getTimeLeftToFinish() {
        return timeLeftToFinish;
    }

    //-- Methods of class Machine
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Machine machine = (Machine) o;
        return Objects.equals(id_machine, machine.id_machine) ;
    }

    @Override
    public int hashCode() {
        return id_machine.hashCode();
    }

    public Machine clone(){
        return new Machine(id_machine, operation, processingSpeed);
    }


    @Override
    public int compareTo(Machine o) {
        int speedComparison = Float.compare(this.processingSpeed, o.getProcessingSpeed());
        if (speedComparison != 0) {
            return speedComparison;
        }
        return this.id_machine.compareTo(o.getId_machine());
    }


}
