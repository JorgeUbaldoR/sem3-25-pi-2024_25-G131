package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.data.ReadTreeInfo;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.IOException;
import java.util.*;

/**
 * Represents a production tree containing nodes that define operations, items, and dependencies.
 * The tree maintains a hierarchical structure and provides functionality to read and organize
 * data from a file into nodes.
 */
public class ProductionTree {

    private ItemRepository itemRepository;

    private String pdtTreeName; // Name of the production tree.
    private final Map<Integer, List<Node>> heightMap; // Maps tree levels (heights) to lists of nodes.
    private final List<Node> nodesOfTree; // List of all nodes in the tree.
    private int treeHeight = 0; // Height of the tree.

    private final Map<ID, Node> materials; // Map of item IDs to corresponding nodes.
    private final Map<ID, Node> rawMaterials; // Map of raw material IDs to corresponding nodes.
    private final Map<ID, Node> operationNodeID; // Map of operation IDs to corresponding nodes.

    private final String BOO_PATH = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/input/boo.csv";

    /**
     * Constructs an empty production tree with default values.
     *
     * Complexity: O(1) - Basic initialization of fields.
     */
    public ProductionTree() {
        this.pdtTreeName = "No Name";
        nodesOfTree = new ArrayList<>();
        heightMap = new HashMap<>();
        materials = new HashMap<>();
        rawMaterials = new HashMap<>();
        operationNodeID = new HashMap<>();
        getItemRepository();
    }

    /**
     * Retrieves the ItemRepository instance.
     *
     * @return the ItemRepository instance
     */
    private ItemRepository getItemRepository() {
        if(itemRepository == null) {
            Repositories repositories = Repositories.getInstance();
            itemRepository = repositories.getItemRepository();
        }
        return itemRepository;

    }

