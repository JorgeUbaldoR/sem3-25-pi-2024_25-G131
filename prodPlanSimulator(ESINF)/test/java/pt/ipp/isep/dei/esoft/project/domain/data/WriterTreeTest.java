package pt.ipp.isep.dei.esoft.project.domain.data;

import org.junit.jupiter.api.*;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link WriterTree} class, which writes UML representations of production trees.
 * This class tests the functionality of writing BOO (Bill of Operations) and BOM (Bill of Materials) files.
 */
class WriterTreeTest {

    private static ProductionTree mockTree;

    /**
     * Set up the initial conditions for the test cases.
     * <p>
     * Initializes a mock production tree and writes a sample CSV input to a file. This method runs once
     * before all test cases to ensure a consistent setup.
     * </p>
     *
     * @throws IOException if there is an error in writing the CSV file
     */
    @BeforeAll
    static void setup() throws IOException {
        mockTree = new ProductionTree();
        String filePath = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/test.csv";
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
        boolean result = mockTree.getInformations(filePath);
    }

    /**
     * Test the {@link WriterTree#writeBOOToUmlFile()} method to ensure it writes the BOO UML file correctly.
     * <p>
     * Verifies that the BOO UML file is created and contains the necessary elements, such as operation and item nodes.
     * </p>
     *
     * @throws IOException if there is an error in reading or writing the UML file
     */
    @Test
    void testWriteBOOToUmlFile() throws IOException {
        // Initialize WriterTree
        WriterTree writer = new WriterTree(mockTree);

        // Write BOO to UML file
        WriterTree.writeBOOToUmlFile();

        // Check File Content
        File file = new File("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/output/ProductionTree_BOO.puml");
        assertTrue(file.exists(), "BOO UML file should be created.");

        String content = Files.readString(file.toPath());
        assertTrue(content.contains("@startuml"), "File should contain PlantUML start marker.");
        assertTrue(content.contains("varnish bench"), "Operation 1 should be included in the BOO file.");
        assertTrue(content.contains("\"fix nut M16 21\" [shape=rectangle]"), "Item A should be included in the BOO file.");
    }

    /**
     * Test the {@link WriterTree#writeBOMToUmlFile()} method to ensure it writes the BOM UML file correctly.
     * <p>
     * Verifies that the BOM UML file is created and contains the necessary elements, such as item relationships
     * and operation references.
     * </p>
     *
     * @throws IOException if there is an error in reading or writing the UML file
     */
    @Test
    void testWriteBOMToUmlFile() throws IOException {
        // Initialize WriterTree
        WriterTree writer = new WriterTree(mockTree);

        // Write BOM to UML file
        WriterTree.writeBOMToUmlFile();

        // Check File Content
        File file = new File("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/output/ProductionTree_BOM.puml");
        assertTrue(file.exists(), "BOM UML file should be created.");

        String content = Files.readString(file.toPath());
        assertTrue(content.contains("@startuml"), "File should contain PlantUML start marker.");
        assertTrue(content.contains("\"finished bench\""), "Item B should be included in the BOM file.");
        assertTrue(content.contains("\"raw bench seat\" -- \"wood 3cm\""), "Operation 2 should be referenced.");
    }
}
