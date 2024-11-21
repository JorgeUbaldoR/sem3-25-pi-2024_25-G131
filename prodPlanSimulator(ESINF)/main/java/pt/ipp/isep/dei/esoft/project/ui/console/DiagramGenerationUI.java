package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.DiagramGenerationController;

import java.util.InputMismatchException;
import java.util.Scanner;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_RESET;

public class DiagramGenerationUI implements Runnable {

    private final Scanner in = new Scanner(System.in);
    private DiagramGenerationController controller;

    public DiagramGenerationUI() {
        controller = new DiagramGenerationController();
    }

    private DiagramGenerationController getDiagramController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "            DIAGRAM GENERATION                 " + ANSI_RESET + "\n");
        System.out.printf("Select the processing order to be used:%n");
        System.out.printf("     %s(1)%s - Generate BOO%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("     %s(2)%s - Generate BOM%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("     %s(0)%s - Cancel%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        doChoice(getChoice());

    }

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
                in.next();

            }
        } while (!valid);
        return choice;
    }

    private void doChoice(int choice) {
        Scanner in = new Scanner(System.in);
        String path;
        switch (choice) {
            case 1:
                System.out.print("Enter the path to the .csv file: ");
                path = in.nextLine();
                confirmationData(path,0);
                break;
            case 2:
                System.out.print("Enter the path to the .csv file: ");
                path = in.nextLine();
                confirmationData(path,1);
                break;

            default:
                System.out.println(ANSI_BRIGHT_RED + "\nLEAVING..." + ANSI_RESET);
                break;
        }
    }

    private void displayOption(String saveFilePath) {
        System.out.printf("%nChosen path -> [" + ANSI_GREEN + "%s" + ANSI_RESET + "]", saveFilePath);
    }

    private void confirmationData(String saveFilePath, int flag) {
        displayOption(saveFilePath);

        System.out.print("\nDo you wish to save the operation? (y/n): ");
        String answer = yesNoConfirmation();

        if (answer.equalsIgnoreCase("y")) {
            try {
                if (getDiagramController().getInformations(saveFilePath)) {
                    if(flag == 0) {
                        System.out.println(ANSI_BRIGHT_GREEN + "\n\nGENERATING BOO..." + ANSI_RESET);
                        getDiagramController().writeBooToUmlFile();
                    }else{
                        System.out.println(ANSI_BRIGHT_GREEN + "\n\nGENERATING BOM..." + ANSI_RESET);
                        getDiagramController().writeBOMToUmlFile();
                    }
                    System.out.println("\n" + ANSI_BRIGHT_GREEN + "Graph successfully generated!" + ANSI_RESET);
                    System.out.println(ANSI_BRIGHT_YELLOW + "!IMPORTANT! Check the files/output for the file" + ANSI_RESET );
                } else {
                    System.out.println("\n" + ANSI_BRIGHT_RED + "Operation canceled - File doesn't have information to be read" + ANSI_RESET);
                }

            } catch (Exception e) {
                System.out.println("\n" + ANSI_BRIGHT_RED + e.getMessage() + ANSI_RESET);
            }

        } else {
            System.out.println("\n" + ANSI_BRIGHT_RED + "Operation canceled." + ANSI_RESET);
        }

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
            answer = sc.nextLine().toUpperCase();
        }

        return answer;
    }
}
