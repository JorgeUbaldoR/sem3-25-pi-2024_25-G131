package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.OperationController;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

import java.util.*;

public class AddOperationDescriptionUI implements Runnable {

    private final OperationController controller;

    public AddOperationDescriptionUI() {
        controller = new OperationController();
    }

    private OperationController getController() {
        return controller;
    }


    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "      Change operation description       " + ANSI_RESET + "\n");
        addDescription();
    }

    /**
     * Retrieves and displays the list of operations with their descriptions.
     *
     * @return A list of operations that were retrieved.
     */
    private List<Operation> seeOperations() {
        List<Operation> operationList = new LinkedList<>();
        Optional<List<Operation>> op = getController().getAllOperations();
        int c = 1;
        if (op.isPresent()) {
            operationList = op.get();
        }
        System.out.printf("%s(%d)%s - %s%n", ANSI_BRIGHT_BLACK, 0, ANSI_RESET, "Cancel");
        for (Operation operation : operationList) {
            if (operation.getOperationDescription() != null) {
                System.out.printf("%s(%d)%s - %-10s %s %s\"%s%s\"%s%n",
                        ANSI_BRIGHT_BLACK, c++, ANSI_RESET,
                        operation.getOperationName(), ":",
                        ANSI_FOREST_GREEN,
                        operation.getOperationDescription(),
                        ANSI_FOREST_GREEN,
                        ANSI_RESET
                );

            }
        }
        return operationList;
    }

    /**
     * Prompts the user to select an operation from a list and validates the input.
     *
     * @return The index of the selected operation.
     */
    private int selectOperation() {
        int selection = -1;
        boolean valid = false;
        Scanner scanner = new Scanner(System.in);
        List<Operation> list = seeOperations();

        do {
            try {
                System.out.print("Select an operation by the number: ");
                selection = scanner.nextInt();

                if (selection < 0 || selection > list.size()) {
                    System.out.println(ANSI_LIGHT_RED + "\nSelect a valid number: " + ANSI_RESET);
                } else {
                    valid = true;
                }

            } catch (InputMismatchException e) {
                System.out.println(ANSI_LIGHT_RED + "\nSelect a NUMBER!" + ANSI_RESET);
                scanner.next();
            }
        } while (!valid);

        return selection;
    }

    /**
     * Adds or updates the description for a selected operation after confirming with the user.
     */
    private void addDescription() {
        Optional<List<Operation>> op = getController().getAllOperations();
        if (op.isPresent()) {
            List<Operation> operationList = op.get();
            int option = selectOperation();
            if (option > 0) {

                Scanner scanner = new Scanner(System.in);

                System.out.print("• Description to be added: ");
                String description = scanner.nextLine();
                String currentDescription = operationList.get(option - 1).getOperationDescription();
                String confirmation = requestConfirmation(description, operationList.get(option - 1).getOperationName());

                if (confirmation.equals("y")) {
                    if (operationList.get(option - 1).setOperationDescription(description))
                        System.out.println(ANSI_BRIGHT_GREEN + "\nNew description successfully added!" + ANSI_RESET);
                } else {
                    operationList.get(option - 1).setOperationDescription(currentDescription);
                    System.out.println(ANSI_LIGHT_RED + "\nNo changes made!" + ANSI_RESET);
                }

            } else {
                System.out.println(ANSI_LIGHT_RED + "Canceled!" + ANSI_RESET);
            }
        } else
            System.out.println(ANSI_LIGHT_RED + "\nNo operations in the system!" + ANSI_RESET);
    }

    /**
     * Prompts the user to confirm an action with a yes or no response.
     *
     * @param answer        The proposed description to confirm.
     * @param operationName The name of the operation related to the description.
     * @return A confirmation response from the user ('y' or 'n').
     */
    private String requestConfirmation(String answer, String operationName) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("• Are you sure that \"%s%s%s\" will be your %s%s%s operation description? (y/n): ",
                ANSI_FOREST_GREEN, answer, ANSI_RESET, ANSI_FOREST_GREEN, operationName, ANSI_RESET);
        String confirmation = scanner.nextLine();
        if (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n")) {
            do {
                System.out.print("Select 'y' or 'n': ");
                confirmation = scanner.nextLine();
            } while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n"));
        }
        return confirmation;

    }

}
