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

/**
 * The {@code ProductionTreeController} class is responsible for managing and controlling the production tree
 * of a production planning system, handling operations and materials through interactions with the
 * operations and items repositories. It provides methods to access, manipulate, and display information
 * about the production tree.
 */
public class ProductionTreeController {

    private ProductionTree productionTree;  // Represents the production tree
    private ItemRepository itemRepository;  // Item (material) repository
    private OperationRepository operationRepository;  // Operation repository

    /**
     * Constructor that initializes the production tree and the item and operation repositories.
     */
    public ProductionTreeController() {
        productionTree = new ProductionTree();
        itemRepository = getItemRepository();
        operationRepository = getOperationRepository();
    }

    /**
     * Returns the operation repository.
     *
     * @return The operation repository.
     */
    private OperationRepository getOperationRepository() {
        if (operationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            operationRepository = repositories.getOperationRepository();
        }
        return operationRepository;
    }

    /**
     * Returns the item repository.
     *
     * @return The item repository.
     */
    private ItemRepository getItemRepository() {
        if (itemRepository == null) {
            Repositories repositories = Repositories.getInstance();
            itemRepository = repositories.getItemRepository();
        }
        return itemRepository;
    }

    /**
     * Sets the name of the production tree.
     *
     * @param name The name of the production tree.
     */
    public void setName(String name) {
        productionTree.setPdtTreeName(name);
    }

    /**
     * Returns the current production tree.
     *
     * @return The production tree.
     */
    public ProductionTree getProductionTree() {
        return productionTree;
    }

    /**
     * Loads information about the production tree from a file path.
     *
     * @param path The file path containing the information.
     * @return {@code true} if the information is successfully loaded, {@code false} otherwise.
     */
    public boolean getInformations(String path) {
        productionTree = new ProductionTree();
        return productionTree.getInformations(path);
    }

    /**
     * Retrieves a list of items or operations to be displayed, depending on the flag value.
     *
     * @param flag An integer value determining what will be returned:
     *             - {@code 1} for items.
     *             - Any other value for operations.
     * @return A map of IDs to item or operation names.
     */
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

    /**
     * Checks if an operation is a raw material.
     *
     * @param selectedOperationID The ID of the operation to check.
     * @return {@code true} if the operation is a raw material, {@code false} otherwise.
     */
    public boolean isRawMaterial(ID selectedOperationID) {
        return getProductionTree().getRawMaterials().containsKey(selectedOperationID);
    }

    /**
     * Returns the item node associated with an operation, depending on whether it is a raw material or not.
     *
     * @param selectedOperationID The ID of the operation.
     * @param rawMaterial If {@code true}, returns the raw material node; otherwise, returns the material node.
     * @return The item node corresponding to the operation.
     */
    public Node getItemNode(ID selectedOperationID, boolean rawMaterial) {
        if (rawMaterial) {
            return getProductionTree().getRawMaterials().get(selectedOperationID);
        }
        return getProductionTree().getMaterials().get(selectedOperationID);
    }

    /**
     * Returns the operation node corresponding to the given operation ID.
     *
     * @param selectedOperationID The ID of the operation.
     * @return The operation node corresponding to the ID.
     */
    public Node getOperationNode(ID selectedOperationID) {
        return getProductionTree().getOperationNodeID().get(selectedOperationID);
    }

    /**
     * Finds the parent operation name of a node in the production tree, if it exists.
     *
     * @param node The node for which the parent operation is to be found.
     * @return The name of the parent operation or {@code null} if there is none.
     */
    public String findParentOperation(Node node) {
        int height = node.getHeigthInTree();
        if (height != 0) {
            List<Node> nodesByHeight = getProductionTree().getHeightMap().get(node.getHeigthInTree() - 1);
            for (Node nodeInList : nodesByHeight) {
                if (nodeInList.getOperationMap().containsKey(node.getOperationID())) {
                    return findNameOperation(nodeInList.getOperationID());
                }
            }
        }
        return null;
    }

    /**
     * Returns the name of an operation based on the operation ID.
     *
     * @param operationID The ID of the operation.
     * @return The name of the operation corresponding to the ID.
     */
    public String findNameOperation(ID operationID) {
        return getOperationRepository().getIdToOperation().get(operationID).getOperationName();
    }

    /**
     * Finds the parent item and corresponding quantity of a node in the production tree,
     * depending on whether it is a raw material or not.
     *
     * @param node The node for which the parent item is to be found.
     * @param rawMaterial If {@code true}, searches in the raw materials repository; otherwise, in materials.
     * @param selectedOperationID The ID of the selected operation.
     * @return An array containing the parent item name and the corresponding quantity.
     */
    public String[] findParentItem(Node node, boolean rawMaterial, ID selectedOperationID) {
        String[] parentAndQtd = new String[2];
        if (rawMaterial) {
            parentAndQtd[0] = findNameItem(node.getItemID());
            parentAndQtd[1] = String.valueOf(node.getMaterialMap().get(selectedOperationID));
            return parentAndQtd;
        }
        int height = node.getHeigthInTree();
        if (height != 0) {
            List<Node> nodesByHeight = getProductionTree().getHeightMap().get(node.getHeigthInTree() - 1);
            for (Node nodeInList : nodesByHeight) {
                if (nodeInList.getOperationMap().containsKey(node.getOperationID())) {
                    parentAndQtd[0] = findNameItem(nodeInList.getItemID());
                    parentAndQtd[1] = String.valueOf(nodeInList.getItem_qtd());
                    return parentAndQtd;
                }
            }
        }
        parentAndQtd[0] = null;
        parentAndQtd[1] = String.valueOf(node.getItem_qtd());
        return parentAndQtd;
    }

    /**
     * Returns the name of an item based on its ID.
     *
     * @param itemID The ID of the item.
     * @return The name of the item corresponding to the ID.
     */
    public String findNameItem(ID itemID) {
        return getItemRepository().getMapItemList().get(itemID).getName();
    }

    /**
     * Returns the operation node corresponding to the given operation ID.
     *
     * @param operationID The ID of the operation.
     * @return The operation node corresponding to the ID.
     */
    public Node getNodeByOperationID(ID operationID) {
        return getProductionTree().getOperationNodeID().get(operationID);
    }

    /**
     * Delegates the retrieval of an item name by its ID to the repository layer.
     *
     * @param itemID The ID of the item.
     * @return The name of the item.
     *
     * Time Complexity: Dependent on the implementation of {@code getItemRepository().getItemNameByID(itemID)}.
     * If it involves a map lookup, it is O(1).
     */
    public String getItemNameByID(ID itemID) {
        return getItemRepository().getItemNameByID(itemID);
    }
}
