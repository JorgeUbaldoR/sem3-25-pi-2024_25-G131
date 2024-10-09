package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.util.Objects;

public class Machine {
    private final Boolean DEFAULT_STATUS = true;
    private final int DEFAULT_TIMELEFT = 0;

    private ID id_machine;
    private Operation operation;
    private float processingSpeed;
    private boolean available;
    private int timeLeftToFinish;
    private Item currentProcessingItem;



    public Machine(ID id_machine, Operation operation, float processingSpeed) {
        this.id_machine = id_machine;
        this.operation = operation;
        this.processingSpeed = processingSpeed;
        this.available = DEFAULT_STATUS;
        this.timeLeftToFinish = DEFAULT_TIMELEFT;
        this.currentProcessingItem = null;
    }


    // Set
    public void setId_machine(ID id_machine) {
        this.id_machine = id_machine;
    }
    public void setProcessingSpeed(float processingSpeed) {
        this.processingSpeed = processingSpeed;
    }
    public void setOperation(Operation operation) {
        this.operation = operation;
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

    //-- Methods of class Machine
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Machine machine = (Machine) o;
        return Float.compare(processingSpeed, machine.processingSpeed) == 0 && Objects.equals(id_machine, machine.id_machine) && Objects.equals(operation, machine.operation);
    }

    @Override
    public int hashCode() {
        return id_machine.hashCode();
    }

    public Machine clone(){
        return new Machine(id_machine, operation, processingSpeed);
    }

}
