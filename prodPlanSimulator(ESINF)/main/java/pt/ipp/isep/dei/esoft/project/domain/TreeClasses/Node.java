package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Map;

/**
 * Represents a node in a tree structure that contains details about an operation,
 * item, and its dependencies in terms of other operations and materials.
 * Each node stores an operation ID, item ID, item quantity,
 * a map of dependent operations, and a map of required materials.
 */
public class Node {

    private final ID operationID; // Represents the operation's unique identifier.
    private final ID itemID;      // Represents the item's unique identifier.
    private int heigthInTree; // Represents the depth or height of this node in the tree.
    private float item_qtd;  // The quantity of the item produced by this operation.
    private Map<ID, Float> operationMap; // Map of dependent operations and their quantities.
    private Map<ID, Float> materialMap;  // Map of required materials and their quantities.
    private OperationRepository operationRepository;

    /**
     * Constructs a new Node with the given details.
     *
     * @param operationID   the unique ID of the operation.
     * @param itemID        the unique ID of the item produced by the operation.
     * @param item_qtd      the quantity of the item produced.
     * @param operationMap  a map of dependent operations and their respective quantities.
     * @param materialMap   a map of required materials and their respective quantities.
     *
     * Complexity: O(1) - Constructor performs assignments without any iteration.
     */
    public Node(ID operationID, ID itemID, float item_qtd, Map<ID, Float> operationMap, Map<ID, Float> materialMap) {
        this.operationID = operationID;
        this.materialMap = materialMap;
        this.operationMap = operationMap;
        this.item_qtd = item_qtd;
        this.itemID = itemID;
        this.heigthInTree = 0;
        getOperationRepository();
    }

    /**
     * Retrieves the OperationRepository instance.
     * If the repository is not already initialized, it fetches it from the Repositories singleton.
     *
     * @return An instance of {@link OperationRepository}.
     *
     * Complexity:
     * - Worst case: O(1) for fetching the repository from the singleton.
     */
    private OperationRepository getOperationRepository() {
        if (operationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            operationRepository = repositories.getOperationRepository();
        }
        return operationRepository;
    }


    /**
     * @return the unique ID of the operation.
     *
     * Complexity: O(1) - Simple field access.
     */
    public ID getOperationID() {
        return operationID;
    }

    /**
     * @return the unique ID of the item.
     *
     * Complexity: O(1) - Simple field access.
     */
    public ID getItemID() {
        return itemID;
    }

    /**
     * @return the quantity of the item produced.
     *
     * Complexity: O(1) - Simple field access.
     */
    public float getItem_qtd() {
        return item_qtd;
    }

    /**
     * @return the map of dependent operations and their quantities.
     *
     * Complexity: O(1) - Simple field access.
     */
    public Map<ID, Float> getOperationMap() {
        return operationMap;
    }

    /**
     * @return the map of required materials and their quantities.
     *
     * Complexity: O(1) - Simple field access.
     */
    public Map<ID, Float> getMaterialMap() {
        return materialMap;
    }

    public float getItemQtdByID (ID id) {
        return materialMap.get(id);
    }

    public void setItem_qtd(float item_qtd) {
        this.item_qtd = item_qtd;
    }

    /**
     * Sets the map of dependent operations and their quantities.
     *
     * @param operationMap the new map of operations.
     *
     * Complexity: O(1) - Simple field assignment.
     */
    public void setOperationMap(Map<ID, Float> operationMap) {
        this.operationMap = operationMap;
    }

    /**
     * Sets the map of required materials and their quantities.
     *
     * @param materialMap the new map of materials.
     *
     * Complexity: O(1) - Simple field assignment.
     */
    public void setMaterialMap(Map<ID, Float> materialMap) {
        this.materialMap = materialMap;
    }

    /**
     * Sets the height of the node in the tree.
     *
     * @param heigthInTree the new height value.
     *
     * Complexity: O(1) - Simple field assignment.
     */
    public void setHeigthInTree(int heigthInTree) {
        this.heigthInTree = heigthInTree;
    }

    /**
     * @return the height of the node in the tree.
     *
     * Complexity: O(1) - Simple field access.
     */
    public int getHeigthInTree() {
        return heigthInTree;
    }

    /**
     * Provides a string representation of the node, including all details.
     *
     * @return a string with operation ID, item ID, item quantity, operation map, and material map.
     *
     * Complexity: O(n + m) - Iterates over the operationMap (n) and materialMap (m).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNode Details:\n");
        sb.append("Operation ID: ").append(operationID != null ? operationID.toString() : "null").append("\n");
        sb.append("Item ID: ").append(itemID != null ? itemID.toString() : "null").append("\n");
        sb.append("Item Quantity: ").append(item_qtd).append("\n");

        sb.append("Operation Map:\n");
        if (operationMap != null && !operationMap.isEmpty()) {
            for (Map.Entry<ID, Float> entry : operationMap.entrySet()) {
                sb.append("  - ").append(entry.getKey().toString()).append(": ").append(entry.getValue()).append("\n");
            }
        } else {
            sb.append("  (empty)\n");
        }

        sb.append("Material Map:\n");
        if (materialMap != null && !materialMap.isEmpty()) {
            for (Map.Entry<ID, Float> entry : materialMap.entrySet()) {
                sb.append("  - ").append(entry.getKey().toString()).append(": ").append(entry.getValue()).append("\n");
            }
        } else {
            sb.append("  (empty)\n");
        }

        return sb.toString();
    }

    /**
     * Retrieves the name of the operation corresponding to the stored operation ID.
     * This method uses the {@link OperationRepository} to fetch the name.
     *
     * @return A {@link String} representing the name of the operation.
     */
    public String getOperationNameByID() {
        return getOperationRepository().getNameByID(this.operationID);
    }



}
