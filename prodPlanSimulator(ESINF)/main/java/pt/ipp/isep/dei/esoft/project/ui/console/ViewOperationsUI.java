package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.MachineController;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.util.*;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

/**
 * A interface de usuário para exibição das operações e suas respectivas máquinas.
 * Esta classe implementa a interface Runnable, permitindo que seja executada como uma tarefa.
 */
/**
 * ViewOperationsUI class displays operations and their associated machines in the console.
 * It uses the MachineController to access machine and operation data and prints the results to the user interface.
 */
public class ViewOperationsUI implements Runnable {

    private final MachineController machineController;

    /**
     * Constructor that initializes the machineController.
     */
    public ViewOperationsUI() {
        machineController = new MachineController();
    }

    /**
     * Returns the instance of the MachineController.
     *
     * @return machineController instance.
     */
    private MachineController getMachineController() {
        return machineController;
    }

    /**
     * Starts the UI by displaying the header and calling the submitData() method
     * to generate and display operation and machine information.
     */
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE+"        List [Operation->Machines]\n"+ANSI_RESET);
        submitData();
    }

    /**
     * Requests the list of machines through the MachineController and prints the results.
     * If the machine list is successfully retrieved, it displays the operation-machine associations.
     * Otherwise, it prints an error message.
     */
    private void submitData() {
        Optional<List<Machine>> machineList = getMachineController().requestList();

        if (machineList.isPresent()) {
            printResult();
            System.out.println(ANSI_BRIGHT_GREEN + "\nList successfully generated!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_BRIGHT_RED + "LEAVING..." + ANSI_RESET);
        }
    }

    /**
     * Connects operations to their respective machines.
     *
     * @return A map where each operation is associated with a list of machines that perform it.
     */
    private Map<Operation, List<Machine>> connectOperationToMachines() {
        Map<Operation, List<Machine>> operationToMachines = new HashMap<>();
        Optional<List<Machine>> machineList = getMachineController().requestList();
        if (machineList.isPresent()) {
            for (Machine machine : machineList.get()) {
                operationToMachines.putIfAbsent(machine.getOperation(), new ArrayList<>());
                operationToMachines.get(machine.getOperation()).add(machine);
            }
        } else {
            System.out.println(ANSI_BRIGHT_RED + "\nRequest failed!" + ANSI_RESET);
        }
        return operationToMachines;
    }

    /**
     * Prints the results of operations and their associated machines to the console.
     */
    private void printResult() {
        Map<Operation, List<Machine>> operationToMachines = connectOperationToMachines();
        for (Map.Entry<Operation, List<Machine>> entry : operationToMachines.entrySet()) {
            Operation operation = entry.getKey();
            List<Machine> machines = entry.getValue();

            System.out.printf("[%-9s%s is performed by machines: %s%n",
                    operation.getOperationName()+"]" ,ANSI_BRIGHT_BLACK, ANSI_RESET);

            for (Machine machine : machines) {
                System.out.printf(" • %s%s%s%n", ANSI_BRIGHT_WHITE, machine.getId_machine(), ANSI_RESET);
            }
        }
    }

    // Not currently in use, maybe later...

    /**
     * Requests the user whether they want to see which machines perform a certain operation.
     *
     * @return 'y' if the user wants to see the list of machines, 'n' otherwise.
     */
    private String requestList() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to see which machines perform a certain operation? (y/n): ");
        String activation = scanner.nextLine().toLowerCase();

        while (!activation.equals("y") && !activation.equals("n")) {
            System.out.print("Please enter 'y' or 'n': ");
            activation = scanner.nextLine().toLowerCase();
        }

        return activation;
    }

    /**
     * Asks the user if they want to continue the process.
     *
     * @return true if the user wants to continue, false otherwise.
     */
    private boolean continueProcess() {
        return requestList().equals("y");
    }
}
