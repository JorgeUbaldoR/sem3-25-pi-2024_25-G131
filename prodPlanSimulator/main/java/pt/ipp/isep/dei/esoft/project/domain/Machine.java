package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class Machine implements Comparable<Machine> {
    private final Boolean DEFAULT_STATUS = true;
    private final float DEFAULT_TIME_LEFT = 0;

    private final ID id_machine;
    private final Operation operation;
    private final float processingSpeed;
    private boolean available;
    private float timeLeftToFinish;
    private Item currentProcessingItem;

    /**
     * Constructs a new Machine with the specified ID, operation, and processing speed.
     *
     * @param id_machine the unique identifier for the machine.
     * @param operation the operation associated with the machine.
     * @param processingSpeed the processing speed of the machine.
     */
    public Machine(ID id_machine, Operation operation, float processingSpeed) {
        this.id_machine = id_machine;
        this.operation = operation;
        this.processingSpeed = processingSpeed;
        this.available = true;
        this.timeLeftToFinish = DEFAULT_TIME_LEFT;
        this.currentProcessingItem = null;
    }

    /**
     * Processes the specified item if the operation of the item matches the machine's operation.
     *
     * @param item the item to be processed by this machine.
     */
    public void processItem(Item item) {
        Operation processingOperation = item.getCurrentOperation();
        if (processingOperation != null && processingOperation.equals(this.operation)) {
            setCurrentProcessingItem(item);
            setNotAvailable();
            setTimeToFinish();
            System.out.printf("   🔄 Machine %s%s%s %sprocessing%s:  📦 %s%s%s [Operation: %s%s%s]%n",
                                ANSI_BRIGHT_WHITE,id_machine,ANSI_RESET,ANSI_BRIGHT_YELLOW,ANSI_RESET,
                                ANSI_BRIGHT_WHITE,item.getItemID(),ANSI_RESET,ANSI_BRIGHT_WHITE,this.operation.getOperationName(),ANSI_RESET);
        } else {
            System.out.printf("   ⚠️ Machine %s%s%s  can't process:  📦 Item %s%s%s [Operation mismatch]",ANSI_BRIGHT_WHITE,id_machine,ANSI_RESET,ANSI_BRIGHT_WHITE,item.getItemID(),ANSI_RESET);
        }
    }

    /**
     * Updates the machine's status, decrementing the time left to finish processing.
     * If the processing is complete, it resets the machine and returns true.
     *
     * @return true if the machine has finished processing; false otherwise.
     */
    public boolean updateMachine() {
        if (!available) {
            this.timeLeftToFinish--;
            if (timeLeftToFinish <= 0) {
                System.out.printf("    ✅ Machine %s%s%s %sfinished%s:  📦 %s%s%s [Operation: %s%s%s]%n",ANSI_BRIGHT_WHITE,this.id_machine,ANSI_RESET,ANSI_RED,ANSI_RESET,ANSI_BRIGHT_WHITE,this.currentProcessingItem.getItemID(),ANSI_RESET,ANSI_BRIGHT_WHITE,this.operation.getOperationName(),ANSI_RESET);
                resetMachine();
                return true;
            }
        }
        return false;
    }

    /**
     * Resets the machine's status to available and time left to finish to default.
     */
    private void resetMachine() {
        this.timeLeftToFinish = DEFAULT_TIME_LEFT;
        this.available = DEFAULT_STATUS;;
    }

    /**
     * Prints the current status of the machine.
     * Displays whether the machine is available or currently processing an item.
     */
    public boolean printStatus() {
        if (available) {
            System.out.printf("   ⚙️ Machine %s%s%s is %sAVAILABLE%4s [Operation: %s%-10s%s [Processing Time: %s%s%s]%n",ANSI_BRIGHT_WHITE,this.id_machine,ANSI_RESET
                                            ,ANSI_BRIGHT_GREEN,ANSI_RESET,ANSI_BRIGHT_WHITE,this.operation.getOperationName()+"]",ANSI_RESET,"",ANSI_BRIGHT_WHITE,this.processingSpeed,ANSI_RESET);
            return true;
        } else {
            System.out.printf("   🛠️ Machine %s%s%s is %sPROCESSING%s:  📦 %s%s%s [Operation: %s%s%s] [Time left: %s%s%s]%n",ANSI_BRIGHT_WHITE,this.id_machine,ANSI_RESET,
                    ANSI_BRIGHT_YELLOW,ANSI_RESET,ANSI_BRIGHT_WHITE,currentProcessingItem.getItemID(),
                    ANSI_RESET,ANSI_BRIGHT_WHITE,this.operation.getOperationName(),ANSI_RESET,
                    ANSI_BRIGHT_WHITE,this.timeLeftToFinish,ANSI_RESET);
        }
        return false;
    }


    //----------------Setters
    /**
     * Sets the current processing item for this machine.
     *
     * @param item the item currently being processed.
     */
    public void setCurrentProcessingItem(Item item) {
        this.currentProcessingItem = item;
    }

    /**
     * Marks the machine as not available.
     */
    public void setNotAvailable() {
        this.available = false;
    }

    /**
     * Sets the time left to finish processing based on the machine's processing speed.
     */
    private void setTimeToFinish() {
        this.timeLeftToFinish = processingSpeed;
    }

    //----------------Getters
    /**
     * Returns the operation associated with this machine.
     *
     * @return the operation of this machine.
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Returns the unique ID of this machine.
     *
     * @return the machine's ID.
     */
    public ID getId_machine() {
        return id_machine;
    }

    /**
     * Returns the processing speed of this machine.
     *
     * @return the processing speed.
     */
    public float getProcessingSpeed() {
        return processingSpeed;
    }

    /**
     * Checks if the machine is available.
     *
     * @return true if the machine is available; false otherwise.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Returns the time left for this machine to finish processing.
     *
     * @return the time left to finish processing.
     */
    public float getTimeLeftToFinish() {
        return timeLeftToFinish;
    }


    /**
     * Returns the item currently being processed by this machine.
     *
     * @return the current processing item.
     */
    public Item getCurrentProcessingItem() {
        return currentProcessingItem;
    }


    //----------------Overrides and clone methods
    /**
     * Compares this machine to the specified object for equality.
     * Two machines are considered equal if they have the same machine ID.
     *
     * @param o the object to compare this machine against.
     * @return true if the specified object is equal to this machine; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Machine machine = (Machine) o;
        return Objects.equals(id_machine, machine.id_machine);
    }

    /**
     * Returns a hash code value for this machine.
     * The hash code is based on the machine's unique ID.
     *
     * @return a hash code value for this machine.
     */
    @Override
    public int hashCode() {
        return id_machine.hashCode();
    }

    /**
     * Compares this machine with another machine for order.
     * Machines are compared based on their processing speed and then by their machine ID.
     *
     * @param o the machine to be compared.
     * @return a negative integer, zero, or a positive integer as this machine
     *         is less than, equal to, or greater than the specified machine.
     */
    @Override
    public int compareTo(Machine o) {
        int speedComparison = Float.compare(this.processingSpeed, o.getProcessingSpeed());
        if (speedComparison != 0) {
            return speedComparison;
        }
        return this.id_machine.compareTo(o.getId_machine());
    }

    /**
     * Creates and returns a clone of this machine.
     * The cloned machine has the same ID, operation, and processing speed.
     *
     * @return a clone of this machine.
     */
    public Machine clone() {
        return new Machine(id_machine, operation, processingSpeed);
    }

    /**
     * Returns a string representation of the machine.
     * <p>
     * This method formats the output to include the ID of the machine.
     * The output will display the machine ID on a new line.
     * </p>
     *
     * @return a formatted string representing the machine's ID.
     */
    @Override
    public String toString() {
        return String.format("%s%n", id_machine.toString());
    }
}
