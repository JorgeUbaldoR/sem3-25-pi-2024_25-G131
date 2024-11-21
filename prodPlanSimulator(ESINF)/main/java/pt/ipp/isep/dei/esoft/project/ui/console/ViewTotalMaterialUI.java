package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;
import pt.ipp.isep.dei.esoft.project.domain.ID;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class ViewTotalMaterialUI implements Runnable {

    private final ProductionTreeController controller;

    public ViewTotalMaterialUI() {
        controller = new ProductionTreeController();
    }


    @Override
    public void run() {

        printTotalMaterial();

    }

    private Map<ID, Float> getTotalMaterial() {
        Map<ID, Float> totalMaterial;

        totalMaterial = controller.getProductionTree().getTotalRequiredMaterials();
        return totalMaterial;
    }

    private void printTotalMaterial() {
        List<Map.Entry<ID, Float>> totalMaterial = ascendingOrderQuantity();

        System.out.println();
        System.out.printf("%s---------------------------------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s%-14s %-21s %-7s %s%n", ANSI_BRIGHT_WHITE, "ID", "Name", "Total Quantity",ANSI_RESET);
        System.out.printf("%s---------------------------------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);

        for (Map.Entry<ID, Float> entry : totalMaterial) {
            ID itemID = entry.getKey();
            String itemName = controller.getItemNameByID(itemID);

            if (itemID.getSerial() == 1015){
                System.out.printf("%-13s %-22s %-7s m² %n", itemID, itemName, entry.getValue());
            } else if (itemID.getSerial() == 1016) {
                System.out.printf("%-13s %-22s %-7s m %n", itemID, itemName, entry.getValue());
            } else if (itemID.getSerial() == 1014) {
                System.out.printf("%-13s %-22s %-7s L %n", itemID, itemName, entry.getValue());
            } else {
                System.out.printf("%-13s %-22s %-7s unit(s) %n", itemID, itemName, entry.getValue());
            }
        }
    }

    List<Map.Entry<ID, Float>> ascendingOrderQuantity() {
        List<Map.Entry<ID, Float>> list = new ArrayList<>(getTotalMaterial().entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return list;
    }




}
