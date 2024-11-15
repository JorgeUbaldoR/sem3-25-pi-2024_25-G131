package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.sprint2.ReadTreeInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductionTree {

    String pdtTreeName;
    Map<Integer, Node> nodesOfTree;

    public ProductionTree() {
        this.pdtTreeName = "No Name";
        nodesOfTree = new HashMap<>();
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


                Map<ID, Float> operationMap = new HashMap<>();

                for(int j = 1; j < arrayOperations.length; j += 2){
                    operationMap.put(new ID(Integer.parseInt(arrayOperations[j]), TypeID.OPERATION), Float.parseFloat(arrayOperations[j+1].replace(",", ".")));
                }

                Map<ID, Float> materialMap = new HashMap<>();
                for(int j = 1; j < arrayMaterials.length; j += 2){
                    materialMap.put(new ID(Integer.parseInt(arrayMaterials[j]), TypeID.ITEM), Float.parseFloat(arrayMaterials[j+1].replace(",", ".")));
                }
                Node node = new Node(operationID,itemID,qtd,operationMap,materialMap);
                nodesOfTree.put(i,node);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void setPdtTreeName(String pdtTreeName) {
        this.pdtTreeName = pdtTreeName;
    }

    public Map<Integer, Node> getNodesOfTree() {
        return nodesOfTree;
    }
}
