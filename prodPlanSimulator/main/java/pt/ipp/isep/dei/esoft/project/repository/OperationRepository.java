package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.OperationController;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.util.*;

public class OperationRepository {

    private final Set<Operation> operations;


    public OperationRepository(List<Machine> machines) {
        this.operations = new HashSet<>();
        fillOperations(machines);
    }

    public Optional<Operation> addOperation(Operation operation) {
        Optional<Operation> op = Optional.empty();
        if (!operations.contains(operation)) {
            op = Optional.of(operation.clone());
            operations.add(operation);
        } else {
            return op;
        }
        return op;
    }

    public void fillOperations(List<Machine> machines) {
        for (Machine machine : machines) {
            operations.add(machine.getOperation());
        }
    }

    public Set<Operation> getOperations() {
        return operations;
    }
}
