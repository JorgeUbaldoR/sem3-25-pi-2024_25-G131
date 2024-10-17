package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MachineController;
import pt.ipp.isep.dei.esoft.project.application.controller.OperationController;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

import java.util.*;

public class AddMachineUI implements Runnable {
    private final MachineController machineController;
    private final OperationController operationController;
    private int machineID;
    private double executionTime;
    private Operation machineOperation;


    public AddMachineUI() {
        machineController = new MachineController();
        operationController = new OperationController();
    }

    public MachineController getMachineController() {
        return machineController;
    }

    public OperationController getOperationController() {
        return operationController;
    }


    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "               Add Machine                 " + ANSI_RESET + "\n");

        submitAndConfirmData();

    }

    private void submitAndConfirmData() {
        int id = requestMachineId();
        displayChosenID(id);

        Operation operation = operationSelection();
        displayChosenOperation(operation);

        double exTime = requestExecutionTime();
        displayChosenExTime(exTime);

        Optional<Machine> machine = confirmMachineSubmission(operation, id, exTime);

        if (machine.isPresent()) {
            System.out.println(ANSI_BRIGHT_GREEN + "Machine successfully registered!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_BRIGHT_RED + "Machine not registered - ID duplication!" + ANSI_RESET);
        }

    }


    private Optional<Machine> confirmMachineSubmission(Operation operation, int id, double exTime) {
        System.out.print("Do you wish to save the operation? (y/n): ");
        String answer = yesNoConfirmation();

        if (answer.equalsIgnoreCase("y")) {
            return getMachineController().registerMachine(operation, id, exTime);

        }

        return Optional.empty();
    }

    private void displayChosenOperation(Operation operation) {
        System.out.printf("Operation chosen -> [" + ANSI_GREEN + "%s" + ANSI_RESET + "]%n%n"
                , operation.getOperationName());
    }

    private void displayChosenID(int id) {
        System.out.printf("ID number chosen -> [" + ANSI_GREEN + "%d" + ANSI_RESET + "]%n%n", id);
    }

    private void displayChosenExTime(double exTime) {
        System.out.printf("Execution time chosen -> [" + ANSI_GREEN + "%.2f" + ANSI_RESET + "]%n%n", exTime);
    }


    private List<Operation> getOperations() {
        Optional<List<Operation>> operations = getOperationController().getAllOperations();
        List<Operation> list = new ArrayList<>();
        if (operations.isPresent()) {
            list = operations.get();
        }
        return list;
    }


    private Operation operationSelection() {
        List<Operation> operations = getOperations();
        Scanner scanner = new Scanner(System.in);
        int number = 1;

        for (Operation operation : operations) {
            System.out.printf("%s(%d)%s %s%n", ANSI_BRIGHT_BLACK, number, ANSI_RESET, operation.getOperationName());
            number++;
        }

        while (true) {
            try {
                System.out.print("Choose the operation: ");
                int choice = scanner.nextInt();

                if (choice >= 1 && choice <= operations.size()) {
                    machineOperation = operations.get(choice - 1);
                    break;
                } else {
                    System.out.println(ANSI_BRIGHT_RED + "Invalid choice, please select a number between 1 and " + operations.size() + "!" + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_BRIGHT_RED + "Please enter a valid number." + ANSI_RESET);
                scanner.next();
            }
        }
        return machineOperation;
    }

    private int requestMachineId() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter the machine id number: ");
                machineID = scanner.nextInt();

                if (machineID >= 0) {
                    break;
                } else {
                    System.out.println(ANSI_BRIGHT_RED + "Invalid input. Please enter a positive number for the machine ID." + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_BRIGHT_RED + "Invalid input. Please enter a valid integer." + ANSI_RESET);
                scanner.next();
            }
        }
        return machineID;
    }

    private double requestExecutionTime() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter the execution time of " + machineOperation.getOperationName() + " (in minutes): ");
                executionTime = scanner.nextDouble();
                if (executionTime > 0) {
                    break;
                } else {
                    System.out.println(ANSI_BRIGHT_RED + "Invalid input. Please enter a positive number for the execution time." + ANSI_RESET);
                }

            } catch (InputMismatchException e) {
                System.out.println(ANSI_BRIGHT_RED + "Invalid input. Please enter a valid number." + ANSI_RESET);
                scanner.next();
            }
        }
        return executionTime;
    }

    /**
     * Prompts the user for a yes or no confirmation.
     *
     * @return The user's answer as a lowercase string, either "y" or "n".
     */
    private String yesNoConfirmation() {
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine().toUpperCase();

        while (!answer.equals("Y") && !answer.equals("N")) {
            System.out.print("Please enter 'y' or 'n': ");
            answer = sc.nextLine().toLowerCase();
        }

        return answer;
    }
}
