package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.MachineRepository;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

public class OperationController {

    private OperationRepository operationRepository;
    private final MachineRepository machineRepository;
    private ItemRepository itemRepository;

    /**
     * Constructor for OperationController.
     * Initializes machineRepository and operationRepository.
     * Also fills operations from machines upon creation.
     */
    public OperationController() {
        this.machineRepository = getMachineRepository();
        this.operationRepository = getOperationRepository();
        this.itemRepository = getItemRepository();
        fillOperationsFromItems();

    }

    /**
     * Retrieves the OperationRepository instance.
     *
     * @return the operationRepository
     */
    private OperationRepository getOperationRepository() {
        if (operationRepository == null) {
            Repositories repository = Repositories.getInstance();
            operationRepository = repository.getOperationRepository();
        }
        return operationRepository;
    }

    /**
     * Retrieves the MachineRepository instance.
     *
     * @return the machineRepository
     */
    private MachineRepository getMachineRepository() {
        Repositories repository = Repositories.getInstance();
        return repository.getMachineRepository();
    }
    private ItemRepository getItemRepository() {
        if (itemRepository == null) {
            Repositories repository = Repositories.getInstance();
            itemRepository = repository.getItemRepository();
        }
        return itemRepository;
    }

    /**
     * Adds a new operation to the operation repository.
     *
     * @param operation the operation to be added
     * @return an Optional containing the added operation if successful
     */
    public Optional<Operation> addOperation(Operation operation) {
        return getOperationRepository().addOperation(operation);
    }


    /**
     * Retrieves all items from the item repository.
     *
     * @return a list containing all items managed by the item repository.
     */
    public List<Item> getAllItems() {
        return itemRepository.getItemList();
    }


    /**
     * Populates the operation repository with operations extracted from the items.

     * This method first retrieves all items by calling getAllItems(), then uses those items
     * to fill the operation repository. Each item may be associated with one or more operations,
     * and this method ensures that the operation repository is populated based on the available items.
     */
    public void fillOperationsFromItems() {
        List<Item> items = getAllItems();
        operationRepository.fillOperations(items);
    }


    /**
     * Retrieves all operations from the operation repository.
     *
     * @return An Optional containing a list of all operations if available; otherwise, an empty Optional.
     */
    public Optional<List<Operation>> getAllOperations() {
        return operationRepository.getAllOperations();
    }

    /**
     * Registers a new operation with the given name and description in the operation repository.
     *
     * @param name        The name of the operation to be registered.
     * @param description The description of the operation to be registered.
     * @return An Optional containing the registered operation if successful; otherwise, an empty Optional.
     */
    public Optional<Operation> registerOperation(String name, String description) {
        return operationRepository.registerOperation(name, description);
    }

    /**
     * Registers a new operation with the given name in the operation repository.
     *
     * @param name The name of the operation to be registered.
     * @return An Optional containing the registered operation if successful; otherwise, an empty Optional.
     */
    public Optional<Operation> registerOperation(String name) {
        return operationRepository.registerOperation(name);
    }
}


