package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_RESET;

public class SearchProductionTreeUI implements Runnable {
    private final Scanner in = new Scanner(System.in);
    private final ProductionTreeController controller;

    public SearchProductionTreeUI() {
        controller = new ProductionTreeController();
    }

    private ProductionTreeController getProductionTreeController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "        SEARCH OPERATION/MATERIAL                 " + ANSI_RESET + "\n");
        Scanner otherin = new Scanner(System.in);
        System.out.print("Enter the path to the boo.csv:");
        String path = otherin.nextLine();
        System.out.printf("Select the processing order to be used:%n");
        System.out.printf("     %s(1)%s - Search Material%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("     %s(2)%s - Search Operation%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("     %s(0)%s - Cancel%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        doChoice(getChoice(),path);

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

    private void doChoice(int choice,String path) {
        try{
            if(getProductionTreeController().getInformations(path)){
                switch (choice) {
                    case 1:
                        Item selectedMaterial = showAndSelectMaterial();
                        break;

                    case 2:
                        Operation selectedOperation = showAndSelectOperation();
                        break;

                    default:
                        System.out.println(ANSI_BRIGHT_RED + "\nLEAVING..." + ANSI_RESET);
                        break;
                }
                System.out.println("\n"+ANSI_BRIGHT_GREEN + "Information Successfully Shown!" + ANSI_RESET);
            }else{
                System.out.println("\n"+ANSI_BRIGHT_RED + "Operation canceled - File doesn't have information to be read" + ANSI_RESET);
            }
        } catch (Exception e) {
            System.out.println("\n"+ANSI_BRIGHT_RED +e.getMessage()+ ANSI_RESET);
        }
    }

    private Operation showAndSelectOperation() {
        System.out.printf("%n%s• OPERATIONS:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("%s-----------------------------------%s%n",ANSI_BRIGHT_BLACK,ANSI_RESET);
        List<String> listOperations = getProductionTreeController().getListToShow(0);
        showOptions(listOperations);
        return null;
    }

    private void showOptions(List<String> listMaterials) {
        for (int i = 0; i < listMaterials.size(); i++) {
            System.out.printf("%s(%-3s%s - %s%n",ANSI_BRIGHT_BLACK,i+")",ANSI_RESET,listMaterials.get(i));
        }
    }

    private Item showAndSelectMaterial() {
        System.out.printf("%n%s• MATERIALS:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("%s-----------------------------------%s%n",ANSI_BRIGHT_BLACK,ANSI_RESET);
        List<String> listMaterials = getProductionTreeController().getListToShow(1);
        showOptions(listMaterials);
        return null;
    }
}
