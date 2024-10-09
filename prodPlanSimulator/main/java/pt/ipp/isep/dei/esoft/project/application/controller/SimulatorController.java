package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Simulator;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.MachineRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class SimulatorController {

    private ItemRepository itemRepository;
    private MachineRepository machineRepository;

    private List<Item> itemList;
    private List<Machine> machineList;

    public SimulatorController() {
        getItemRepository();
        getMachineRepository();
        itemList = itemRepository.getItemList();
        machineList = machineRepository.getMachineList();
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

    public void startSimulation(ID id) {
        Simulator simulator = new Simulator(itemList,machineList,id);
        simulator.startSimulation();
    }



}
