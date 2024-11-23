package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;

import java.util.List;
import java.util.Map;

public class UpdateMaterial {
    private Map<ID, Node> materials;
    private ProductionTree pdt;

    public UpdateMaterial(ProductionTree pdt) {
        this.pdt = pdt;
        this.materials = pdt.getMaterials();
    }

    public ProductionTree updateMaterial(ID materialID, float newQuantity) {
        Node materialNode = materials.get(materialID);

        if (materialNode == null) {
            throw new IllegalArgumentException("Material not found / is raw material: " + materialID);
        }

        float currentQuantity = materialNode.getItem_qtd();
        float quantity = newQuantity / currentQuantity;
        updateQTD(materialNode, quantity);

        return pdt;
    }

    private void updateQTD(Node node, float quantity) {
        node.setItem_qtd(quantity);
        System.out.println("UpdateQTD" + node.getItemID() + " " + node.getItem_qtd());

        if (!node.getMaterialMap().isEmpty()) updateRawMaterialsQuantity(node, quantity);
        System.out.println("--------------------------------------------------------------------");
        for (ID current_id : node.getOperationMap().keySet()) {
            Node childNode = pdt.findNodeByOperation(current_id);

            updateQTD(childNode, quantity);
        }
    }

    private void updateRawMaterialsQuantity(Node node, float quantity) {
        Map<ID, Float> rawMaterials = node.getMaterialMap();
        for (Map.Entry<ID, Float> raw : rawMaterials.entrySet()) {
            raw.setValue(raw.getValue() * quantity);
            System.out.println("updateRawMaterialsQuantity" + raw.getKey() + " " + raw.getValue());
        }
    }
}