    /**
     * Reads tree information from a file and constructs the nodes.
     *
     * @param path the file path to read data from.
     * @return true if information is successfully read and processed, false otherwise.
     *
     * Complexity: O(n * m) - n is the number of records in the file, m is the average
     * number of operations/materials per record.
     */
    public boolean getInformations(String path) {
        try {
            List<String[]> readedInformation = ReadTreeInfo.readBoo(path);
            if (readedInformation.isEmpty()) {
                return false;
            }

            for (int i = 0; i < readedInformation.size(); i += 3) {
                String[] firstThreeValues = readedInformation.get(i);
                String[] arrayOperations = readedInformation.get(i + 1);
                String[] arrayMaterials = readedInformation.get(i + 2);

                ID operationID = new ID(Integer.parseInt(firstThreeValues[0]), TypeID.OPERATION);
                ID itemID = new ID(Integer.parseInt(firstThreeValues[1]), TypeID.ITEM);
                float qtd = Float.parseFloat(firstThreeValues[2]);

                associatedQtdToItem(itemID,qtd);

                // Process operation map
                Map<ID, Float> operationMap = new HashMap<>();
                for (int j = 1; j < arrayOperations.length; j += 2) {
                    operationMap.put(new ID(Integer.parseInt(arrayOperations[j]), TypeID.OPERATION),
                            Float.parseFloat(arrayOperations[j + 1].replace(",", ".")));
                }

                Node node = new Node(operationID, itemID, qtd, operationMap, null);

                // Process material map
                Map<ID, Float> materialMap = new HashMap<>();
                for (int j = 1; j < arrayMaterials.length; j += 2) {
                    ID newID = new ID(Integer.parseInt(arrayMaterials[j]), TypeID.ITEM);
                    float newQtd = Float.parseFloat(arrayMaterials[j + 1].replace(",", "."));
                    materialMap.put(newID, newQtd);
                    rawMaterials.put(newID, node);
                    associatedQtdToItem(newID,newQtd);
                }
                node.setMaterialMap(materialMap);
                nodesOfTree.add(node);
                operationNodeID.put(operationID, node);
                materials.put(itemID, node);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found...");
        }

        fillTreeHeight(nodesOfTree.get(0), 0);
        return true;
    }

    private void associatedQtdToItem(ID itemID, float qtd) {
        getItemRepository().getMapItemList().get(itemID).setQuantity(qtd);
    }

    /**
     * Recursively calculates and assigns heights to nodes in the tree.
     *
     * @param node   the current node being processed.
     * @param height the height of the current node.
     *
     * Complexity: O(n) - Processes each node exactly once.
     */
    private void fillTreeHeight(Node node, int height) {
        if (node == null) {
            return;
        }

        heightMap.putIfAbsent(height, new ArrayList<>());
        heightMap.get(height).add(node);
        node.setHeigthInTree(height);

        treeHeight = Math.max(treeHeight, height + 1);

        for (ID id : node.getOperationMap().keySet()) {
            Node childNode = findNodeByOperation(id);
            if (childNode != null) {
                fillTreeHeight(childNode, height + 1);
            }
        }
    }

    /**
     * Finds a node in the tree by its operation ID.
     *
     * @param id the operation ID to search for.
     * @return the node corresponding to the operation ID, or null if not found.
     *
     * Complexity: O(n) - Searches through the list of nodes.
     */
    private Node findNodeByOperation(ID id) {
        for (Node node : nodesOfTree) {
            if (node.getOperationID().equals(id)) {
                return node;
            }
        }
        return null;
    }

    // Getters and setters

    /**
     * Sets the name of the production tree.
     *
     * @param pdtTreeName the new name of the tree.
     *
     * Complexity: O(1) - Simple field assignment.
     */
    public void setPdtTreeName(String pdtTreeName) {
        this.pdtTreeName = pdtTreeName;
    }

    /**
     * @return the list of nodes in the tree.
     *
     * Complexity: O(1) - Simple field access.
     */
    public List<Node> getNodesOfTree() {
        return nodesOfTree;
    }

    /**
     * @return the height map of the tree.
     *
     * Complexity: O(1) - Simple field access.
     */
    public Map<Integer, List<Node>> getHeightMap() {
        return heightMap;
    }

    /**
     * @return the map of operation IDs to nodes.
     *
     * Complexity: O(1) - Simple field access.
     */
    public Map<ID, Node> getOperationNodeID() {
        return operationNodeID;
    }

    /**
     * @return the map of material IDs to nodes.
     *
     * Complexity: O(1) - Simple field access.
     */
    public Map<ID, Node> getMaterials() {
        return materials;
    }

    /**
     * @return the map of raw material IDs to nodes.
     *
     * Complexity: O(1) - Simple field access.
     */
    public Map<ID, Node> getRawMaterials() {
        return rawMaterials;
    }

    /**
     * Calculates the total quantity of required materials for production.
     *
     * @return a map where the key is the material ID and the value is the total quantity required
     *
     * Time Complexity: O(n), where n is the number of raw materials. The loop iterates through all raw materials,
     * and the operations inside the loop are O(1) assuming constant-time map operations.
     */
    public Map<ID, Float> getTotalRequiredMaterials() {
        getInformations(BOO_PATH);

        Map<ID, Float> totalRequiredMaterials = new HashMap<>();

        for (Map.Entry<ID, Node> rawMaterial : rawMaterials.entrySet()) {
            Node materialNode = rawMaterial.getValue();
            ID materialID = rawMaterial.getKey();

            float itemQtd = materialNode.getItem_qtd();
            float totalQtd = itemQtd * materialNode.getItemQtdByID(materialID);

            totalRequiredMaterials.put(materialID, totalQtd);
        }

        return totalRequiredMaterials;
    }

    public List<List<String>> getCriticalPath() {
        ProductionTree pdt = new ProductionTree();
        pdt.getInformations(BOO_PATH);

        Map<Integer, List<Node>> heightMap = pdt.getHeightMap();
        Map<ID, Node> operationNodeID = pdt.getOperationNodeID();

        List<List<String>> criticalPaths = new ArrayList<>();

        // Identificar os nós raízes (os de menor altura)
        int minHeight = heightMap.keySet().stream().min(Integer::compareTo).orElse(0);
        List<Node> rootNodes = heightMap.get(minHeight);


        if (rootNodes == null || rootNodes.isEmpty()) {
            throw new IllegalStateException("Tree does not have root nodes.");
        }

        PriorityQueue<QueueNode> pq = new PriorityQueue<>(Comparator.comparingInt(QueueNode::getDepth));

        for (Node rootNode : rootNodes) {
            pq.offer(new QueueNode(rootNode, new ArrayList<>(), 0));
        }

        while (!pq.isEmpty()) {
            QueueNode current = pq.poll();
            Node currentNode = current.node;
            List<String> currentPath = new ArrayList<>(current.path);

            currentPath.add(currentNode.getOperationNameByID());

            Map<ID, Float> operationMap = currentNode.getOperationMap();
            if (operationMap.isEmpty()) {

                criticalPaths.add(currentPath); // É um nó folha, adicionar o caminho crítico

            } else {

                for (Map.Entry<ID, Float> entry : operationMap.entrySet()) { // Adicionar os filhos à fila de prioridade
                    ID childId = entry.getKey();
                    Node childNode = operationNodeID.get(childId);

                    if (childNode != null) {
                        pq.offer(new QueueNode(childNode, currentPath, current.depth + 1));
                    }
                }
            }
        }

        return criticalPaths ;
    }

    private static class QueueNode {
        private final Node node;
        private final List<String> path;
        private final int depth;

        public QueueNode(Node node, List<String> path, int depth) {
            this.node = node;
            this.path = path;
            this.depth = depth;
        }

        public int getDepth() {
            return depth;
        }
    }

}
