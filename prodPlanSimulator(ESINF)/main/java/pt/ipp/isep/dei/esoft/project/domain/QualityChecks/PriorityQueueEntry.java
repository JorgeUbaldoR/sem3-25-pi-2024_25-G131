package pt.ipp.isep.dei.esoft.project.domain.QualityChecks;

import pt.ipp.isep.dei.esoft.project.domain.ID;

public class PriorityQueueEntry {

    private final int priority;
    private final ID operationID;


    public PriorityQueueEntry(int priority, ID operation) {
        this.priority = priority;
        this.operationID = operation;

    }

    public int getPriority() {
        return priority;
    }

    public ID getOperationID() {
        return operationID;
    }





}
