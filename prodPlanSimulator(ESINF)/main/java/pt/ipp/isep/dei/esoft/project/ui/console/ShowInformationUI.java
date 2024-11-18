package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SimulatorController;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

/**
 * ShowInformationUI class is responsible for displaying information about operations, items, and machines
 * in the simulation process. It fetches data from the `SimulatorController` and presents it in a console interface.
 */
public class ShowInformationUI implements Runnable {

    private SimulatorController controller;

    /**
     * Constructor initializes the SimulatorController instance.
     */
    public ShowInformationUI() {
        controller = new SimulatorController();
    }

    /**
     * Returns the instance of the SimulatorController.
     *
     * @return controller instance.
     */
    private SimulatorController getSimulationController() {
        return controller;
    }

    /**
     * Starts the UI and displays information about machines, items, and operations.
     * It sequentially calls methods to show each type of information on the console.
     */
    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "               Information                 " + ANSI_RESET + "\n");
        showMachines();
        System.out.println();
        showItems();
        System.out.println();
        showOperations();
    }

    /**
     * Displays information about operations, including their names and descriptions.
     */
    private void showOperations() {
        System.out.printf("%n%s• OPERATIONS:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("%s-----------------------------------------------%s%n",ANSI_BRIGHT_BLACK,ANSI_RESET);
        List<Operation> operationList = getSimulationController().getOperationList();
        System.out.printf("%s%-26s %s%s%n", ANSI_BRIGHT_WHITE, "Name", "Description", ANSI_RESET);
        System.out.printf("%s-----------------------------------------------%s%n",ANSI_BRIGHT_BLACK,ANSI_RESET);
        for (Operation operation : operationList) {
            System.out.printf("%-20s %s%n", operation.getOperationName(), operation.getOperationDescription());
        }
    }

    /**
     * Displays information about items, including their IDs, names, priorities, and associated operations.
     * If an item has no associated operations, it displays [__] under the operations column.
     */
    private void showItems() {
        boolean flag = true;
        System.out.printf("%n%s• ITEMS:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("%s--------------------------------------------------------------------------%s%n",ANSI_BRIGHT_BLACK,ANSI_RESET);
        List<Item> listItem = getSimulationController().getItemList();
        System.out.printf("%s%-14s %-17s %-17s %s%s%n", ANSI_BRIGHT_WHITE, "ID","Name","Priority" ,"Operation", ANSI_RESET);
        System.out.printf("%s--------------------------------------------------------------------------%s%n",ANSI_BRIGHT_BLACK,ANSI_RESET);

        for (Item item : listItem) {
            flag = true;
            if (!item.getOperationList().isEmpty()) {
                flag = false;

                System.out.printf("%-11s %-21s %-11s [", item.getItemID(),item.getName(),item.getPriority());
                for (Operation operation : item.getOperationList()) {
                    if (item.getOperationList().indexOf(operation) != item.getOperationList().size() - 1) {
                        System.out.printf("%s, ", operation.getOperationName());
                    } else {
                        System.out.printf("%s]%n", operation.getOperationName());
                    }
                }
            }

        }
        if (flag) {

            for (Item item : listItem) {
                if (item.getOperationList().isEmpty()) {
                    System.out.printf("%-10s %-23s %-17s [__]%n", item.getItemID(),item.getName(),item.getPriority());
                }

            }
        }
    }

    /**
     * Displays information about machines, including their IDs, associated operations, and processing times.
     */
    private void showMachines() {
        System.out.printf("%s• MACHINES:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("%s-----------------------------------------------%s%n",ANSI_BRIGHT_BLACK,ANSI_RESET);
        Map<Operation, Queue<Machine>> machinesMap = getSimulationController().getMachinesMap();
        System.out.printf("%s%-13s %-17s %-15s%s%n", ANSI_BRIGHT_WHITE, "ID", "Operation", "Processing Time", ANSI_RESET);
        System.out.printf("%s-----------------------------------------------%s%n",ANSI_BRIGHT_BLACK,ANSI_RESET);
        for (Map.Entry<Operation, Queue<Machine>> entry : machinesMap.entrySet()) {
            for (Machine machine : entry.getValue()) {
                System.out.printf("%-13s %-17s %10.2f%n", machine.getId_machine(), machine.getOperation().getOperationName(), machine.getProcessingSpeed());
            }
        }
    }
}
