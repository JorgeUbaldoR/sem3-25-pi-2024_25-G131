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

    public OperationController() {
        this.machineRepository = getMachineRepository();
        this.operationRepository = getOperationRepository();
        fillOperationsFromMachines();

    }

    private OperationRepository getOperationRepository() {
        if (operationRepository == null) {
            Repositories repository = Repositories.getInstance();
            operationRepository = repository.getOperationRepository();
        }
        return operationRepository;
    }

    private MachineRepository getMachineRepository() {
        Repositories repository = Repositories.getInstance();
        return repository.getMachineRepository();
    }

    public Optional<Operation> addOperation(Operation operation) {
        return getOperationRepository().addOperation(operation);
    }

    public List<Machine> getAllMachines() {
        return machineRepository.getMachineList();
    }

    public void fillOperationsFromMachines() {
        List<Machine> machines = getAllMachines();
        operationRepository.fillOperations(machines);
    }


}

