package pt.ipp.isep.dei.esoft.project.ui.ignorar;

import pt.ipp.isep.dei.esoft.project.application.controller.OperationController;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.MachineRepository;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;


import java.util.List;
import java.util.Set;


public class Main2 {

    public static void main(String[] args) {
        ItemRepository itemRepository = new ItemRepository();
        MachineRepository machineRepository = new MachineRepository();
        OperationRepository operationRepository = new OperationRepository();
        OperationController operationController = new OperationController();
        List<Item> items = itemRepository.getItemList();
        List<Machine> machineList = machineRepository.getMachineList();
        Set<Operation> operations = operationRepository.getOperations();


        if (items.isEmpty()) {
            System.out.println("A lista de itens está vazia.");
        } else {
            System.out.println("Itens carregados:");
            for (Item item : items) {
                System.out.print(item);
            }
        }

        if (machineList.isEmpty()) {
            System.out.println("A lista de maquinas está vazia");
        } else {
            System.out.println("Maquinas carregadas:");
            for (Machine machine : machineList) {
                System.out.print(machine);
            }
        }

        if (operations.isEmpty()) {
            System.out.println("A lista de operações está vazia");
        } else {
            System.out.println("Operações carregadas:");
            for (Operation operation : operations) {
                System.out.print(operation);
            }
        }

    }
}
