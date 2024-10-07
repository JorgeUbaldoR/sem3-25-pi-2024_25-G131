package pt.ipp.isep.dei.esoft.project;

import java.util.Objects;

public class Machine {

    private ID id_machine;
    private Operation operation;
    private float time;


    public Machine(ID id_machine, Operation operation, float time) {
        this.id_machine = id_machine;
        this.operation = operation;
        this.time = time;
    }

    public Machine(ID id_machine) {
        this.id_machine = id_machine;
    }



    public void setId_machine(ID id_machine) {
        this.id_machine = id_machine;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }


    public Operation getOperation() {
        return operation;
    }

    public ID getId_machine() {
        return id_machine;
    }

    public float getTime() {
        return time;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Machine machine = (Machine) o;
        return Float.compare(time, machine.time) == 0 && Objects.equals(id_machine, machine.id_machine) && Objects.equals(operation, machine.operation);
    }

    @Override
    public int hashCode() {
        return id_machine.hashCode();
    }

    public Machine clone(){
        return new Machine(id_machine, operation, time);
    }

}
