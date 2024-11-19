package pt.ipp.isep.dei.esoft.project.domain;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

import java.util.Objects;

/**
 * Represents an operation with a unique identifier, name, and description.
 * Provides methods for accessing and modifying its attributes, as well as equality checks.
 */
public class Operation {

    /** Default message for operations without a description. */
    private static final String NO_DESCRIPTION = ANSI_BRIGHT_RED + "No description provided!" + ANSI_RESET;

    /** The unique identifier for the operation. */
    private final ID operationId;

    /** The name of the operation. */
    private String operationName;

    /** The description of the operation. */
    private String operationDescription;

    /**
     * Constructs an Operation with a name, unique ID, and description.
     *
     * @param operationName        the name of the operation.
     * @param operationId          the unique identifier for the operation.
     * @param operationDescription the description of the operation.
     */
    public Operation(String operationName, ID operationId, String operationDescription) {
        this.operationName = operationName;
        this.operationId = operationId;
        this.operationDescription = operationDescription;
    }

    /**
     * Constructs an Operation with a name and unique ID, and assigns a default description.
     *
     * @param operationName the name of the operation.
     * @param operationId   the unique identifier for the operation.
     */
    public Operation(String operationName, ID operationId) {
        this.operationName = operationName;
        this.operationId = operationId;
        this.operationDescription = NO_DESCRIPTION;
    }

    /**
     * Constructs an Operation with a name and assigns default values for the ID and description.
     *
     * @param operationName the name of the operation.
     */
    public Operation(String operationName) {
        this.operationName = operationName;
        this.operationId = null;
        this.operationDescription = NO_DESCRIPTION;
    }
    public Operation(ID operationId) {
        this.operationName = "Not provided yet";
        this.operationId = operationId;
        this.operationDescription = NO_DESCRIPTION;
    }

    /**
     * Gets the name of the operation.
     *
     * @return the operation's name.
     */
    public String getOperationName() {
        return operationName.trim();
    }

    /**
     * Sets a new name for the operation.
     *
     * @param operationName the new name for the operation.
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * Gets the unique identifier of the operation.
     *
     * @return the operation's unique ID.
     */
    public ID getOperationId() {
        return operationId;
    }

    /**
     * Gets the description of the operation.
     *
     * @return the operation's description.
     */
    public String getOperationDescription() {
        return operationDescription;
    }

    /**
     * Sets a new description for the operation.
     * Updates the description only if the new value is different from the current one.
     *
     * @param operationDescription the new description for the operation.
     * @return true if the description was updated, false otherwise.
     */
    public boolean setOperationDescription(String operationDescription) {
        if (this.operationDescription == null || !this.operationDescription.equals(operationDescription)) {
            this.operationDescription = operationDescription;
            return true;
        }
        return false;
    }

    /**
     * Checks equality between this operation and another object.
     * Operations are considered equal if their names are the same.
     *
     * @param o the object to compare.
     * @return true if the operations are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(operationName, operation.operationName);
    }

    /**
     * Computes the hash code for this operation.
     * The hash code is based on the operation's name.
     *
     * @return the hash code of the operation.
     */
    @Override
    public int hashCode() {
        return operationName.hashCode();
    }

    /**
     * Returns a string representation of the operation.
     * The string consists of the operation's name.
     *
     * @return the string representation of the operation.
     */
    @Override
    public String toString() {
        return String.format("%s%n", operationName);
    }

    /**
     * Creates a copy of this Operation object.
     *
     * @return a new Operation instance with the same attributes as this one.
     */
    public Operation clone() {
        return new Operation(operationName, operationId, operationDescription);
    }
}
