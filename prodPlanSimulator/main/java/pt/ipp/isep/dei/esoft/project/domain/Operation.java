package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Operation {


    private String operationName;
    private String operationDescription;
    private float duration;

    public Operation(String operationName, String operationDescription, float duration) {
        this.operationName = operationName;
        this.operationDescription = operationDescription;
        this.duration = duration;
    }


    public Operation(String operationName, float duration) {
        this.operationName = operationName;
        this.duration = duration;
    }

    public Operation(String operationName) {
        this.operationName = operationName;
    }


    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }


    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(operationName, operation.operationName);
    }

    @Override
    public int hashCode() {
        return operationName.hashCode();
    }



    @Override
    public String toString() {
        return String.format("%s%n", operationName);
    }

    public Operation clone() {
        return new Operation(operationName, operationDescription, duration);
    }
}
