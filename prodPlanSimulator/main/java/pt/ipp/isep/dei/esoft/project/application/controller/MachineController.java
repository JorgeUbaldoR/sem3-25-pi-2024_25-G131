package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Machine;
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

    public List<Machine> getMachinesList(){
        return machineRepository.getMachineList();
    }

    public Optional<List<Machine>> requestList() {
        Optional<List<Machine>> request;

        request = machineRepository.requestMachineList();
        return request;
    }
}


