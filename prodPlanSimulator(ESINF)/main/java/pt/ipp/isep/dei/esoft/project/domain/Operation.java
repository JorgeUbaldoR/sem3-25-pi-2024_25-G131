package pt.ipp.isep.dei.esoft.project.domain;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

import java.util.Objects;

public class Operation {

    private static final String NO_DESCRIPTION = ANSI_BRIGHT_RED + "No description provided!" + ANSI_RESET;

    private ID operationId;
    private String operationName;
    private String operationDescription;


    public Operation(String operationName,ID operationId,String operationDescription) {
        this.operationName = operationName;
        this.operationId = operationId;
        this.operationDescription = operationDescription;
    }


    public Operation(String operationName, ID operationId) {
        this.operationName = operationName;
        this.operationId = operationId;
    }


    public Operation(String operationName) {
        this.operationName = operationName;
        this.operationId = null;
        this.operationDescription = NO_DESCRIPTION;
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
     * Getter method for operationID
     *
     * @return the ID of the operation
     */
    public ID getOperationId() {
        return operationId;
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
     * @return the hash code of the operation name
     */
    @Override
    public int hashCode() {
        return operationName.hashCode();
    }


    /**
     * @return the operation name as a string
     */
    @Override
    public String toString() {
        return String.format("%s%n", operationName);
    }

    /**
     * Clones the current Operation object
     *
     * @return a new instance of Operation with the same parameters
     */
    public Operation clone() {
        return new Operation(operationName,operationId,operationDescription);
    }
}
