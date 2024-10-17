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

    /**
     * Constructor of the AddMachineUI class.

     * This constructor is responsible for initializing the controllers
     * that will be used to manage machine-related and operation-related
     * actions in the system.
     */
    public AddMachineUI() {
        machineController = new MachineController();
        operationController = new OperationController();
    }

    /**
     * Method to retrieve the machine controller.

     * This method returns an instance of the MachineController that can be used
     * to manage and perform operations related to machines.
     *
     * @return an instance of MachineController
     */
    public MachineController getMachineController() {
        return machineController;
    }

    /**
     * Method to retrieve the operation controller.

     * This method returns an instance of the OperationController that can be used
     * to manage and perform operations related to the system's operations.
     *
     * @return an instance of OperationController
     */
    public OperationController getOperationController() {
        return operationController;
    }


    /**
     * The method that runs the process of adding a machine.

     * This is the main entry point for adding a machine via the user interface.
     * It prints the interface header and then invokes the submission and confirmation process.
     */
    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "               Add Machine                 " + ANSI_RESET + "\n");

        submitAndConfirmData();

    }


    /**
     * Handles the input and confirmation of machine data.

     * This method requests the user to input the machine's ID, operation, and execution time.
     * After receiving the data, it displays the user's choices and attempts to register the machine.
     * It provides feedback on whether the machine was successfully registered or if there was an ID duplication.
     */
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

    /**
     * Confirms whether the user wants to submit and save the machine.

     * This method prompts the user to confirm if they wish to save the operation.
     * If the user confirms with 'y', it calls the MachineController to register the machine
     * with the provided operation, ID, and execution time. If the user declines with 'n',
     * an empty Optional is returned, meaning no machine was saved.
     *
     * @param operation The operation associated with the machine.
     * @param id The ID of the machine to be registered.
     * @param exTime The execution time for the machine.
     * @return An Optional containing the Machine if successfully registered, or an empty Optional if not.
     */
    private Optional<Machine> confirmMachineSubmission(Operation operation, int id, double exTime) {
        System.out.print("Do you wish to save the operation? (y/n): ");
        String answer = yesNoConfirmation();

        if (answer.equalsIgnoreCase("y")) {
            return getMachineController().registerMachine(operation, id, exTime);

        }

        return Optional.empty();
    }

    /**
     * Displays the chosen operation back to the user.

     * This method prints the operation that the user has selected in a formatted manner,
     * with the operation name highlighted in green for visibility.
     *
     * @param operation The Operation object that was selected by the user.
     */
    private void displayChosenOperation(Operation operation) {
        System.out.printf("Operation chosen -> [" + ANSI_GREEN + "%s" + ANSI_RESET + "]%n%n"
                , operation.getOperationName());
    }

    /**
     * Displays the chosen ID number to the user.

     * This method formats and prints the ID number that the user has selected,
     * highlighting it in green for better visibility.
     *
     * @param id The ID number that was chosen by the user.
     */
    private void displayChosenID(int id) {
        System.out.printf("ID number chosen -> [" + ANSI_GREEN + "%d" + ANSI_RESET + "]%n%n", id);
    }


    /**
     * Displays the chosen execution time to the user.

     * This method formats and prints the execution time that the user has selected,
     * highlighting it in green and ensuring it is displayed with two decimal places.
     *
     * @param exTime The execution time chosen by the user, represented as a double.
     */
    private void displayChosenExTime(double exTime) {
        System.out.printf("Execution time chosen -> [" + ANSI_GREEN + "%.2f" + ANSI_RESET + "]%n%n", exTime);
    }


    /**
     * Retrieves a list of operations from the operation controller.

     * This method calls the operation controller to get all operations and
     * returns them as a list. If no operations are available, it returns
     * an empty list.
     *
     * @return A List of Operation objects, which may be empty if no operations are available.
     */
    private List<Operation> getOperations() {
        Optional<List<Operation>> operations = getOperationController().getAllOperations();
        List<Operation> list = new ArrayList<>();
        if (operations.isPresent()) {
            list = operations.get();
        }
        return list;
    }


    /**
     * Prompts the user to select an operation from a list of available operations.

     * This method displays all operations to the user and allows them to
     * choose one by entering the corresponding number. It validates the user's
     * input and ensures they select a valid option. If the input is invalid,
     * it prompts the user again until a valid selection is made.
     *
     * @return The selected Operation object based on the user's choice.
     */
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

    /**
     * Prompts the user to enter a machine ID number.

     * This method continuously asks the user for a valid machine ID until
     * a positive integer is provided. It handles invalid inputs, such as
     * non-integer values and negative numbers, by displaying appropriate
     * error messages and re-prompting the user.
     *
     * @return The valid machine ID number entered by the user as an integer.
     */
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


    /**
     * Prompts the user to enter the execution time for a specific operation.

     * This method continually requests input from the user until a valid,
     * positive number is provided for the execution time. It handles
     * invalid inputs, such as non-numeric values and negative numbers,
     * by displaying appropriate error messages and re-prompting the user.
     *
     * @return The valid execution time entered by the user as a double.
     */
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
