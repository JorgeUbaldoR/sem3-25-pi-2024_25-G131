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
        System.out.println(ANSI_BRIGHT_WHITE+"      Change operation description       "+ANSI_RESET+"\n");
        addDescription();
    }

    private List<Operation> seeOperations() {
        List<Operation> operationList = new LinkedList<>();
        Optional<List<Operation>> op = getController().getAllOperations();
        int c = 1;
        if (op.isPresent()) {
            operationList = op.get();
        }
        for (Operation operation : operationList) {
            if (operation.getOperationDescription() != null) {
                System.out.printf("%s(%d)%s - %-10s %s %s\"%s\"%s%n", ANSI_BRIGHT_BLACK, c++, ANSI_RESET,
                        operation.getOperationName(),":", ANSI_FOREST_GREEN,
                        operation.getOperationDescription(), ANSI_RESET);

            } else {
                operation.setOperationDescription(ANSI_BRIGHT_RED + "No description provided!" + ANSI_RESET);
                System.out.printf("%s(%d)%s - %-10s %s %s%n", ANSI_BRIGHT_BLACK, c++, ANSI_RESET,
                        operation.getOperationName(),":",operation.getOperationDescription());
            }
        }
        return operationList;
    }

    private int selectOperation() {
        int selection = 0;
        boolean valid = false;
        Scanner scanner = new Scanner(System.in);
        List<Operation> list = seeOperations();

        do {
            try {
                System.out.print("Select an operation by the number: ");
                selection = scanner.nextInt();

                if (selection <= 0 || selection > list.size()) {
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

    private void addDescription() {
        Optional<List<Operation>> op = getController().getAllOperations();
        if (op.isPresent()) {
            List<Operation> operationList = op.get();
            int option = selectOperation();
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

        } else
            System.out.println(ANSI_LIGHT_RED + "\nNo operations in the system!" + ANSI_RESET);
    }

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
