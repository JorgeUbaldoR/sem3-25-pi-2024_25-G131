package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ProductionTree.
 * Verifies the correct behavior of its methods and the integrity of data processing.
 */
public class ProductionTreeTest {

    /**
     * Tests the constructor of the ProductionTree class.
     * Ensures that all maps and lists are initialized as empty.
     */
    @Test
    void constructProductionTree() {
        System.out.println("Test Constructor");
        ProductionTree productionTree = new ProductionTree();
        List<Node> nodes = productionTree.getNodesOfTree();
        Map<ID, Node> rawMaterials = productionTree.getRawMaterials();

        // Verify that all collections are empty upon initialization
        assertTrue(rawMaterials.isEmpty(), "Raw materials map should be empty after initialization.");
        assertTrue(nodes.isEmpty(), "Nodes list should be empty after initialization.");
        assertTrue(productionTree.getMaterials().isEmpty(), "Materials map should be empty after initialization.");
        assertTrue(productionTree.getOperationNodeID().isEmpty(), "Operation map should be empty after initialization.");
    }

    /**
     * Tests the getInformations() method with a valid file path.
     * Verifies that data is loaded correctly and all collections are populated.
     */
    @Test
    void getInformation() {
        System.out.println("Test GetInformation");
        ProductionTree productionTree = new ProductionTree();

        // Attempt to load valid information
        boolean result = productionTree.getInformations("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/boo.csv");

        // Verify that the collections are populated
        assertFalse(productionTree.getRawMaterials().isEmpty(), "Raw materials map should not be empty.");
        assertFalse(productionTree.getHeightMap().isEmpty(), "Height map should not be empty.");
        assertFalse(productionTree.getNodesOfTree().isEmpty(), "Nodes list should not be empty.");
        assertFalse(productionTree.getMaterials().isEmpty(), "Materials map should not be empty.");
        assertTrue(result, "Information should be successfully loaded.");

        // Test with an invalid file path
        assertThrows(IllegalArgumentException.class,
                () -> productionTree.getInformations(""),
                "An exception should be thrown for an invalid file path.");
    }

    /**
     * Tests the getInformations() method with an invalid file path.
     * Ensures that an exception is thrown and the tree remains empty.
     */
    @Test
    void testGetInformationInvalidFile() {
        System.out.println("Test GetInformation with Invalid File");
        ProductionTree productionTree = new ProductionTree();

        // Verify exception for invalid file
        String invalidFilePath = "invalidFile.csv";
        assertThrows(IllegalArgumentException.class,
                () -> productionTree.getInformations(invalidFilePath),
                "An exception should be thrown for an invalid file.");

        // Verify that the tree remains empty
        assertTrue(productionTree.getNodesOfTree().isEmpty(), "Nodes list should be empty after failure.");
        assertTrue(productionTree.getRawMaterials().isEmpty(), "Raw materials map should be empty after failure.");
    }

    /**
     * Tests the getInformations() method with a valid file.
     * Verifies the correct loading of nodes, raw materials, and tree height levels.
     *
     * @throws IOException If an error occurs while creating the test file.
     */
    @Test
    void testGetInformationsValidFile() throws IOException {
        System.out.println("Test GetInformations with Valid File");
        String filePath = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/test.csv";

        // Create a test file with valid data
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("""
                op_id;item_id;item_qtd;(;op1;op_qtd1;op2;op_qtd2;opN;op_qtdN;);(;item_id1;item_qtd1;item_id1;item_qtd1;item_id1;item_qtd1;)
                20;1006;1;(;11;1;;;;;);(;1014;0,125;;;;;)
                11;1010;1;(;17;1;16;4;;;);(;;;;;;;)
                17;1004;2;(;15;1;;;;;);(;1007;4;1005;12;;;)
                16;1002;1;(;14;1;;;;;);(;1013;1;;;;;)
                14;1001;1;(;18;1;;;;;);(;;;;;;;)
                18;1008;1;(;12;1;;;;;);(;;;;;;;)
                12;1011;1;(;;;;;;;);(;1016;0,28;;;;;)
                15;1003;1;(;19;1;;;;;);(;;;;;;;)
                19;1009;1;(;13;1;;;;;);(;;;;;;;)
                13;1012;1;(;;;;;;;);(;1015;0,0576;;;;;)
                """);
        }

