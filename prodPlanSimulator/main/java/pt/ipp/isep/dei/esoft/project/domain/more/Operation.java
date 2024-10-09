package pt.ipp.isep.dei.esoft.project.domain.more;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Operation {


    private String operationName;
    private String operationDescription;
    private LocalTime duration;

    public Operation(String operationName, String operationDescription, LocalTime duration) {
        this.operationName = operationName;
        this.operationDescription = operationDescription;
        this.duration = duration;
    }


    public Operation(String operationName, LocalTime duration) {
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
        return Objects.equals(operationName, operation.operationName);
    }

    @Override
    public int hashCode() {
        return operationName.hashCode();
    }

    private String convertTimeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("%s took %s", operationName, convertTimeToString(duration));
    }
}
