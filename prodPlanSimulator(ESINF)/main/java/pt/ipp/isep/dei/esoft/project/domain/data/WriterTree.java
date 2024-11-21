package pt.ipp.isep.dei.esoft.project.domain.data;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class WriterTree {
    static private PrintWriter treePrintWriter;
    static private ProductionTree productionTree;
    static private OperationRepository operationRepository;
    static private ItemRepository itemRepository;
    static private List<Operation> operationList;
    static private List<Item> itemList;

    /**
     * Constructor to initialize the WriterTree with the given ProductionTree.
     * Time Complexity: O(M + N), where M is the number of operations and N is the number of items.
     */
    public WriterTree(ProductionTree productionTree) {
        this.productionTree = productionTree;
        operationRepository = Repositories.getInstance().getOperationRepository();
        itemRepository = Repositories.getInstance().getItemRepository();
        itemList = new ArrayList<>(itemRepository.getItemList()); // O(N)
        operationList = new ArrayList<>(operationRepository.getOperations()); // O(M)
    }

    /**
     * Writes the BOO (Bill of Operations) production tree to a UML file.
     * Time Complexity: O(P * (O + M)), where P is the number of nodes,
     * O is the maximum size of the operation map, and M is the maximum size of the material map for a node.
     */
    public static void writeBOOToUmlFile() {
        List<Node> boo = productionTree.getNodesOfTree(); // O(P)
        try {
            treePrintWriter = new PrintWriter("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/output/ProductionTree_BOO.puml");
            treePrintWriter.println("@startuml");
            treePrintWriter.println("graph TreeDiagram {");

            System.out.println();
            for (Node node : boo) { // O(P)
                String opName = getOpName(node.getOperationID()); // O(M)
                String itmName = getItmName(node.getItemID()); // O(N)

                System.out.printf("%sProcessing Node%s: Operation -> %s'%-20s%s | Item -> %s'%-20s%s | Quantity -> %s%-5.1f%s%n",
                        ANSI_BRIGHT_BLACK,ANSI_RESET,
                        ANSI_BRIGHT_WHITE,opName+"'",ANSI_RESET,
                        ANSI_BRIGHT_WHITE,itmName+"'",ANSI_RESET,
                        ANSI_BRIGHT_WHITE,node.getItem_qtd(),ANSI_RESET);
                treePrintWriter.printf("\"%s\" [shape=rectangle]%n", opName);
                treePrintWriter.printf("\"%s\" -- \"%s\"[label = %.1f]%n",
                        opName,
                        itmName,
                        node.getItem_qtd());

                if (!node.getOperationMap().isEmpty()) { // O(O)
                    for (Map.Entry<ID, Float> operation : node.getOperationMap().entrySet()) {
                        treePrintWriter.printf("\"%s\" -- \"%s\"[label = %.1f]%n",
                                opName,
                                getOpName(operation.getKey()), // O(M)
                                operation.getValue());
                    }
                }

                if (!node.getMaterialMap().isEmpty()) { // O(M)
                    for (Map.Entry<ID, Float> mat : node.getMaterialMap().entrySet()) {
                        String material = getItmName(mat.getKey()); // O(N)
                        treePrintWriter.printf("\"%s\" -- \"%s\"[label = %.3f]%n",
                                opName,
                                material,
                                mat.getValue());
                        treePrintWriter.printf("\"%s\" [shape=hexagon]%n", material);
                    }
                }
            }
            treePrintWriter.printf("}%n");
            treePrintWriter.println("@enduml");
            treePrintWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File write error: " + exception.getMessage());
        }
    }

    /**
     * Writes the BOM (Bill of Materials) production tree to a UML file.
     * Time Complexity: O(P * (O + M)), where P is the number of nodes,
     * O is the maximum size of the operation map, and M is the maximum size of the material map for a node.
     */
    public static void writeBOMToUmlFile() {
        List<Node> bom = productionTree.getNodesOfTree(); // O(P)
        try {
            treePrintWriter = new PrintWriter("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/output/ProductionTree_BOM.puml");
            treePrintWriter.println("@startuml");
            treePrintWriter.println("graph TreeDiagram {");
            System.out.println();
            for (Node node : bom) { // O(P)
                String itmName = getItmName(node.getItemID()); // O(N)

                System.out.printf("%sProcessing Node%s: Item -> %s'%s'%s%n",ANSI_BRIGHT_BLACK,ANSI_RESET,ANSI_BRIGHT_WHITE ,itmName,ANSI_RESET);

                if (!node.getOperationMap().isEmpty()) { // O(O)
                    for (Map.Entry<ID, Float> operation : node.getOperationMap().entrySet()) {
                        String otherItem = getItmName(findeNextItemID(operation.getKey(), bom)); // O(P * N)
                        treePrintWriter.printf("\"%s\" -- \"%s\"[label = %.1f]%n",
                                itmName,
                                otherItem,
                                operation.getValue());
                    }
                }

                if (!node.getMaterialMap().isEmpty()) { // O(M)
                    for (Map.Entry<ID, Float> mat : node.getMaterialMap().entrySet()) {
                        String material = getItmName(mat.getKey()); // O(N)
                        treePrintWriter.printf("\"%s\" -- \"%s\"[label = %.3f]%n",
                                itmName,
                                material,
                                mat.getValue());
                        treePrintWriter.printf("\"%s\" [shape=hexagon]%n", material);
                    }
                }
            }
            treePrintWriter.printf("}%n");
            treePrintWriter.println("@enduml");
            treePrintWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File write error: " + exception.getMessage());
        }
    }

    /**
     * Finds the item ID corresponding to the next operation in the production tree.
     * Time Complexity: O(P), where P is the number of nodes in the production tree.
     *
     * @param key the ID of the operation to find.
     * @param bom the list of nodes in the production tree.
     * @return the ID of the next item, or null if not found.
     */
    private static ID findeNextItemID(ID key, List<Node> bom) {
        for (Node node : bom) { // O(P)
            if (node.getOperationID().equals(key)) {
                return node.getItemID();
            }
        }
        return null;
    }

    /**
     * Gets the name of an item by its ID.
     * Time Complexity: O(N), where N is the number of items.
     *
     * @param id the ID of the item.
     * @return the name of the item, or an empty string if not found.
     */
    private static String getItmName(ID id) {
        for (Item item : itemList) { // O(N)
            if (item.getItemID().equals(id)) {
                return item.getName();
            }
        }
        return "";
    }

    /**
     * Gets the name of an operation by its ID.
     * Time Complexity: O(M), where M is the number of operations.
     *
     * @param id the ID of the operation.
     * @return the name of the operation, or an empty string if not found.
     */
    private static String getOpName(ID id) {
        for (Operation operation : operationList) { // O(M)
            if (operation.getOperationId().equals(id)) {
                return operation.getOperationName();
            }
        }
        return "";
    }
}
