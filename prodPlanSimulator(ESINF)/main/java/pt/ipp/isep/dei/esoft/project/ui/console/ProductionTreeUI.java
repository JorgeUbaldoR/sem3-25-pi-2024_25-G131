package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;

import java.util.Map;
import java.util.Scanner;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class ProductionTreeUI implements Runnable {

    private ProductionTreeController controller;

    public ProductionTreeUI() {
        controller = new ProductionTreeController();
    }

    private ProductionTreeController getProductionTreeController() {
        return controller;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "            Production Tree                 " + ANSI_RESET + "\n");

        System.out.printf("Enter a name to your production tree:");
        String name = scanner.nextLine();
        System.out.printf("Enter the path to the boo.csv:");
        String path = scanner.nextLine();
        confirmationData(name,path);


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
                    System.out.println(ANSI_BRIGHT_GREEN + "%nProduction Tree successfully generated!" + ANSI_RESET);
                }else{
                    System.out.println(ANSI_BRIGHT_RED + "%nOperation canceled - File doesn't have information to be read" + ANSI_RESET);
                }

            } catch (Exception e) {
                System.out.println(ANSI_BRIGHT_RED +e.getMessage()+ ANSI_RESET);
            }

        }else{
            System.out.println(ANSI_BRIGHT_RED + "%nOperation canceled." + ANSI_RESET);
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
