package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.OperationController;
import pt.ipp.isep.dei.esoft.project.domain.Operation;


import java.util.Optional;
import java.util.Scanner;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class AddOperationUI implements Runnable {


    private String operationName;
    private String operationDescription;
    private final OperationController controller;

    public AddOperationUI() {
        this.controller = new OperationController();
    }

    public OperationController getController() {
        return controller;
    }

    @Override
    public void run() {
        confirmAndSubmitOperation();

    }

    private String requestOperationName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the operation name: ");
        operationName = scanner.nextLine().trim();
        return operationName;
    }

    private String requestOperationDescription() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the operation description: ");
        operationDescription = scanner.nextLine().trim();
        return operationDescription;
    }

    private void displayTypedName(String typedOperationName) {
        System.out.printf("%nTyped name -> [" + ANSI_GREEN + "%s" + ANSI_RESET + "]%n"
                , typedOperationName);
    }

    private void displayTypedDescription(String typedOperationDescription) {
        System.out.printf("%nTyped description -> [" + ANSI_GREEN + "%s" + ANSI_RESET + "]%n"
                , typedOperationDescription);
    }

    private Optional<Operation> confirmOperationSubmission(String name, String description) {
        System.out.print("Do you wish to save the operation? (y/n): ");
        String answer = yesNoConfirmation();

        if (answer.equalsIgnoreCase("y")) {
            return description != null ? getController().registerOperation(name, description)
                    : getController().registerOperation(name);
        }

        return Optional.empty();
    }

    private void confirmAndSubmitOperation() {
        String name = requestOperationName();
        displayTypedName(name);

        System.out.print("Do you wish to add an operation description to this operation? (y/n): ");
        String d = yesNoConfirmation();
        String description = null;

        if (d.equalsIgnoreCase("y")) {
            description = requestOperationDescription();
            displayTypedDescription(description);
        }

        Optional<Operation> operation = confirmOperationSubmission(name, description);

        if (operation.isPresent()) {
            System.out.println(ANSI_BRIGHT_GREEN + "Operation successfully registered!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_BRIGHT_RED + "Operation not registered - Already registered or cancelled!" + ANSI_RESET);
        }
    }

    private String yesNoConfirmation() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().toLowerCase();

        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.print("Please enter 'y' or 'n': ");
            answer = scanner.nextLine().toLowerCase();
        }

        return answer;
    }


}
