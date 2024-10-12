package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.OperationController;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AddOperationDescription implements Runnable {

    private final OperationController controller;

    public AddOperationDescription() {
        controller = new OperationController();
    }

    private OperationController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Add description to an operation ------------------------");
        addDescription();


    }

    private void seeOperations() {
        List<Operation> operationList = new LinkedList<>();
        Optional<List<Operation>> op = controller.getAllOperations();
        int c = 1;
        if (op.isPresent()) {
            operationList = op.get();
        }
        for (Operation operation : operationList) {
            System.out.printf("%s(%d)%s %s%n", ANSI_PURPLE, c++, ANSI_RESET, operation.getOperationName());
        }
    }

    private int selectOperation() {
        Scanner scanner = new Scanner(System.in);
        seeOperations();
        System.out.println("Select an operation by the number: ");
        return scanner.nextInt();
    }

    private void addDescription() {
        Optional<List<Operation>> op = controller.getAllOperations();
        List<Operation> operationList = op.get();
        int option = selectOperation();
        Scanner scanner = new Scanner(System.in);
        if (operationList.get(option - 1).getOperationDescription() == null) {
            System.out.println(ANSI_BRIGHT_RED + "Selected Operation description is empty!" + ANSI_RESET);
        } else {
            System.out.printf("Current operation description: %s%s%s%n", ANSI_BRIGHT_YELLOW, operationList.get(option - 1).getOperationDescription(), ANSI_RESET);
        }
        System.out.print("New one: ");
        String description = scanner.nextLine();
        String currentDescription = operationList.get(option - 1).getOperationDescription();
        String confirmation = requestConfirmation(description, operationList.get(option - 1).getOperationName());

        if (confirmation.equals("y")) {
            operationList.get(option - 1).setOperationDescription(description);
        } else {
            operationList.get(option - 1).setOperationDescription(currentDescription);
        }

    }

    private String requestConfirmation(String answer, String operationName) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Are you sure that %s%s%s will be your %s%s%s operation description? (y/n): ", ANSI_YELLOW, answer, ANSI_RESET, ANSI_YELLOW, operationName, ANSI_RESET);
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
