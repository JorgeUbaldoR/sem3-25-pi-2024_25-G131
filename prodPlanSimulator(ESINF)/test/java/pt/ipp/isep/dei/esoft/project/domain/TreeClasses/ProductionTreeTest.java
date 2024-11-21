package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductionTreeTest {

    private ProductionTree productionTree;

    @BeforeEach
    void setUp() {
        productionTree = new ProductionTree();
    }

    @Test
    void constructProductionTree() {
        System.out.println("Test Constructor");
        ProductionTree productionTree = new ProductionTree();
        List<Node> list = productionTree.getNodesOfTree();
        Map<ID, Node> map = productionTree.getRawMaterials();
        assertTrue(map.isEmpty());
        assertTrue(list.isEmpty());

        assertTrue(productionTree.getNodesOfTree().isEmpty(), "Nodes list should be empty after initialization.");
        assertTrue(productionTree.getRawMaterials().isEmpty(), "Raw materials map should be empty after initialization.");
        assertTrue(productionTree.getMaterials().isEmpty(), "Materials map should be empty after initialization.");
        assertTrue(productionTree.getOperationNodeID().isEmpty(), "Operation map should be empty after initialization.");
    }


    @Test
    void getInformation() {
        System.out.println("Test GetInformation");
        ProductionTree productionTree = new ProductionTree();
        boolean result = productionTree.getInformations("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/boo.csv");

        List<Node> list = productionTree.getNodesOfTree();
        Map<ID, Node> map = productionTree.getRawMaterials();
        Map<ID, Node> map2 = productionTree.getMaterials();
        Map<ID, Node> map1 = productionTree.getRawMaterials();
        Map<Integer, List<Node>> mapHeight = productionTree.getHeightMap();

        assertFalse(map.isEmpty());
        assertFalse(mapHeight.isEmpty());
        assertFalse(list.isEmpty());
        assertFalse(map2.isEmpty());
        assertFalse(map1.isEmpty());
        assertTrue(result);


        assertThrows(IllegalArgumentException.class ,
                () ->  productionTree.getInformations(""));

        try{
            result = productionTree.getInformations("");
            assertFalse(result);
        }catch (IllegalArgumentException e){
        }
    }

    @Test
    void testGetInformationInvalidFile() {
        System.out.println("Test GetInformation with Invalid File");
        String invalidFilePath = "invalidFile.csv";

        assertThrows(IllegalArgumentException.class,
                () -> productionTree.getInformations(invalidFilePath),
                "Deveria lançar uma exceção para um arquivo inválido.");

        // Verifica o estado da árvore após erro
        assertTrue(productionTree.getNodesOfTree().isEmpty(), "A lista de nós deveria estar vazia após falha no processamento.");
        assertTrue(productionTree.getRawMaterials().isEmpty(), "O mapa de matérias-primas deveria estar vazio após falha no processamento.");
    }


    @Test
    void testGetInformationsValidFile() throws IOException {
        System.out.println("Test GetInformations with valid file");
        // Criar um arquivo temporário para simular a entrada
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


        boolean result = productionTree.getInformations(filePath);
        assertTrue(result, "Informações deveriam ser lidas com sucesso.");

        // Validar se os nós foram carregados corretamente
        assertFalse(productionTree.getNodesOfTree().isEmpty(), "Lista de nós não deveria estar vazia.");
        assertFalse(productionTree.getRawMaterials().isEmpty(), "Mapa de matérias-primas não deveria estar vazio.");
        assertFalse(productionTree.getMaterials().isEmpty(), "Mapa de materiais não deveria estar vazio.");
        assertEquals(10, productionTree.getNodesOfTree().size(), "Deveria haver 10 nós na árvore.");

        // Validar alturas da árvore
        assertNotNull(productionTree.getHeightMap().get(0), "Altura 0 deveria conter nós.");
        assertNotNull(productionTree.getHeightMap().get(1), "Altura 1 deveria conter nós.");
    }

    @Test
    void testGetTotalRequiredMaterials() throws IOException {
        System.out.println("Test GetTotalRequiredMaterials");
        // Criar um arquivo temporário para simular a entrada
        String filePath = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/testMaterials.csv";
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

        productionTree.getInformations(filePath);
        Map<ID, Float> totalMaterials = productionTree.getTotalRequiredMaterials();

        // Validar materiais calculados
        assertEquals(6, totalMaterials.size(), "Deveria haver 6 matérias-primas no total.");
        assertTrue(totalMaterials.containsKey(new ID(1014, TypeID.ITEM)), "Material 1014 deveria estar presente.");
        assertEquals(0.125f, totalMaterials.get(new ID(1014, TypeID.ITEM)), 0.01, "Quantidade total do material 1014 está incorreta.");
    }

    @Test
    void testEmptyTree() {
        System.out.println("Test Empty Tree");
        ProductionTree emptyTree = new ProductionTree();
        assertTrue(emptyTree.getNodesOfTree().isEmpty(), "Árvore vazia não deveria ter nós.");
        assertTrue(emptyTree.getHeightMap().isEmpty(), "Árvore vazia não deveria ter mapa de alturas.");
        assertEquals(0, emptyTree.getRawMaterials().size(), "Árvore vazia não deveria ter matérias-primas.");
    }
}