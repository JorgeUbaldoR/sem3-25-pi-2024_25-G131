package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.sprint2.ReadTreeInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductionTree {

    String pdtTreeName;
    Map<Integer, List<Node>> heightMap;
    List<Node> nodesOfTree;
    int treeHeight = 0;

    Map<ID, Float> materialsInventory;

    public ProductionTree() {
        this.pdtTreeName = "No Name";
        nodesOfTree = new ArrayList<>();
        heightMap = new HashMap<>();
        materialsInventory = new HashMap<>();
    }

    public boolean getInformations(String path) {
        try{
            List<String[]> readedInformation = ReadTreeInfo.readBoo(path);
            if (readedInformation.isEmpty()) {
                return false;
            }
            for (int i = 0; i < readedInformation.size(); i += 3) {
                String[] firstThreeValues = readedInformation.get(i);
                String[] arrayOperations = readedInformation.get(i+1);
                String[] arrayMaterials = readedInformation.get(i+2);

                ID operationID = new ID(Integer.parseInt(firstThreeValues[0]), TypeID.OPERATION);
                ID itemID = new ID(Integer.parseInt(firstThreeValues[1]), TypeID.ITEM);
                float qtd = Float.parseFloat(firstThreeValues[2]);
                materialsInventory.put(itemID,qtd);


                Map<ID, Float> operationMap = new HashMap<>();

                for(int j = 1; j < arrayOperations.length; j += 2){
                    operationMap.put(new ID(Integer.parseInt(arrayOperations[j]), TypeID.OPERATION), Float.parseFloat(arrayOperations[j+1].replace(",", ".")));
                }

                Map<ID, Float> materialMap = new HashMap<>();
                for(int j = 1; j < arrayMaterials.length; j += 2){
                    ID newID = new ID(Integer.parseInt(arrayMaterials[j]),TypeID.ITEM);
                    Float newQtd = Float.parseFloat(arrayMaterials[j+1].replace(",", "."));

                    materialMap.put(newID,newQtd);
                    materialsInventory.put(newID,newQtd);
                }
                Node node = new Node(operationID,itemID,qtd,operationMap,materialMap);
                nodesOfTree.add(node);

            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found...");
        }
        fillTreeHeight(nodesOfTree.get(0), 0);
        return true;
    }


    private void fillTreeHeight(Node node, int height) {
        if (node == null) {
            return;
        }

        heightMap.putIfAbsent(height, new ArrayList<>());
        heightMap.get(height).add(node);

        treeHeight = Math.max(treeHeight, height + 1);

        for (ID id : node.getOperationMap().keySet()) {
            Node childNode = findNodeByOperation(id);
            if (childNode != null) {
                fillTreeHeight(childNode, height + 1);
            }
        }
    }



    private Node findNodeByOperation(ID id) {
        for (Node node : nodesOfTree) {
            if (node.getOperationID().equals(id)) {
                return node;
            }
        }
        return null;
    }



    public void setPdtTreeName(String pdtTreeName) {
        this.pdtTreeName = pdtTreeName;
    }

    public List<Node> getNodesOfTree() {
        return nodesOfTree;
    }

    public Map<Integer, List<Node>> getHeightMap() {
        return heightMap;
    }

    public Map<ID, Float> getMaterialsInventory() {
        return materialsInventory;
    }
}
