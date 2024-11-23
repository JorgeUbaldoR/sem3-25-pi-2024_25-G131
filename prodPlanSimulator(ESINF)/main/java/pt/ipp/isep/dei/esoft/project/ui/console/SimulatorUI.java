package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SimulatorController;

import java.util.*;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

/**
 * SimulatorUI class handles the user interface for simulating a process with or without priority.
 * It interacts with the `SimulatorController` to start simulations based on the user's choice.
 */
public class SimulatorUI implements Runnable {

    private final Scanner in = new Scanner(System.in);
    private SimulatorController controller;

    /**
     * Constructor initializes the SimulatorController instance.
     */
    public SimulatorUI() {
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
     * Starts the simulation UI, prompts the user to select the processing order for the simulation,
     * and calls the appropriate method based on the user's choice.
     */
    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "               Simulation                 " + ANSI_RESET + "\n");
        getSimulationController().startSimulationWithOutPriority();
//        System.out.printf("Select the processing order to be used:%n");
//        System.out.printf("     %s(1)%s - Without Priority%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
//        System.out.printf("     %s(2)%s - Using Priority%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
//        System.out.printf("     %s(0)%s - Cancel%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
//        doChoice(getChoice());
    }

    /**
     * Prompts the user to make a choice between the simulation modes (with priority, without priority, or cancel).
     * Ensures valid input from the user.
     *
     * @return The user's choice as an integer (0, 1, or 2).
     */
    private int getChoice() {
        int choice = 0;
        boolean valid = false;
        do {
            System.out.printf("Type your choice: ");
            try {
                choice = in.nextInt();

                if (choice < 0 || choice > 2) {
                    System.out.println(ANSI_LIGHT_RED + "Select a valid number: " + ANSI_RESET);
                } else {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_LIGHT_RED + "Invalid choice. Please try again: " + ANSI_RESET);
                in.next(); // Consume the invalid input
            }
        } while (!valid);
        return choice;
    }

    /**
     * Executes the simulation based on the user's choice.
     *
     * @param choice The user's choice for the simulation (1 for no priority, 2 for priority, 0 for cancel).
     */
    private void doChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println(ANSI_BRIGHT_GREEN + "\n\n         START OF THE SIMULATION WITHOUT PRIORITY..." + ANSI_RESET);
                getSimulationController().startSimulationWithOutPriority();
                break;

            case 2:
                System.out.println(ANSI_BRIGHT_GREEN + "\n\n         START OF THE SIMULATION WITH PRIORITY..." + ANSI_RESET);
                getSimulationController().startSimulationWithPriority();
                break;

            default:
                System.out.println(ANSI_BRIGHT_RED + "\nLEAVING..." + ANSI_RESET);
                break;
        }
    }
}



