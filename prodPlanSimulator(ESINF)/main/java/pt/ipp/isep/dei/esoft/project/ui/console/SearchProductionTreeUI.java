package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.*;

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
        //System.out.printf("%s%s%s%n",ANSI_BRIGHT_BLACK,"prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/boo.csv",ANSI_RESET);
        //System.out.print("Enter the path to the boo.csv:");
        //String path = otherin.nextLine();
        System.out.printf("Select the processing order to be used:%n");
        System.out.printf(" %s(1)%s - Search Material%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf(" %s(2)%s - Search Operation%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf(" %s(3)%s - Search by ID%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf(" %s(0)%s - Cancel%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        doChoice(getChoice(3), "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/boo.csv");

    }

    private int getChoice(int max) {
        int choice = 0;
        boolean valid = false;
        do {
            System.out.printf(" %sType your choice:%s ", ANSI_BRIGHT_BLACK, ANSI_RESET);
            try {
                choice = in.nextInt();

                if (choice < 0 || choice > max) {
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

    private void doChoice(int choice, String path) {
        try {
            if (getProductionTreeController().getInformations(path)) {
                switch (choice) {
                    case 1:
                        Item selectedMaterial = showAndSelectMaterial();
                        break;
                    case 2:
                        ID selectedOperationID = showAndSelectOperation();
                        searchInfOperation(selectedOperationID);
                        break;
                    case 3:
                        System.out.printf("%n%s• SEARCH BY ID:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
                        System.out.printf("%s-----------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
                        System.out.printf("%sExample of Input ->%s %sI-102%s %s|%s %sO-232%s%n%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, ANSI_RESET, ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, ANSI_RESET);
                        ID id = getInputID();
                        if (id != null) {
                            if(id.getTypeID() == TypeID.ITEM){

                            }else{
                                searchInfOperation(id);
                            }
                        }
                        break;

                    default:
                        System.out.println(ANSI_BRIGHT_RED + "\nLEAVING..." + ANSI_RESET);
                        break;
                }
                System.out.println("\n" + ANSI_BRIGHT_GREEN + "Information Successfully Shown!" + ANSI_RESET);
            } else {
                System.out.println("\n" + ANSI_BRIGHT_RED + "Operation canceled - File doesn't have information to be read" + ANSI_RESET);
            }
        } catch (Exception e) {
            System.out.println("\n" + ANSI_BRIGHT_RED + e.getMessage() + ANSI_RESET);
        }
    }

    private void searchInfOperation(ID selectedOperationID) {
        Node node = getProductionTreeController().getNode(selectedOperationID);
        String parentName = getProductionTreeController().findParentOperation(node);
        printNodeOperationInf("OPERATION", node, parentName);
    }

    private ID getInputID() {
        Scanner scanner = new Scanner(System.in);
        String inputID;
        System.out.print("Enter an ID: ");
        inputID = scanner.nextLine();

        if (!checkInputID(inputID)) {
            do {
                System.out.print("Enter an ID (follow the example): ");
                inputID = scanner.nextLine();
            } while (!checkInputID(inputID));
        }
        char type = inputID.charAt(0);
        int serial = Integer.parseInt(inputID.split("-")[1]);
        switch (Character.toUpperCase(type)) {
            case 'I':
                return new ID(serial, TypeID.ITEM);
            case 'O':
                return new ID(serial, TypeID.OPERATION);
            default:
                return null;
        }
    }

    private boolean checkInputID(String inputID) {
        char reference = inputID.charAt(0);
        reference = Character.toUpperCase(reference);
        return (reference == 'I' || reference == 'O') &&
                inputID.charAt(1) == '-' && Character.isDigit(inputID.charAt(2));
    }

    private void printNodeOperationInf(String type, Node node, String parentName) {
        System.out.printf("%n%n%s========================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("   %sNODE INFORMATION | %s NODE%s%n", ANSI_BRIGHT_WHITE, type, ANSI_RESET);
        System.out.printf("%s========================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s•%s Name Operation: %s%s%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, getProductionTreeController().findNameOperation(node.getOperationID()), ANSI_RESET);
        System.out.printf("%s•%s ID Operation: %s%s%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, node.getOperationID(), ANSI_RESET);
        System.out.printf("%s•%s Quantity: %sN/A%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, ANSI_RESET);
        if (parentName == null) {
            System.out.printf("%s•%s Parent Operation: %sNone%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_RED, ANSI_RESET);
        } else {
            System.out.printf("%s•%s Parent Operation: %s%s%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, parentName, ANSI_RESET);
        }


    }

    private ID showAndSelectOperation() {
        System.out.printf("%n%s• OPERATIONS:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("%s-----------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        Map<ID, String> listOperations = getProductionTreeController().getListToShow(0);

        Map<Integer, Map.Entry<ID, String>> options = showOptions(listOperations);
        int choice = getChoice(listOperations.size() - 1);

        return options.get(choice).getKey();
    }


    private Map<Integer, Map.Entry<ID, String>> showOptions(Map<ID, String> list) {
        int index = 0;
        Map<Integer, Map.Entry<ID, String>> options = new HashMap<>();
        for (Map.Entry<ID, String> entry : list.entrySet()) {
            System.out.printf("%s(%-3s%s - %sID:%s %s -> %sName:%s %s%n",
                    ANSI_BRIGHT_BLACK, index + ")", ANSI_RESET,
                    ANSI_BRIGHT_BLACK, ANSI_RESET, entry.getKey(),
                    ANSI_BRIGHT_BLACK, ANSI_RESET, entry.getValue());
            options.put(index, entry);
            index++;
        }
        return options;
    }

    private Item showAndSelectMaterial() {
        System.out.printf("%n%s• MATERIALS:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("%s-----------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        Map<ID, String> listMaterials = getProductionTreeController().getListToShow(1);
        showOptions(listMaterials);
        int choice = getChoice(listMaterials.size() - 1);
        return null;
        //return getProductionTreeController().findItemByName(listMaterials.get(choice));
    }
}
