package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class ProductionTreeUI implements Runnable {
    private final String DEFAULT_PATH = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/boo.csv";
    private final Scanner in = new Scanner(System.in);
    private ProductionTreeController controller;

    public ProductionTreeUI() {
        controller = new ProductionTreeController();
    }

    private ProductionTreeController getProductionTreeController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "            Production Tree                 " + ANSI_RESET + "\n");

        System.out.printf("Select a option:%n");
        System.out.printf("     %s(1)%s - Use Default File%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("     %s(2)%s - Insert Path File%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("     %s(0)%s - Cancel%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        doChoice(getChoice());

    }
    private void doChoice(int choice) {
        Scanner in = new Scanner(System.in);
        String name;
        switch (choice) {
            case 1:
                System.out.println(ANSI_BRIGHT_GREEN + "\n\n         GENERATING BOO | DEFAULT FILE" + ANSI_RESET+"\n");
                System.out.print("Enter a name to your production tree:");
                name = in.nextLine();
                confirmationData(name,DEFAULT_PATH);
                break;

            case 2:
                System.out.println(ANSI_BRIGHT_GREEN + "\n\n         GENERATING BOO | SPECIFIC FILE" + ANSI_RESET+"\n");
                System.out.print("Enter a name to your production tree:");
                name = in.nextLine();
                System.out.print("Enter the path to the boo.csv:");
                String path = in.nextLine();
                confirmationData(name,path);
                break;

            default:
                System.out.println(ANSI_BRIGHT_RED + "\nLEAVING..." + ANSI_RESET);
                break;
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







    private void showTree(ProductionTree productionTree, String name) {
        System.out.printf("%n%n══════════|PRODUCTION TREE: %s%s%s|══════════%n%n",ANSI_BRIGHT_WHITE,name,ANSI_RESET);
        for (Node node : productionTree.getNodesOfTree()){
            printNode(node,1);
        }
    }


    private void printNode(Node node, int level) {
        String indent = "  ".repeat(level - 1) + (level > 1 ? "├─ " : "");

        System.out.println(indent + ANSI_BRIGHT_WHITE+"Operation: " + node.getOperationID()+ANSI_RESET);

        System.out.println(indent + ANSI_BRIGHT_BLACK+"│  └─"+ANSI_RESET+" Item: " + node.getItemID() + ", Quantity: " + node.getItem_qtd());

        System.out.println(indent +  ANSI_BRIGHT_BLACK+"│  ├─"+ANSI_RESET +" Operations:");

        if (!node.getOperationMap().isEmpty()) {
            for (Map.Entry<ID, Float> entry : node.getOperationMap().entrySet()) {
                System.out.println(indent +  ANSI_BRIGHT_BLACK+"│     ├─ "+ ANSI_RESET+ entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println(indent +  ANSI_BRIGHT_BLACK+"│     └─"+ANSI_RESET +" (No operations)");
        }

        System.out.println(indent +  ANSI_BRIGHT_BLACK+"│  └─"+ANSI_RESET+" Materials:");
        if (!node.getMaterialMap().isEmpty()) {
            for (Map.Entry<ID, Float> entry : node.getMaterialMap().entrySet()) {
                System.out.println(indent +  ANSI_BRIGHT_BLACK+"│     ├─ "+ANSI_RESET + entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println(indent +  ANSI_BRIGHT_BLACK+"│     └─"+ANSI_RESET+" (No materials)");
        }
    }

    private void displayOption(String name,int flag) {
        if (flag == 0) {
            System.out.printf("%nChosen Name -> [" + ANSI_GREEN + "%s" + ANSI_RESET + "]", name);
        }else{
            System.out.printf("%nChosen Operation -> [" + ANSI_GREEN + "%s" + ANSI_RESET + "]", name);
        }
    }


    private void confirmationData(String name, String path) {
        displayOption(name,0);
        displayOption(path,1);

        System.out.print("\nDo you wish to save the operation? (y/n): ");
        String answer = yesNoConfirmation();

        if(answer.equalsIgnoreCase("y")){
            getProductionTreeController().setName(name);
            try{
                if(getProductionTreeController().getInformations(path)){
                    showTree(getProductionTreeController().getProductionTree(),name);
                    System.out.println("\n"+ANSI_BRIGHT_GREEN + "Production Tree successfully generated!" + ANSI_RESET);
                }else{
                    System.out.println("\n"+ANSI_BRIGHT_RED + "Operation canceled - File doesn't have information to be read" + ANSI_RESET);
                }

            } catch (Exception e) {
                System.out.println("\n"+ANSI_BRIGHT_RED +e.getMessage()+ ANSI_RESET);
            }

        }else{
            System.out.println("\n"+ANSI_BRIGHT_RED + "Operation canceled." + ANSI_RESET);
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
