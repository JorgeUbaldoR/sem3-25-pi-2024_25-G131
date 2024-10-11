package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.OperationQueue;
import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Simulator;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.MachineRepository;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.*;

public class SimulatorController {

    private ItemRepository itemRepository;
    private MachineRepository machineRepository;
    private OperationRepository operationRepository;
    private Simulator simulator;


    public SimulatorController() {
        getItemRepository();
        getMachineRepository();
        getOperationRepository();
        this.simulator = new Simulator(getMachinesMap(), getItemRepository().getItemList(),getOperationRepository().getOperations());
    }

    private ItemRepository getItemRepository() {
        if(itemRepository == null) {
            Repositories repositories = Repositories.getInstance();
            itemRepository = repositories.getItemRepository();
        }
        return itemRepository;

    }
    private MachineRepository getMachineRepository() {
        if(machineRepository == null) {
            Repositories repositories = Repositories.getInstance();
            machineRepository = repositories.getMachineRepository();
        }
        return machineRepository;
    }
    private OperationRepository getOperationRepository() {
        if(operationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            operationRepository = repositories.getOperationRepository();
        }
        return operationRepository;
    }


    public void startSimulation(){
        this.simulator.startSimulation();
    }

    private Map<Operation, Queue<Machine>> getMachinesMap() {
        Map<Operation, Queue<Machine>> machinesMap = new HashMap<>();
        for (Machine machine : getMachineRepository().getMachineList()) {
            if(!machinesMap.containsKey(machine.getOperation())) {
                machinesMap.put(machine.getOperation(), new PriorityQueue<>());
                machinesMap.get(machine.getOperation()).add(machine);
            }else {
                machinesMap.get(machine.getOperation()).add(machine);
            }
        }
        return machinesMap;
    }


}
