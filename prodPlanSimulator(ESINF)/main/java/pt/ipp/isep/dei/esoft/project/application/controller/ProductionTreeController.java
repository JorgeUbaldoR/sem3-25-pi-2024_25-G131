package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void setName(String name) {
        productionTree.setPdtTreeName(name);
    }

    public ProductionTree getProductionTree() {
        return productionTree;
    }

    public boolean getInformations(String path) {
        productionTree = new ProductionTree();
        return productionTree.getInformations(path);
    }

    public Map<ID, String> getListToShow(int flag) {
        Map<ID, String> map = new HashMap<>();
        if (flag == 1) {
            for (Item material : getItemRepository().getItemList()) {
                map.put(material.getItemID(), material.getName());
            }
            return map;
        }

        for (Operation operation : getOperationRepository().getOperations()) {
            map.put(operation.getOperationId(), operation.getOperationName());
        }
        return map;
    }

    public boolean isRawMaterial(ID selectedOperationID) {
        return getProductionTree().getRawMaterials().containsKey(selectedOperationID);
    }
    
    public Node getItemNode(ID selectedOperationID, boolean rawMaterial) {
        if(rawMaterial) {
            return getProductionTree().getRawMaterials().get(selectedOperationID);
        }
        return getProductionTree().getMaterials().get(selectedOperationID);
    }

    public Node getOperationNode(ID selectedOperationID) {
        return getProductionTree().getOperationNodeID().get(selectedOperationID);
    }

    public String findParentOperation(Node node) {
        int heigth = node.getHeigthInTree();
        if(heigth != 0) {
            List<Node> nodesByHeigth = getProductionTree().getHeightMap().get(node.getHeigthInTree() - 1);
            for (Node nodeInList : nodesByHeigth) {
                if (nodeInList.getOperationMap().containsKey(node.getOperationID())) {
                    return findNameOperation(nodeInList.getOperationID());
                }
            }
        }
        return null;
    }

    public String findNameOperation(ID operationID) {
        return getOperationRepository().getIdToOperation().get(operationID).getOperationName();
    }

    public String[] findParentItem(Node node, boolean rawMaterial, ID selectedOperationID) {
        String[] parentAndQtd = new String[2];
        if(rawMaterial) {
            parentAndQtd[0] = findNameItem(node.getItemID());
            parentAndQtd[1] = String.valueOf(node.getMaterialMap().get(selectedOperationID));
            //return findNameItem(node.getItemID());
            return parentAndQtd;
        }
        int heigth = node.getHeigthInTree();
        if(heigth != 0) {
            List<Node> nodesByHeigth = getProductionTree().getHeightMap().get(node.getHeigthInTree() - 1);
            for (Node nodeInList : nodesByHeigth) {
                if (nodeInList.getOperationMap().containsKey(node.getOperationID())) {
                    parentAndQtd[0] = findNameItem(nodeInList.getItemID());
                    parentAndQtd[1] = String.valueOf(nodeInList.getItem_qtd());
                    return parentAndQtd;
                    //return findNameItem(nodeInList.getItemID());
                }
            }
        }
        parentAndQtd[0] = null;
        parentAndQtd[1] = String.valueOf(node.getItem_qtd());
        return parentAndQtd;
    }

    public String findNameItem(ID itemID) {
        return getItemRepository().getMapItemList().get(itemID).getName();
    }


}
