package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.*;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_RESET;

/**
 * The {@code SearchProductionTreeUI} class provides a user interface for searching and displaying
 * information about materials and operations in the production tree.
 * It interacts with the {@link ProductionTreeController} to retrieve the data and display it to the user.
 *
 * Features:
 * - Search for materials, operations, or by ID.
 * - View information of selected items or operations.
 * - Choose between default or custom input files.
 *
 * This class implements the {@link Runnable} interface, allowing it to be executed as part of a larger system.
 *
 * Complexity:
 * The overall complexity of this class depends on the user interaction and size of the data. Most methods involve
 * linear-time operations (e.g., iterating over lists or validating inputs).
 */
public class SearchProductionTreeUI implements Runnable {
    private final Scanner in = new Scanner(System.in);
    private final ProductionTreeController controller;

    /**
     * Constructs a {@code SearchProductionTreeUI} object that interacts with the {@link ProductionTreeController}.
     */
    public SearchProductionTreeUI() {
        controller = new ProductionTreeController();
    }

    /**
     * Returns the {@link ProductionTreeController} associated with this UI.
     *
     * @return the production tree controller
     */
    private ProductionTreeController getProductionTreeController() {
        return controller;
    }

    /**
     * Starts the user interface for searching the production tree.
     * Displays the search options and processes the user's choices.
     *
     * Complexity: O(n), where n depends on the complexity of `doChoice`.
     */
    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "        SEARCH OPERATION/MATERIAL                 " + ANSI_RESET + "\n");

        System.out.printf("Select type of search:%n");
        System.out.printf(" %s(1)%s - Search Material%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf(" %s(2)%s - Search Operation%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf(" %s(3)%s - Search by ID%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf(" %s(0)%s - Cancel%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        int searchChoice = getChoice(3);

        if(searchChoice != 0) {
            System.out.printf("%n%s!IMPORTANT!%s Select your preference:%n",ANSI_BRIGHT_YELLOW,ANSI_RESET);
            System.out.printf(" %s(1)%s - Use Default File%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
            System.out.printf(" %s(2)%s - Insert Path File%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
            int preferenceChoice = getChoice(2);
            if(preferenceChoice == 1) {
                doChoice(searchChoice, "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/boo.csv");
            }else {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter path: ");
                String path = scanner.nextLine();
                doChoice(searchChoice,path );
            }
        }
    }


    /**
     * Prompts the user for a choice and validates the input.
     * Ensures the choice is within the range of 0 to the specified maximum value.
     *
     * Complexity: O(1) for each input validation.
     *
     * @param max the maximum number of valid options
     * @return the user's choice
     */
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

    /**
     * Processes the user's choice and performs the corresponding search operation.
     * Delegates to specific methods based on the selected option.
     *
     * Complexity:
     * - Case 1 (search material): O(m), where m is the size of the materials list.
     * - Case 2 (search operation): O(o), where o is the size of the operations list.
     * - Case 3 (search by ID): O(1), as it involves constant-time checks and retrievals.
     *
     * @param choice the user's search choice
     * @param path   the path to the data file
     */
    private void doChoice(int choice, String path) {
        try {
            if (getProductionTreeController().getInformations(path)) {
                switch (choice) {
                    case 1:
                        ID selectedItemID = showAndSelectMaterial();
                        searchInfItem(selectedItemID);
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
                                searchInfItem(id);
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


    /**
     * Searches and displays information about the selected material.
     *
     * @param selectedItemID the ID of the selected material
     */
    private void searchInfItem(ID selectedItemID) {
        boolean rawMaterial = getProductionTreeController().isRawMaterial(selectedItemID);
        Node node = getProductionTreeController().getItemNode(selectedItemID,rawMaterial);
        if(node == null) {
            throw new IllegalArgumentException("Make sure ITEM belongs to the inserted path BOO");
        }
        String[] parentAndQtd = getProductionTreeController().findParentItem(node,rawMaterial,selectedItemID);
        printNodeItemInf(node,parentAndQtd[0],selectedItemID,parentAndQtd[1]);
    }


    /**
     * Displays detailed information about the selected material node.
     *
     * @param node the material node
     * @param parentName the name of the parent operation (if any)
     * @param selectedItemID the ID of the selected material
     * @param qtd the quantity of the material
     */
    private void printNodeItemInf(Node node, String parentName,ID selectedItemID,String qtd) {
        System.out.printf("%n%n%s========================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("     %sNODE INFORMATION | %s NODE%s%n", ANSI_BRIGHT_WHITE, "MATERIAL", ANSI_RESET);
        System.out.printf("%s========================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s•%s Name Material: %s%s%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE,getProductionTreeController().findNameItem(selectedItemID), ANSI_RESET);
        System.out.printf("%s•%s ID Material: %s%s%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, selectedItemID, ANSI_RESET);
        System.out.printf("%s•%s Quantity: %s%s%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE,qtd ,ANSI_RESET);
        if (parentName == null) {
            System.out.printf("%s•%s Parent Operation: %sNone%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_RED, ANSI_RESET);
        } else {
            System.out.printf("%s•%s Parent Operation: %s%s%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, parentName, ANSI_RESET);
        }
    }

    /**
     * Searches and displays information about the selected operation.
     *
     * @param selectedOperationID the ID of the selected operation
     */
    private void searchInfOperation(ID selectedOperationID) {
        Node node = getProductionTreeController().getOperationNode(selectedOperationID);
        if(node == null) {
            throw new IllegalArgumentException("Make sure operation belongs to the inserted path BOO");
        }
        String parentName = getProductionTreeController().findParentOperation(node);
        printNodeOperationInf(node, parentName,selectedOperationID);
    }

    /**
     * Prompts the user to enter an ID (either item or operation) and validates the input.
     *
     * @return the ID entered by the user, or null if invalid input is provided.
     *
     * Complexity:
     * - Validation loop: O(n), where n is the number of attempts until valid input is provided.
     * - String parsing and ID creation: O(1).
     * Overall: O(n), dominated by the input validation loop.
     */
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

    /**
     * Checks if the provided input ID follows the expected format (e.g., "I-123" or "O-456").
     *
     * @param inputID the input string to validate.
     * @return true if the input is valid; false otherwise.
     *
     * Complexity: O(1) as it performs a constant number of character checks.
     */
    private boolean checkInputID(String inputID) {
        char reference = inputID.charAt(0);
        reference = Character.toUpperCase(reference);
        return (reference == 'I' || reference == 'O') &&
                inputID.charAt(1) == '-' && Character.isDigit(inputID.charAt(2));
    }

    /**
     * Displays detailed information about the selected operation node.
     *
     * @param node the operation node
     * @param parentName the name of the parent operation (if any)
     * @param selectedOperationID the ID of the selected operation
     */
    private void printNodeOperationInf(Node node, String parentName,ID selectedOperationID) {
        System.out.printf("%n%n%s========================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("   %sNODE INFORMATION | %s NODE%s%n", ANSI_BRIGHT_WHITE, "OPERATION", ANSI_RESET);
        System.out.printf("%s========================================%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s•%s Name Operation: %s%s%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, getProductionTreeController().findNameOperation(node.getOperationID()), ANSI_RESET);
        System.out.printf("%s•%s ID Operation: %s%s%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, selectedOperationID, ANSI_RESET);
        System.out.printf("%s•%s Quantity: %sN/A%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, ANSI_RESET);
        if (parentName == null) {
            System.out.printf("%s•%s Parent Operation: %sNone%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_RED, ANSI_RESET);
        } else {
            System.out.printf("%s•%s Parent Operation: %s%s%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET, ANSI_BRIGHT_WHITE, parentName, ANSI_RESET);
        }
    }

    /**
     * Displays a list of available operations and allows the user to select one.
     *
     * @return the ID of the selected operation
     */
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

    /**
     * Displays a list of available materials and allows the user to select one.
     *
     * @return the ID of the selected material
     */
    private ID showAndSelectMaterial() {
        System.out.printf("%n%s• MATERIALS:%s%n", ANSI_BRIGHT_WHITE, ANSI_RESET);
        System.out.printf("%s-----------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        Map<ID, String> listMaterials = getProductionTreeController().getListToShow(1);

        Map<Integer, Map.Entry<ID, String>> options = showOptions(listMaterials);
        int choice = getChoice(listMaterials.size() - 1);

        return options.get(choice).getKey();
    }
}
