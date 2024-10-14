package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.util.*;

/**
 * Repository class that manages operations associated with machines.
 * This class allows for the addition and retrieval of operations.
 */
public class OperationRepository {

    private final Set<Operation> operations;

    /**
     * Constructs an OperationRepository instance.
     * Initializes the set to hold operations.
     *
     * @param machines a list of machines to fill the repository with operations
     */
    public OperationRepository(List<Machine> machines) {
        this.operations = new HashSet<>();
        fillOperations(machines);
    }

    /**
     * Adds an operation to the repository if it is not already present.
     *
     * @param operation the operation to be added
     * @return an Optional containing the cloned operation if added successfully, or empty if it was already present
     */
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

    /**
     * Fills the repository with operations from the provided list of machines.
     *
     * @param machines a list of machines whose operations will be added to the repository
     */
    public void fillOperations(List<Machine> machines) {
        for (Machine machine : machines) {
            operations.add(machine.getOperation());
        }
    }

    /**
     * Retrieves a list of all operations in the repository.
     *
     * @return a list containing all operations
     */
    public List<Operation> getOperations() {
        List<Operation> operation = new ArrayList<>();
        operation.addAll(operations);
        return operation;
    }

    public Optional<List<Operation>> getAllOperations() {
        Optional<List<Operation>> op = Optional.empty();
        List<Operation> operations = getOperations();
        if (!operations.isEmpty()) {
            op = Optional.of(operations);
        }
        return op;
    }

    public Optional<Operation> registerOperation(String name, String description) {
        Optional<Operation> optionalValue = Optional.empty();

        Operation operation = new Operation(name, description);

        if (operations.add(operation)) {
            optionalValue = Optional.of(operation);
        }
        return optionalValue;
    }


    public Optional<Operation> registerOperation(String name) {
        Optional<Operation> optionalValue = Optional.empty();

        Operation operation = new Operation(name);

        if (operations.add(operation)) {
            optionalValue = Optional.of(operation);
        }
        return optionalValue;
    }

}
