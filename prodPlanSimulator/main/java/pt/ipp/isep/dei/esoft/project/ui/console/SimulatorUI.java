package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SimulatorController;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_BRIGHT_WHITE;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_RESET;

public class SimulatorUI implements Runnable {


    private SimulatorController controller;

    public SimulatorUI() {
        controller = new SimulatorController();
    }

    private SimulatorController getSimulationController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "               Simulation                 " + ANSI_RESET + "\n");
        showMachines();
        showItems();

        getSimulationController().startSimulation();
    }

    private void showItems() {
        System.out.printf("%n%s• ITEMS:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("-----------------------------------------------%n");
        List<Item> listItem = getSimulationController().getItemList();
        System.out.printf("%s%-20s %-17s%s%n", ANSI_BRIGHT_WHITE, "ID", "Operation", ANSI_RESET);
        System.out.printf("-----------------------------------------------%n");
        for (Item item : listItem) {
            System.out.printf("%-13s [", item.getItemID());
            for (Operation operation : item.getOperationList()) {
                if (item.getOperationList().indexOf(operation) != item.getOperationList().size() - 1) {
                    System.out.printf("%s, ", operation.getOperationName());
                }else{
                    System.out.printf("%s]%n%n", operation.getOperationName());
                }
            }
        }

    }

    private void showMachines() {
        System.out.printf("%s• MACHINES:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("-----------------------------------------------%n");
        Map<Operation, Queue<Machine>> machinesMap = getSimulationController().getMachinesMap();
        System.out.printf("%s%-13s %-17s %-15s%s%n", ANSI_BRIGHT_WHITE, "ID", "Operation", "Processing Time", ANSI_RESET);
        System.out.printf("-----------------------------------------------%n");
        for (Map.Entry<Operation, Queue<Machine>> entry : machinesMap.entrySet()) {
            for (Machine machine : entry.getValue()) {
                System.out.printf("%-13s %-17s %10.2f%n", machine.getId_machine(), machine.getOperation().getOperationName(), machine.getProcessingSpeed());
            }
        }

    }


}


