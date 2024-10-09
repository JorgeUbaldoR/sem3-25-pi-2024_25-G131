package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;
import pt.ipp.isep.dei.esoft.project.repository.MachineRepository;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

public class OperationController {

    private OperationRepository operationRepository;
    private MachineRepository machineRepository;

    public OperationController() {
        getOperationRepository();
    }

    private OperationRepository getOperationRepository() {
        if (operationRepository == null) {
            Repositories repository = Repositories.getInstance();
            operationRepository = repository.getOperationRepository();
        }
        return operationRepository;
    }

    private MachineRepository getMachineRepository() {
        if (machineRepository == null) {
            Repositories repository = Repositories.getInstance();
            machineRepository = repository.getMachineRepository();
        }
        return machineRepository;
    }

    public Optional<Operation> addOperation(Operation operation) {
        return getOperationRepository().addOperation(operation);
    }

    public List<Machine> getAllMachines() {
        MachineRepository machineRepository = getMachineRepository();
        return machineRepository.getMachineList();
    }
}

