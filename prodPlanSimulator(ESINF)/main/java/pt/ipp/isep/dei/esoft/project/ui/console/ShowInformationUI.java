package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ShowInfController;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

/**
 * The ShowInformationUI class provides a console-based user interface for displaying
 * detailed information about operations, items, and machines involved in the simulation process.
 * This class interacts with the ShowInfController to fetch relevant data and displays it
 * in a structured and formatted manner using ANSI escape codes for color output.
 *
 * Key Responsibilities:
 * - Fetch and display operations with their names and descriptions.
 * - Fetch and display items with details such as ID, name, quantity, priority, and associated operations.
 * - Fetch and display machines with details such as ID, associated operations, and processing times.
 */
public class ShowInformationUI implements Runnable {

    private ShowInfController controller;

    /**
     * Constructor initializes the ShowInfController instance to manage data retrieval.
     */
    public ShowInformationUI() {
        controller = new ShowInfController();
    }

    /**
     * Returns the instance of the ShowInfController.
     *
     * @return an instance of ShowInfController for data access.
     */
    private ShowInfController getShowInfController() {
        return controller;
    }

    /**
     * Entry point for the UI. Displays information sequentially about:
     * - Machines involved in the simulation.
     * - Items processed or produced during the simulation.
     * - Operations performed during the simulation.
     * Each category is formatted for better readability.
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
     * Displays detailed information about all operations.
     * For each operation, displays:
     * - Name
     * - Description
     * Uses formatted output for better readability.
     */
    private void showOperations() {
        System.out.printf("%n%s• OPERATIONS:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("%s-----------------------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        List<Operation> operationList = getShowInfController().getOperationList();
        System.out.printf("%s%-26s %s%s%n", ANSI_BRIGHT_WHITE, "Name", "Description", ANSI_RESET);
        System.out.printf("%s-----------------------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        for (Operation operation : operationList) {
            System.out.printf("%-20s %s%n", operation.getOperationName(), operation.getOperationDescription());
        }
    }

    /**
     * Displays detailed information about all items.
     * For each item, displays:
     * - ID
     * - Name
     * - Quantity
     * - Priority
     * - Associated operations (if any). If none, displays "[__]".
     * Items with multiple operations are displayed in a comma-separated list.
     */
    private void showItems() {
        boolean flag = true; // Tracks whether all items without operations have been displayed
        System.out.printf("%n%s• ITEMS:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("%s-------------------------------------------------------------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        List<Item> listItem = getShowInfController().getItemList();
        System.out.printf("%s%-14s %-17s %-17s %-17s %s%s%n", ANSI_BRIGHT_WHITE, "ID", "Name", "Quantity", "Priority", "Operation", ANSI_RESET);
        System.out.printf("%s-------------------------------------------------------------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);

        for (Item item : listItem) {
            flag = true;
            if (!item.getOperationList().isEmpty()) {
                flag = false;
                System.out.printf("%-10s %-23s %-17s %-13s [", item.getItemID(), item.getName(), item.getQuantity(), item.getPriority());
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
                    System.out.printf("%-10s %-23s %-17s %-17s [__]%n", item.getItemID(), item.getName(), item.getQuantity(), item.getPriority());
                }
            }
        }
    }

    /**
     * Displays detailed information about all machines.
     * For each machine, displays:
     * - ID
     * - Associated operation
     * - Processing time (formatted to two decimal places)
     * Machines are displayed based on their association with operations in the simulation.
     */
    private void showMachines() {
        System.out.printf("%s• MACHINES:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("%s-----------------------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        Map<Operation, Queue<Machine>> machinesMap = getShowInfController().getMachinesMap();
        System.out.printf("%s%-13s %-17s %-15s%s%n", ANSI_BRIGHT_WHITE, "ID", "Operation", "Processing Time", ANSI_RESET);
        System.out.printf("%s-----------------------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        for (Map.Entry<Operation, Queue<Machine>> entry : machinesMap.entrySet()) {
            for (Machine machine : entry.getValue()) {
                System.out.printf("%-13s %-17s %10.2f%n", machine.getId_machine(), machine.getOperation().getOperationName(), machine.getProcessingSpeed());
            }
        }
    }
}
