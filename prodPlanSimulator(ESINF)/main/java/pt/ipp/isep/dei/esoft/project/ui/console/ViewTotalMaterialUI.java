package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;
import pt.ipp.isep.dei.esoft.project.domain.ID;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

/**
 * User interface class to view total material requirements for production.
 */
public class ViewTotalMaterialUI implements Runnable {

    private final ProductionTreeController controller;

    public ViewTotalMaterialUI() {
        controller = new ProductionTreeController();
    }

    /**
     * Runs the interface logic.
     */
    @Override
    public void run() {
        printTotalMaterial();
    }

    /**
     * Retrieves the total required material quantities from the production tree.
     *
     * @return a map where the key is the material ID and the value is the total quantity required
     *
     * Time Complexity: Dependent on the complexity of {@code controller.getProductionTree().getTotalRequiredMaterials()},
     * which is O(n), where n is the number of raw materials.
     */
    private Map<ID, Float> getTotalMaterial() {
        return controller.getProductionTree().getTotalRequiredMaterials();
    }

    /**
     * Prints the total material quantities in a formatted table.
     * The format depends on the material type (e.g., m², m, L, unit(s)).
     *
     * Time Complexity: O(n log n), where n is the number of entries in the total material map.
     * The sorting operation dominates the complexity.
     */
    private void printTotalMaterial() {
        List<Map.Entry<ID, Float>> totalMaterial = ascendingOrderQuantity();

        System.out.println();
        System.out.printf("%s---------------------------------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);
        System.out.printf("%s%-14s %-21s %-7s %s%n", ANSI_BRIGHT_WHITE, "ID", "Name", "Total Quantity", ANSI_RESET);
        System.out.printf("%s---------------------------------------------------------%s%n", ANSI_BRIGHT_BLACK, ANSI_RESET);

        for (Map.Entry<ID, Float> entry : totalMaterial) {
            ID itemID = entry.getKey();
            String itemName = controller.getItemNameByID(itemID);

            if (itemID.getSerial() == 1015) {
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

    /**
     * Orders the total material quantities in descending order.
     *
     * @return a list of map entries sorted by quantity in descending order
     *
     * Time Complexity: O(n log n), where n is the number of entries in the total material map.
     * The sorting operation dominates the complexity.
     */
    List<Map.Entry<ID, Float>> ascendingOrderQuantity() {
        List<Map.Entry<ID, Float>> list = new ArrayList<>(getTotalMaterial().entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return list;
    }
}
