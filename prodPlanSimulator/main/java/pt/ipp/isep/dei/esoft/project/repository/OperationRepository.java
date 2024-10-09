package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.OperationController;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.util.*;

public class OperationRepository {

    private final Set<Operation> operations;


    public OperationRepository() {
        this.operations = new HashSet<>();
        fillOperations();
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

    private List<Machine> getMachines() {
        OperationController operationController = new OperationController();
        return operationController.getAllMachines();

    }

    private void fillOperations() {
        List<Machine> machines = getMachines();
        for (Machine machine : machines) {
            operations.add(machine.getOperation());
        }
    }

    public Set<Operation> getOperations() {
        return operations;
    }
}
