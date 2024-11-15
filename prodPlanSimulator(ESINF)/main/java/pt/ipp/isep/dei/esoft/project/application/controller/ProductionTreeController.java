package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.ProductionTree;

public class ProductionTreeController {
    private ProductionTree productionTree;

    public ProductionTreeController() {
        productionTree = new ProductionTree();
    }

    public void setName(String name){
        productionTree.setPdtTreeName(name);
    }

    public ProductionTree getProductionTree() {
        return productionTree;
    }

    public boolean getInformations(String path){
        return productionTree.getInformations(path);
    }
}