        // Load information from the test file
        ProductionTree productionTree = new ProductionTree();
        boolean result = productionTree.getInformations(filePath);

        // Verify successful data loading
        assertTrue(result, "Information should be successfully loaded.");
        assertFalse(productionTree.getNodesOfTree().isEmpty(), "Nodes list should not be empty.");
        assertFalse(productionTree.getRawMaterials().isEmpty(), "Raw materials map should not be empty.");
        assertEquals(10, productionTree.getNodesOfTree().size(), "There should be 10 nodes in the tree.");

        // Verify tree height map
        assertNotNull(productionTree.getHeightMap().get(0), "Height 0 should contain nodes.");
        assertNotNull(productionTree.getHeightMap().get(1), "Height 1 should contain nodes.");
    }

    /**
     * Tests the getTotalRequiredMaterials() method.
     * Validates that the required materials and their quantities are calculated correctly.
     *
     * @throws IOException If an error occurs while creating the test file.
     */
    @Test
    void testGetTotalRequiredMaterials() throws IOException {
        System.out.println("Test GetTotalRequiredMaterials");
        String filePath = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/testMaterials.csv";

        // Create a test file with material data
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("""
                op_id;item_id;item_qtd;(;op1;op_qtd1;op2;op_qtd2;opN;op_qtdN;);(;item_id1;item_qtd1;item_id1;item_qtd1;item_id1;item_qtd1;)
                20;1006;1;(;11;1;;;;;);(;1014;0,125;;;;;)
                11;1010;1;(;17;1;16;4;;;);(;;;;;;;)
                17;1004;2;(;15;1;;;;;);(;1007;4;1005;12;;;)
                16;1002;1;(;14;1;;;;;);(;1013;1;;;;;)
                14;1001;1;(;18;1;;;;;);(;;;;;;;)
                18;1008;1;(;12;1;;;;;);(;;;;;;;)
                12;1011;1;(;;;;;;;);(;1016;0,28;;;;;)
                15;1003;1;(;19;1;;;;;);(;;;;;;;)
                19;1009;1;(;13;1;;;;;);(;;;;;;;)
                13;1012;1;(;;;;;;;);(;1015;0,0576;;;;;)
                """);
        }

        // Load data and calculate required materials
        ProductionTree productionTree = new ProductionTree();
        productionTree.getInformations(filePath);
        Map<ID, Float> totalMaterials = productionTree.getTotalRequiredMaterials();

        // Verify materials and quantities
        assertEquals(6, totalMaterials.size(), "There should be 6 raw materials in total.");
        assertTrue(totalMaterials.containsKey(new ID(1014, TypeID.ITEM)), "Material 1014 should be present.");
        assertEquals(0.125f, totalMaterials.get(new ID(1014, TypeID.ITEM)), 0.01, "Quantity for material 1014 is incorrect.");
    }

    /**
     * Tests the behavior of an empty ProductionTree.
     * Verifies that all maps and lists are empty.
     */
    @Test
    void testEmptyTree() {
        System.out.println("Test Empty Tree");
        ProductionTree emptyTree = new ProductionTree();

        // Verify empty state
        assertTrue(emptyTree.getNodesOfTree().isEmpty(), "Nodes list should be empty in an empty tree.");
        assertTrue(emptyTree.getHeightMap().isEmpty(), "Height map should be empty in an empty tree.");
        assertEquals(0, emptyTree.getRawMaterials().size(), "Raw materials map should be empty in an empty tree.");
    }
}