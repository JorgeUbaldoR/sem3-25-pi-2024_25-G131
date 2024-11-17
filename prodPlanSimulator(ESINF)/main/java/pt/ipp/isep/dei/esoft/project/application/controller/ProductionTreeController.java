package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class ProductionTreeController {
    private ProductionTree productionTree;
    private ItemRepository itemRepository;
    private OperationRepository operationRepository;

    public ProductionTreeController() {
        productionTree = new ProductionTree();
        itemRepository = getItemRepository();
        operationRepository = getOperationRepository();
    }

    private OperationRepository getOperationRepository() {
        if (operationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            operationRepository = repositories.getOperationRepository();
        }
        return operationRepository;
    }

    private ItemRepository getItemRepository() {
        if (itemRepository == null) {
            Repositories repositories = Repositories.getInstance();
            itemRepository = repositories.getItemRepository();
        }
        return itemRepository;
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

    public List<String> getListToShow(int flag){
        List<String> list = new ArrayList<>();

        if(flag == 1){
            for(Item material : getItemRepository().getItemList()){
                list.add(material.getName());
            }
            return list;
        }

        for (Operation operation : getOperationRepository().getOperations()){
            list.add(operation.getOperationName());
        }
        return list;
    }
}
