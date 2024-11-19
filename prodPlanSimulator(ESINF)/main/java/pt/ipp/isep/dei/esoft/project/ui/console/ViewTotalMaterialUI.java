package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;
import pt.ipp.isep.dei.esoft.project.domain.ID;

import javax.swing.text.View;
import java.util.HashMap;
import java.util.Map;

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
        Map<ID, Float> totalMaterial = getTotalMaterial();

        for (Map.Entry<ID, Float> entry : totalMaterial.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("CHEGOU AQUI");
    }




}
