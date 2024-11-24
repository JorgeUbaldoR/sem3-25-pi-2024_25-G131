package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;

import java.util.Map;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

/**
 * Class responsible for updating the quantity of materials in a production tree.
 * This includes updating both the quantities of raw materials and operations.
 */
public class UpdateMaterial {
    private Map<ID, Node> materials;
    private ProductionTree pdt;

    /**
     * Constructor to initialize the UpdateMaterial with a given production tree.
     * <p>
     * The production tree is used to access and modify the material quantities.
     * </p>
     *
     * @param pdt The production tree containing the materials.
     */
    public UpdateMaterial(ProductionTree pdt) {
        this.pdt = pdt;
        this.materials = pdt.getMaterials();
    }

    /**
     * Updates the quantity of a specific material in the production tree.
     * <p>
     * The method retrieves the material by its ID, calculates the new quantity based on the provided
     * new quantity relative to the current quantity, and updates both the material and its associated raw materials.
     * </p>
     *
     * @param materialID The ID of the material to be updated.
     * @param newQuantity The new quantity to be set for the material.
     * @return The updated production tree with the new material quantities.
     * @throws IllegalArgumentException If the material is not found or is a raw material (not updatable).
     */
    public ProductionTree updateMaterial(ID materialID, float newQuantity) {
        Node materialNode = materials.get(materialID);

        if (materialNode == null) {
            throw new IllegalArgumentException("Material not found / is raw material: " + materialID);
        }

        float currentQuantity = materialNode.getItem_qtd();
        float quantity = newQuantity / currentQuantity;
        System.out.println(ANSI_BRIGHT_GREEN + "\nCHANGES MADE..." + ANSI_RESET);
        updateQTD(materialNode, quantity);

        return pdt;
    }

    /**
     * Recursively updates the quantity of an item and its dependent materials and operations.
     * <p>
     * The method first updates the current item's quantity, and then propagates the update to its raw materials
     * and operations dependent on this item.
     * </p>
     *
     * @param node The node representing the item whose quantity needs to be updated.
     * @param quantity The factor by which the item's quantity is to be updated.
     */
    private void updateQTD(Node node, float quantity) {
        node.setItem_qtd(quantity);

        System.out.println("• " + ANSI_BRIGHT_BLACK + "Update Item:" + ANSI_RESET + " " + node.getItemID() + " -> " + node.getItem_qtd());

        if (!node.getMaterialMap().isEmpty()) updateRawMaterialsQuantity(node, quantity);
        for (ID current_id : node.getOperationMap().keySet()) {
            Node childNode = pdt.findNodeByOperation(current_id);

            updateQTD(childNode, quantity);
        }
    }

    /**
     * Updates the quantities of raw materials associated with a given item.
     * <p>
     * The method multiplies the raw material's current quantity by the specified update factor.
     * </p>
     *
     * @param node The node representing the item whose raw materials are being updated.
     * @param quantity The factor by which the raw materials' quantities are to be updated.
     */
    private void updateRawMaterialsQuantity(Node node, float quantity) {
        Map<ID, Float> rawMaterials = node.getMaterialMap();
        for (Map.Entry<ID, Float> raw : rawMaterials.entrySet()) {
            raw.setValue(raw.getValue() * quantity);
            System.out.println("• " + ANSI_BRIGHT_BLACK + "Update Raw Materials:" + ANSI_RESET + " " + raw.getKey() + " -> " + raw.getValue());
        }
    }
}
