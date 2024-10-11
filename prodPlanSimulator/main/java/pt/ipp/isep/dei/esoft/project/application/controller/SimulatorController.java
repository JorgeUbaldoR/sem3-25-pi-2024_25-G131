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


/**
 * Controller class that manages the simulation of operations involving machines and items.
 * This class initializes the simulator and provides functionality to start the simulation.
 */
public class SimulatorController {

    private ItemRepository itemRepository;
    private MachineRepository machineRepository;
    private OperationRepository operationRepository;
    private Simulator simulator;

    /**
     * Constructs a SimulatorController instance.
     * Initializes the item repository, machine repository, operation repository, and the simulator.
     */
    public SimulatorController() {
        getItemRepository();
        getMachineRepository();
        getOperationRepository();
        this.simulator = new Simulator(getMachinesMap(), getItemRepository().getItemList(),getOperationRepository().getOperations());
    }

    /**
     * Retrieves the ItemRepository instance.
     *
     * @return the ItemRepository instance
     */
    private ItemRepository getItemRepository() {
        if(itemRepository == null) {
            Repositories repositories = Repositories.getInstance();
            itemRepository = repositories.getItemRepository();
        }
        return itemRepository;

    }

    /**
     * Retrieves the MachineRepository instance.
     *
     * @return the MachineRepository instance
     */
    private MachineRepository getMachineRepository() {
        if(machineRepository == null) {
            Repositories repositories = Repositories.getInstance();
            machineRepository = repositories.getMachineRepository();
        }
        return machineRepository;
    }

    /**
     * Retrieves the OperationRepository instance.
     *
     * @return the OperationRepository instance
     */
    private OperationRepository getOperationRepository() {
        if(operationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            operationRepository = repositories.getOperationRepository();
        }
        return operationRepository;
    }


    /**
     * Starts the simulation process.
     */
    public void startSimulation(){
        this.simulator.startSimulation();
    }

    /**
     * Constructs a mapping of operations to their associated machines.
     *
     * @return a Map where each key is an Operation and its value is a Queue of Machines that can perform it
     */
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
