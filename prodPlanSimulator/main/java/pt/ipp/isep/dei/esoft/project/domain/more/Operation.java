package pt.ipp.isep.dei.esoft.project.domain.more;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Operation {

    private ID operationId;
    private String operationName;
    private String operationDescription;
    private LocalTime duration;

    public Operation(String operationName, String operationDescription, ID operationId, LocalTime duration) {
        this.operationName = operationName;
        this.operationDescription = operationDescription;
        this.operationId = operationId;
        this.duration = duration;
    }


    public Operation(String operationName, ID operationId, LocalTime duration) {
        this.operationName = operationName;
        this.operationId = operationId;
        this.duration = duration;
    }

    public Operation(ID operationId) {
        this.operationId = operationId;
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

    public ID getOperationId() {
        return operationId;
    }

    public void setOperationId(ID operationId) {
        this.operationId = operationId;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(operationId, operation.operationId);
    }

    @Override
    public int hashCode() {
        return operationId.hashCode();
    }

    private String convertTimeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("(%s) - %s took %s", operationId, operationName, convertTimeToString(duration));
    }
}
