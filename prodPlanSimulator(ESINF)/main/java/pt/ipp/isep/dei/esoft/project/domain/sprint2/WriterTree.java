package pt.ipp.isep.dei.esoft.project.domain.sprint2;

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

public class WriterTree {
    static private PrintWriter treePrintWriter;
    static private ProductionTree productionTree;
    static private OperationRepository operationRepository;
    static private ItemRepository itemRepository;
    static private List<Operation> operationList;
    static private List<Item> itemList;


    public WriterTree(ProductionTree productionTree) {
        this.productionTree = productionTree;
        operationRepository = Repositories.getInstance().getOperationRepository();
        itemRepository = Repositories.getInstance().getItemRepository();
        itemList = new ArrayList<>(itemRepository.getItemList());
        operationList = new ArrayList<>(operationRepository.getOperations());

    }


    public static void writeBOOToUmlFile() {
        List<Node> boo = productionTree.getNodesOfTree();
        try {
            treePrintWriter = new PrintWriter("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/output/ProductionTree_BOO.puml");
            treePrintWriter.println("@startuml");
            treePrintWriter.println("graph TreeDiagram {");
            for (Node node : boo) {
                String opName = getOpName(node.getOperationID());
                String itmName = getItmName(node.getItemID());

                treePrintWriter.printf("\"%s\" [shape=rectangle]%n", opName);
                treePrintWriter.printf("\"%s\" -- \"%s\"[label = %.1f]%n",
                        opName,
                        itmName,
                        node.getItem_qtd());

                if (!node.getOperationMap().isEmpty()){
                    for (Map.Entry<ID,Float> operation : node.getOperationMap().entrySet()) {
                        treePrintWriter.printf("\"%s\" -- \"%s\"[label = %.1f]%n",
                                opName,
                                getOpName(operation.getKey()),
                                operation.getValue());
                    }
                }

                if (!node.getMaterialMap().isEmpty()){
                    for (Map.Entry<ID,Float> mat : node.getMaterialMap().entrySet()) {
                        String material = getItmName(mat.getKey());
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

    public static void writeBOMToUmlFile() {
        List<Node> bom = productionTree.getNodesOfTree();
        try {
            treePrintWriter = new PrintWriter("prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/output/ProductionTree_BOM.puml");
            treePrintWriter.println("@startuml");
            treePrintWriter.println("graph TreeDiagram {");

            for (Node node : bom) {
                String itmName = getItmName(node.getItemID());

                if (!node.getOperationMap().isEmpty()){
                    for (Map.Entry<ID,Float> operation : node.getOperationMap().entrySet()) {
                        String otherItem = getItmName(findeNextItemID(operation.getKey(),bom));
                        treePrintWriter.printf("\"%s\" -- \"%s\"[label = %.1f]%n",
                                itmName,
                                otherItem,
                                operation.getValue());
                    }
                }

                if (!node.getMaterialMap().isEmpty()){
                    for (Map.Entry<ID,Float> mat : node.getMaterialMap().entrySet()) {
                        String material = getItmName(mat.getKey());
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

    private static ID findeNextItemID(ID key,List<Node> bom) {
        for (Node node : bom){
            if(node.getOperationID().equals(key)){
                return node.getItemID();
            }
        }
        return null;
    }


    private static String getItmName(ID id) {
        for (Item item : itemList) {
            if (item.getItemID().equals(id)) {
                return item.getName();
            }
        }
        return "";
    }

    private static String getOpName(ID id) {
        for (Operation operation : operationList) {
            if(operation.getOperationId().equals(id)){
                return operation.getOperationName();
            }
        }
        return "";
    }

}
