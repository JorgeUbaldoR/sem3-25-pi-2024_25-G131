package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Operation {


    private String operationName;
    private String operationDescription;
    private float duration;

    /**
     *
     * @param operationName the name of the operation
     * @param operationDescription description of the operation
     * @param duration duration of operation
     *
     * Operation class constructor that receives all its parameters.
     */
    public Operation(String operationName, String operationDescription, float duration) {
        this.operationName = operationName;
        this.operationDescription = operationDescription;
        this.duration = duration;
    }

    /**
     *
     * @param operationName the name of the operation
     * @param duration duration of operation
     *
     *  Operation class constructor that receives the name and duration of the operation.
     */
    public Operation(String operationName, float duration) {
        this.operationName = operationName;
        this.duration = duration;
    }

    /**
     *
     * @param operationName the name of the operation
     *
     * Constructor of Operation class that receives only the name
     */
    public Operation(String operationName) {
        this.operationName = operationName;
    }


    /**
     * Getter method for operationName
     *
     * @return the name of the operation
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * Setter method for operationName
     *
     * @param operationName the new name of the operation
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * Getter method for operationDescription
     *
     * @return the description of the operation
     */
    public String getOperationDescription() {
        return operationDescription;
    }

    /**
     * Setter method for operationDescription
     *
     * @param operationDescription the new description of the operation
     */
    public boolean setOperationDescription(String operationDescription) {
        if (this.operationDescription == null || !this.operationDescription.equals(operationDescription)) {
            this.operationDescription = operationDescription;
            return true;
        }
        return false;
    }

    /**
     * Getter method for duration
     *
     * @return the duration of the operation
     */
    public float getDuration() {
        return duration;
    }

    /**
     * Setter method for duration
     *
     * @param duration the new duration of the operation
     */
    public void setDuration(float duration) {
        this.duration = duration;
    }

    /**
     *
     * @param o the object to compare
     * @return true if the operations are equal based on their names, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(operationName, operation.operationName);
    }

    /**
     *
     * @return the hash code of the operation name
     */
    @Override
    public int hashCode() {
        return operationName.hashCode();
    }


    /**
     *
     * @return the operation name as a string
     */
    @Override
    public String toString() {
        return String.format("%s%n", operationName);
    }

    /**
     * Clones the current Operation object
     * @return a new instance of Operation with the same parameters
     */
    public Operation clone() {
        return new Operation(operationName, operationDescription, duration);
    }
}
