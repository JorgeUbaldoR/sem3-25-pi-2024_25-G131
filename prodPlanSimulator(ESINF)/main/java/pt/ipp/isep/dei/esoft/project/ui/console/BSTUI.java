package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.BSTController;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ObjectBST;

import java.util.InputMismatchException;
import java.util.Scanner;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_RESET;

public class BSTUI implements Runnable{

    private final Scanner in = new Scanner(System.in);
    private BSTController controller;

    public BSTUI() {
        controller = new BSTController();
    }

    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "           QUANTITIES BY MATERIAL                 " + ANSI_RESET + "\n");

        System.out.printf("Select a option:%n");
        System.out.printf("     %s(1)%s - Increasing Order%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("     %s(2)%s - Decreasing Order%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("     %s(0)%s - Cancel%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        doChoice(getChoice());

    }

    private void doChoice(int choice) {
        Scanner in = new Scanner(System.in);
        switch (choice) {
            case 1:
                System.out.println(ANSI_BRIGHT_GREEN + "\n\n         GENERATING LIST | INCREASING ORDER" + ANSI_RESET+"\n");
                printItems(controller.generateTreeIncrescingOrder());
                break;

            case 2:
                System.out.println(ANSI_BRIGHT_GREEN + "\n\n         GENERATING LIST | DECREASING ORDER" + ANSI_RESET+"\n");
                printItems(controller.generateTreeDecrescingOrder());
                break;

            default:
                System.out.println(ANSI_BRIGHT_RED + "\nLEAVING..." + ANSI_RESET);
                break;
        }
    }

    private void printItems(Iterable<ObjectBST> objectBSTS) {
        for (ObjectBST object : objectBSTS) {
            System.out.println(object.toString());
        }
    }

    private int getChoice () {
        int choice = 0;
        boolean valid = false;
        do {
            System.out.print("Type your choice: ");
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



}
