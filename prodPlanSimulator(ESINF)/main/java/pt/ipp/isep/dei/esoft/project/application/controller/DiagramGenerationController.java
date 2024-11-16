package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.domain.sprint2.WriterTree;

public class DiagramGenerationController {
    private WriterTree writerTree;
    private ProductionTree productionTree;

    public DiagramGenerationController() {
    }

    public boolean getInformations(String path){
        productionTree = new ProductionTree();
        return productionTree.getInformations(path);
    }

    public void writeBooToUmlFile(){
        writerTree = new WriterTree(productionTree);
        WriterTree.writeBOOToUmlFile();
    }

    public void writeBOMToUmlFile(){
        writerTree = new WriterTree(productionTree);
        WriterTree.writeBOMToUmlFile();
    }
}
