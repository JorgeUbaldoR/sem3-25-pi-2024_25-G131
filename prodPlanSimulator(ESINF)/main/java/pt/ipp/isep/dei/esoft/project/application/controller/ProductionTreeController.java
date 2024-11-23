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
     * Constructor that initializes the production tree and the repositories.
     *
     * Time Complexity: O(1) - Constructor initializes objects, which takes constant time.
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
     *
     * Time Complexity: O(1) - Repositories are accessed and initialized once in constant time.
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
     *
     * Time Complexity: O(1) - Repositories are accessed and initialized once in constant time.
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
     * @param name The name to set.
     *
     * Time Complexity: O(1) - Setting a field value is a constant-time operation.
     */
    public void setName(String name) {
        productionTree.setPdtTreeName(name);
    }

    /**
     * Returns the current production tree.
     *
     * @return The production tree.
     *
     * Time Complexity: O(1) - Returning an object reference takes constant time.
     */
    public ProductionTree getProductionTree() {
        return productionTree;
    }

    /**
     * Loads production tree information from a file path.
     *
     * @param path The file path.
     * @return {@code true} if loading was successful, {@code false} otherwise.
     *
     * Time Complexity: O(n) - Depends on the size of the file and the number of items or operations in it.
     */
    public boolean getInformations(String path) {
        productionTree = new ProductionTree();
        return productionTree.getInformations(path);
    }

    /**
     * Retrieves a list of items or operations to display based on a flag.
     *
     * @param flag {@code 1} for items, any other value for operations.
     * @return A map of IDs to names (items or operations).
     *
     * Time Complexity: O(n) - Iterates over all items or operations; `n` is the number of entries.
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
     * @param selectedOperationID The ID of the operation.
     * @return {@code true} if it is a raw material, {@code false} otherwise.
     *
     * Time Complexity: O(1) - Lookup in a `Map` is constant time on average.
     */
    public boolean isRawMaterial(ID selectedOperationID) {
        return getProductionTree().getRawMaterials().containsKey(selectedOperationID);
    }

    /**
     * Retrieves the item node associated with an operation.
     *
     * @param selectedOperationID The operation ID.
     * @param rawMaterial If {@code true}, fetches the raw material node; otherwise, the material node.
     * @return The corresponding item node.
     *
     * Time Complexity: O(1) - Lookup in a `Map` is constant time on average.
     */
    public Node getItemNode(ID selectedOperationID, boolean rawMaterial) {
        if (rawMaterial) {
            return getProductionTree().getRawMaterials().get(selectedOperationID);
        }
        return getProductionTree().getMaterials().get(selectedOperationID);
    }

    /**
     * Retrieves the operation node associated with an operation ID.
     *
     * @param selectedOperationID The operation ID.
     * @return The corresponding operation node.
     *
     * Time Complexity: O(1) - Lookup in a `Map` is constant time on average.
     */
    public Node getOperationNode(ID selectedOperationID) {
        return getProductionTree().getOperationNodeID().get(selectedOperationID);
    }

    /**
     * Finds the parent operation name of a given node.
     *
     * @param node The node to find the parent for.
     * @return The parent operation name, or {@code null} if none exists.
     *
     * Time Complexity: O(n) - Iterates through nodes of the parent level, where `n` is the number of nodes at that level.
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
     * Retrieves the name of an operation by ID.
     *
     * @param operationID The ID of the operation.
     * @return The name of the operation.
     *
     * Time Complexity: O(1) - Lookup in a `Map` is constant time on average.
     */
    public String findNameOperation(ID operationID) {
        return getOperationRepository().getIdToOperation().get(operationID).getOperationName();
    }

    /**
     * Finds the parent item and quantity of a node in the tree.
     *
     * @param node The node to search.
     * @param rawMaterial {@code true} for raw materials, {@code false} for materials.
     * @param selectedOperationID The ID of the operation.
     * @return An array with the parent item name and quantity.
     *
     * Time Complexity: O(n) - Depends on the number of nodes at the parent level.
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
     * Retrieves the name of an item by ID.
     *
     * @param itemID The ID of the item.
     * @return The name of the item.
     *
     * Time Complexity: O(1) - Lookup in a `Map` is constant time on average.
     */
    public String findNameItem(ID itemID) {
        return getItemRepository().getMapItemList().get(itemID).getName();
    }

    /**
     * Returns the operation node corresponding to the given operation ID.
     *
     * @param operationID The ID of the operation.
     * @return The operation node corresponding to the ID, or {@code null} if no such node exists.
     *
     * Time Complexity: O(1) - The operation involves a lookup in a `Map`, which has average constant-time complexity.
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
