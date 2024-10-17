package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.repository.MachineRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

/**
 * Controller class that manages the interaction with the Machine repository.
 * This class provides functionality to add machines to the repository.
 */
public class MachineController {
    private MachineRepository machineRepository;

    /**
     * Constructs a MachineController instance.
     * Initializes the MachineRepository by retrieving it from the Repositories singleton.
     */
    public MachineController() {
        getMachineRepository();
    }


    /**
     * Retrieves the MachineRepository instance.
     *
     * @return the MachineRepository instance
     */
    private MachineRepository getMachineRepository() {
        if (machineRepository == null) {
            Repositories repositories = Repositories.getInstance();
            machineRepository = repositories.getMachineRepository();
        }
        return machineRepository;
    }

    /**
     * Adds a new Machine to the repository.
     *
     * @param machine the Machine to be added
     * @return an Optional containing the newly added Machine if successful, or empty if the Machine already exists
     */
    public Optional<Machine> addMachineController(Machine machine) {
        return getMachineRepository().addMachine(machine);
    }

    /**
     * Requests a list of machines from the machine repository.
     *
     * @return An Optional containing a list of machines if available; otherwise, an empty Optional.
     */
    public Optional<List<Machine>> requestList() {
        Optional<List<Machine>> request;

        request = machineRepository.requestMachineList();
        return request;
    }

    /**
     * Registers a new machine with the specified operation, ID, and execution time.

     * This method delegates the machine registration process to the machine repository.
     * It utilizes the provided parameters to create and store a new machine.
     * The method returns an Optional<Machine> which contains the registered machine if
     * the registration is successful. If the machine could not be registered (e.g.,
     * due to ID duplication), an empty Optional is returned.
     *
     * @param operation The operation associated with the machine to be registered.
     * @param id The unique identifier for the machine.
     * @param exTime The execution time for the machine's operation.
     * @return An Optional containing the registered machine if successful, or an empty Optional if not.
     */
    public Optional<Machine> registerMachine(Operation operation, int id, double exTime) {
        return machineRepository.registerMachine(operation, id, exTime);
    }
}


