package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UpdateMaterialTest {

    private ProductionTree productionTree;
    private UpdateMaterial updateMaterial;

    @BeforeEach
    void setUp() {
        productionTree = new ProductionTree();
        productionTree.getInformations("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/boo.csv"); // Replace with mock/test data
        updateMaterial = new UpdateMaterial(productionTree);
    }

    @Test
    void testUpdateMaterialValid() {
        ID materialID = productionTree.getMaterials().keySet().iterator().next();
        Node materialNode = productionTree.getMaterials().get(materialID);
        float currentQuantity = materialNode.getItem_qtd();
        float factor = 3.0f;
        float newQuantity = currentQuantity * factor;

        updateMaterial.updateMaterial(materialID, newQuantity);

        assertEquals(newQuantity, materialNode.getItem_qtd(), "Material quantity should be updated.");
    }

    @Test
    void testUpdateMaterialUpdatesDependentMaterials() {
        ID materialID = productionTree.getMaterials().keySet().iterator().next();
        Node materialNode = productionTree.getMaterials().get(materialID);

        Map<ID, Float> initialRawMaterials = materialNode.getMaterialMap();
        float factor = 1.5f;
        float newQuantity = materialNode.getItem_qtd() * factor;

        updateMaterial.updateMaterial(materialID, newQuantity);

        for (Map.Entry<ID, Float> entry : initialRawMaterials.entrySet()) {
            ID rawMaterialID = entry.getKey();
            float expectedQuantity = entry.getValue();
            float actualQuantity = materialNode.getMaterialMap().get(rawMaterialID);

            assertEquals(expectedQuantity, actualQuantity,
                    "Raw material quantities should be updated proportionally.");
        }
    }


    @Test
    void testUpdateMaterialThrowsExceptionForInvalidMaterial() {
        ID invalidMaterialID = new ID(9999, TypeID.ITEM);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                        updateMaterial.updateMaterial(invalidMaterialID, 100),
                "Expected exception for non-existent material ID."
        );

        assertTrue(exception.getMessage().contains("Material not found"),
                "Exception message should indicate invalid material.");
    }

    @Test
    void testUpdateMaterialPropagatesToDependentOperations() {
        ID materialID = productionTree.getMaterials().keySet().iterator().next();
        Node materialNode = productionTree.getMaterials().get(materialID);

        Map<ID, Float> initialOperations = materialNode.getOperationMap();
        float factor = 2.0f;
        float newQuantity = materialNode.getItem_qtd() * factor;

        updateMaterial.updateMaterial(materialID, newQuantity);

        for (Map.Entry<ID, Float> entry : initialOperations.entrySet()) {
            Node operationNode = productionTree.findNodeByOperation(entry.getKey());
            assertEquals(factor, operationNode.getItem_qtd() / materialNode.getItem_qtd(),
                    "Dependent operation quantities should be updated proportionally.");
        }
    }
}
