package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DiagramGenerationController;
import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.domain.UpdateMaterial;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.InputMismatchException;
import java.util.Scanner;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_RESET;

public class UpdateQuantityUI implements Runnable {

    private final Scanner in = new Scanner(System.in);
    private ProductionTreeUI pdtUI;
    private ProductionTreeController pdtControler;
    private DiagramGenerationController controller;

    /**
     * Default constructor initializing necessary controllers and UI.
     */
    public UpdateQuantityUI() {
        this.pdtUI = new ProductionTreeUI();
        this.pdtControler = new ProductionTreeController();
        this.controller = new DiagramGenerationController();
    }

    /**
     * Retrieves the DiagramGenerationController instance.
     *
     * @return The diagram generation controller.
     */
    private DiagramGenerationController getDiagramController() {
        return controller;
    }

    /**
     * Main entry point of the `UpdateQuantityUI`. Handles user interaction to update material quantities,
     * validates input, and generates diagrams if requested.
     */
    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "         UPDATE MATERIAL QUANTITY                " + ANSI_RESET + "\n");

        System.out.printf("%s!IMPORTANT!%s %sQuantities for raw materials can't be change.%s%n", ANSI_BRIGHT_YELLOW, ANSI_RESET, ANSI_BRIGHT_BLACK, ANSI_RESET);
        int id = (int) getInputedID("• Input ID: ");
        float quantity = getInputedID("• Input New Quantity: ");
        ID newId = new ID(id, TypeID.ITEM);

        try {
            generateDiagram(pdtUI.auxilary(newId, quantity));
        } catch (Exception e) {
            System.out.println(ANSI_BRIGHT_RED + e.getMessage() + ANSI_RESET);
        }
    }

    /**
     * Handles the generation of the production tree diagram in UML format.
     *
     * @param productionTree The production tree to generate the diagram for.
     */
    private void generateDiagram(ProductionTree productionTree) {
        System.out.print("\nDo you wish to generate a diagram? (y/n): ");
        String answer = yesNoConfirmation();

        if (answer.equalsIgnoreCase("y")) {
            try {
                System.out.print("Enter a name for the file: ");
                String fileName = in.nextLine();
                System.out.println(ANSI_BRIGHT_GREEN + "\n\nGENERATING BOO DIAGRAM..." + ANSI_RESET);
                getDiagramController().writeBooToUmlFile(productionTree,fileName);

                System.out.println("\n" + ANSI_BRIGHT_GREEN + "Graph successfully generated!" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_YELLOW + "!IMPORTANT! Check the files/output for the file" + ANSI_RESET);

            } catch (Exception e) {
                System.out.println("\n" + ANSI_BRIGHT_RED + e.getMessage() + ANSI_RESET);
            }
        }
    }

    /**
     * Prompts the user for a numeric input and validates the input as a positive float.
     *
     * @param message The prompt message to display to the user.
     * @return A valid float value inputted by the user.
     */
    private float getInputedID(String message) {
        float input;
        System.out.print(message);
        while (true) {
            try {
                input = Float.parseFloat(in.nextLine());
                if (input <= 0) {
                    throw new NumberFormatException();
                }
                return input;
            } catch (NumberFormatException e) {
                System.out.println(ANSI_BRIGHT_RED + "INVALID INPUT" + ANSI_RESET);
                System.out.print(message);
            }
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
