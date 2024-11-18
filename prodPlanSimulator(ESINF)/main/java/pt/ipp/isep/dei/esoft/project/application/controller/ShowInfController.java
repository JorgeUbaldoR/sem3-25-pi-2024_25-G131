package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.MachineRepository;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.*;

public class ShowInfController {

    private ItemRepository itemRepository;
    private MachineRepository machineRepository;
    private OperationRepository operationRepository;


    public ShowInfController() {
        getItemRepository();
        getMachineRepository();
        getOperationRepository();
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
     * Method that returns the list of operations.
     * It accesses the operation repository and retrieves all stored operations.
     *
     * @return List of operations present in the repository.
     */
    public List<Operation> getOperationList(){
        return getOperationRepository().getOperations();
    }


    /**
     * Method that returns the list of items.
     * It accesses the item repository and retrieves all stored items.
     *
     * @return List of items present in the repository.
     */
    public List<Item> getItemList(){
        return getItemRepository().getItemList();
    }


    /**
     * Constructs a mapping of operations to their associated machines.
     *
     * @return a Map where each key is an Operation and its value is a Queue of Machines that can perform it
     */
    public Map<Operation, Queue<Machine>> getMachinesMap() {
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
