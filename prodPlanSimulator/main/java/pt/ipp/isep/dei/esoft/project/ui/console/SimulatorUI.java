package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SimulatorController;

import java.util.*;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class SimulatorUI implements Runnable {

    private final Scanner in = new Scanner(System.in);
    private SimulatorController controller;

    public SimulatorUI() {
        controller = new SimulatorController();
    }

    private SimulatorController getSimulationController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "               Simulation                 " + ANSI_RESET + "\n");
        System.out.printf("Select the processing order to be used:%n");
        System.out.printf("     %s(1)%s - Without Priority%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("     %s(2)%s - Using Priority%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("     %s(0)%s - Cancel%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        doChoice(getChoice());

    }

    private int getChoice () {
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
                in.next();

            }
        } while (!valid);
        return choice;
    }

    private void doChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println(ANSI_BRIGHT_GREEN + "\nSTART OF THE SIMULATION WITHOUT PRIORITY..." + ANSI_RESET);
                getSimulationController().startSimulationWithOutPriority();
                break;

            case 2:
                System.out.println(ANSI_BRIGHT_GREEN + "\nSTART OF THE SIMULATION WITH PRIORITY..." + ANSI_RESET);
                getSimulationController().startSimulationWithPriority();
                break;

            default:
                System.out.println(ANSI_BRIGHT_RED + "\nLEAVING..." + ANSI_RESET);
                break;
        }
    }


}


