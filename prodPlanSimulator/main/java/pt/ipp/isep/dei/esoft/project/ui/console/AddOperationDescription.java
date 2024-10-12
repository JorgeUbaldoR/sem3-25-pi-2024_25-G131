package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.OperationController;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AddOperationDescription implements Runnable{

    private final OperationController controller;

    public AddOperationDescription()  {
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
        Scanner description = new Scanner(System.in);
        if (operationList.get(option - 1).getOperationDescription() == null) {
            System.out.println(ANSI_BRIGHT_RED + "Selected Operation description is empty!" + ANSI_RESET);
        } else {
            System.out.printf("Current operation description: %s%s%s%n", ANSI_BRIGHT_YELLOW, operationList.get(option - 1).getOperationDescription(), ANSI_RESET);
        }
        System.out.print("New one: ");
        description.nextLine();

    }


}
