package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import pt.ipp.isep.dei.esoft.project.domain.ID;

import java.util.List;
import java.util.Map;

public class Node{

    ID operationID;
    ID itemID;
    float item_qtd;
    Map<ID, Float> operationMap;
    Map<ID, Float> materialMap;


    public Node(ID operationID, ID itemID,float item_qtd,Map<ID, Float> operationMap ,Map<ID, Float> materialMap) {
        this.operationID = operationID;
        this.materialMap = materialMap;
        this.operationMap = operationMap;
        this.item_qtd = item_qtd;
        this.itemID = itemID;
    }

    public ID getOperationID() {
        return operationID;
    }
    public ID getItemID() {
        return itemID;
    }
    public float getItem_qtd() {
        return item_qtd;
    }
    public Map<ID, Float> getOperationMap() {
        return operationMap;
    }
    public Map<ID, Float> getMaterialMap() {
        return materialMap;
    }



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

}
