package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.repository.MachineRepository;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

public class OperationController {

    private OperationRepository operationRepository;
    private final MachineRepository machineRepository;

    /**
     * Constructor for OperationController.
     * Initializes machineRepository and operationRepository.
     * Also fills operations from machines upon creation.
     */
    public OperationController() {
        this.machineRepository = getMachineRepository();
        this.operationRepository = getOperationRepository();
        fillOperationsFromMachines();

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
     * Retrieves a list of all machines.
     *
     * @return a list of machines
     */
    public List<Machine> getAllMachines() {
        return machineRepository.getMachineList();
    }

    /**
     * Fills operations in the operation repository based on the available machines.
     */
    public void fillOperationsFromMachines() {
        List<Machine> machines = getAllMachines();
        operationRepository.fillOperations(machines);
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


