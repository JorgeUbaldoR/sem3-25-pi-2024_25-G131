package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.OperationController;
import pt.ipp.isep.dei.esoft.project.domain.Item;
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
     * @param items a list of items to fill the repository with operations
     */
    public OperationRepository(List<Item> items) {
        this.operations = new HashSet<>();
        fillOperations(items);
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
     * Populates the operations list with operations extracted from the provided items.

     * This method iterates over each item in the provided list and retrieves the list of operations
     * associated with each item. Each operation is cloned to ensure that the original operation objects
     * are not modified when added to the operations list. The cloned operations are then added to the
     * internal list of operations.
     *
     * @param items a list of Item objects from which operations are extracted and added to the operations list.
     */
    public void fillOperations(List<Item> items) {
        for (Item item : items) {
            for (Operation operation : item.getOperationList()) {
                operations.add(operation.clone());
            }
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

    /**
     * Retrieves all operations from the operations list.
     *
     * @return An Optional containing a list of operations if available; otherwise, an empty Optional.
     */
    public Optional<List<Operation>> getAllOperations() {
        Optional<List<Operation>> op = Optional.empty();
        List<Operation> operations = getOperations();
        if (!operations.isEmpty()) {
            op = Optional.of(operations);
        }
        return op;
    }


    /**
     * Registers a new operation with the given name and description.
     *
     * @param name        The name of the operation to be registered.
     * @param description The description of the operation.
     * @return An Optional containing the registered operation if successful; otherwise, an empty Optional.
     */
    public Optional<Operation> registerOperation(String name, String description) {
        Optional<Operation> optionalValue = Optional.empty();

        Operation operation = new Operation(name, description);

        if (operations.add(operation)) {
            optionalValue = Optional.of(operation);
        }
        return optionalValue;
    }


    /**
     * Registers a new operation with the given name.
     *
     * @param name The name of the operation to be registered.
     * @return An Optional containing the registered operation if successful; otherwise, an empty Optional.
     */
    public Optional<Operation> registerOperation(String name) {
        Optional<Operation> optionalValue = Optional.empty();

        Operation operation = new Operation(name);

        if (operations.add(operation)) {
            optionalValue = Optional.of(operation);
        }
        return optionalValue;
    }

}
