package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.domain.UpdateMaterial;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.Scanner;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_RESET;

public class UpdateQuantityUI implements Runnable {

    private final Scanner in = new Scanner(System.in);
    private ProductionTreeUI pdtUI;
    private ProductionTreeController pdtControler;

    public UpdateQuantityUI() {
        this.pdtUI = new ProductionTreeUI();
        this.pdtControler = new ProductionTreeController();
    }

    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "            Update material quantity                 " + ANSI_RESET + "\n");

        System.out.print("Put ID: ");
        int id = in.nextInt();
        System.out.print("Put quantity: ");
        float quantity = in.nextFloat();
        ID newId = new ID(id, TypeID.ITEM);

        try {
            pdtUI.auxilary(newId, quantity);
        } catch (Exception e) {
            System.out.println(ANSI_BRIGHT_RED + e.getMessage() + ANSI_RESET);
        }
    }


}
