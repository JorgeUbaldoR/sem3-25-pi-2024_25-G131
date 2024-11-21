package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.Node;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A classe {@code ProductionTreeController} é responsável por gerenciar e controlar a árvore de produção
 * de um sistema de planejamento de produção, manipulando operações e materiais através de interações com
 * repositórios de operações e itens. Ela fornece métodos para acessar, manipular e exibir informações sobre
 * a árvore de produção.
 */
public class ProductionTreeController {

    private ProductionTree productionTree;  // Representa a árvore de produção
    private ItemRepository itemRepository;  // Repositório de itens (materiais)
    private OperationRepository operationRepository;  // Repositório de operações

    /**
     * Construtor que inicializa a árvore de produção e os repositórios de itens e operações.
     */
    public ProductionTreeController() {
        productionTree = new ProductionTree();
        itemRepository = getItemRepository();
        operationRepository = getOperationRepository();
    }

    /**
     * Retorna o repositório de operações.
     *
     * @return O repositório de operações.
     */
    private OperationRepository getOperationRepository() {
        if (operationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            operationRepository = repositories.getOperationRepository();
        }
        return operationRepository;
    }

    /**
     * Retorna o repositório de itens.
     *
     * @return O repositório de itens.
     */
    private ItemRepository getItemRepository() {
        if (itemRepository == null) {
            Repositories repositories = Repositories.getInstance();
            itemRepository = repositories.getItemRepository();
        }
        return itemRepository;
    }

    /**
     * Define o nome da árvore de produção.
     *
     * @param name O nome da árvore de produção.
     */
    public void setName(String name) {
        productionTree.setPdtTreeName(name);
    }

    /**
     * Retorna a árvore de produção atual.
     *
     * @return A árvore de produção.
     */
    public ProductionTree getProductionTree() {
        return productionTree;
    }

    /**
     * Obtém informações sobre a árvore de produção a partir de um caminho de arquivo.
     *
     * @param path O caminho para o arquivo contendo as informações.
     * @return {@code true} se as informações forem carregadas com sucesso, {@code false} caso contrário.
     */
    public boolean getInformations(String path) {
        productionTree = new ProductionTree();
        return productionTree.getInformations(path);
    }

    /**
     * Obtém uma lista de itens ou operações para exibição, dependendo do valor de {@code flag}.
     *
     * @param flag Um valor inteiro que determina o que será retornado:
     *             - {@code 1} para itens.
     *             - Outro valor para operações.
     * @return Um mapa de IDs para nomes de itens ou operações.
     */
    public Map<ID, String> getListToShow(int flag) {
        Map<ID, String> map = new HashMap<>();
        if (flag == 1) {
            for (Item material : getItemRepository().getItemList()) {
                map.put(material.getItemID(), material.getName());
            }
            return map;
        }

        for (Operation operation : getOperationRepository().getOperations()) {
            map.put(operation.getOperationId(), operation.getOperationName());
        }
        return map;
    }

    /**
     * Verifica se uma operação é um material bruto (matéria-prima).
     *
     * @param selectedOperationID O ID da operação a ser verificada.
     * @return {@code true} se a operação for um material bruto, {@code false} caso contrário.
     */
    public boolean isRawMaterial(ID selectedOperationID) {
        return getProductionTree().getRawMaterials().containsKey(selectedOperationID);
    }

    /**
     * Retorna o nó de item associado a uma operação, dependendo se é um material bruto ou não.
     *
     * @param selectedOperationID O ID da operação.
     * @param rawMaterial Se {@code true}, retorna o nó de material bruto; caso contrário, o nó de material.
     * @return O nó de item correspondente à operação.
     */
    public Node getItemNode(ID selectedOperationID, boolean rawMaterial) {
        if (rawMaterial) {
            return getProductionTree().getRawMaterials().get(selectedOperationID);
        }
        return getProductionTree().getMaterials().get(selectedOperationID);
    }

    /**
     * Retorna o nó de operação correspondente ao ID da operação fornecido.
     *
     * @param selectedOperationID O ID da operação.
     * @return O nó de operação correspondente.
     */
    public Node getOperationNode(ID selectedOperationID) {
        return getProductionTree().getOperationNodeID().get(selectedOperationID);
    }

    /**
     * Encontra o nome da operação pai de um nó na árvore de produção, se existir.
     *
     * @param node O nó para o qual se deseja encontrar a operação pai.
     * @return O nome da operação pai ou {@code null} se não houver.
     */
    public String findParentOperation(Node node) {
        int height = node.getHeigthInTree();
        if (height != 0) {
            List<Node> nodesByHeight = getProductionTree().getHeightMap().get(node.getHeigthInTree() - 1);
            for (Node nodeInList : nodesByHeight) {
                if (nodeInList.getOperationMap().containsKey(node.getOperationID())) {
                    return findNameOperation(nodeInList.getOperationID());
                }
            }
        }
        return null;
    }

    /**
     * Retorna o nome de uma operação com base no ID da operação.
     *
     * @param operationID O ID da operação.
     * @return O nome da operação correspondente ao ID.
     */
    public String findNameOperation(ID operationID) {
        return getOperationRepository().getIdToOperation().get(operationID).getOperationName();
    }

    /**
     * Encontra o item pai e a quantidade correspondente de um nó na árvore de produção,
     * dependendo se é um material bruto ou não.
     *
     * @param node O nó para o qual se deseja encontrar o item pai.
     * @param rawMaterial Se {@code true}, procura no repositório de materiais brutos; caso contrário, em materiais.
     * @param selectedOperationID O ID da operação selecionada.
     * @return Um array com o nome do item pai e a quantidade correspondente.
     */
    public String[] findParentItem(Node node, boolean rawMaterial, ID selectedOperationID) {
        String[] parentAndQtd = new String[2];
        if (rawMaterial) {
            parentAndQtd[0] = findNameItem(node.getItemID());
            parentAndQtd[1] = String.valueOf(node.getMaterialMap().get(selectedOperationID));
            return parentAndQtd;
        }
        int height = node.getHeigthInTree();
        if (height != 0) {
            List<Node> nodesByHeight = getProductionTree().getHeightMap().get(node.getHeigthInTree() - 1);
            for (Node nodeInList : nodesByHeight) {
                if (nodeInList.getOperationMap().containsKey(node.getOperationID())) {
                    parentAndQtd[0] = findNameItem(nodeInList.getItemID());
                    parentAndQtd[1] = String.valueOf(nodeInList.getItem_qtd());
                    return parentAndQtd;
                }
            }
        }
        parentAndQtd[0] = null;
        parentAndQtd[1] = String.valueOf(node.getItem_qtd());
        return parentAndQtd;
    }

    /**
     * Retorna o nome de um item com base no seu ID.
     *
     * @param itemID O ID do item.
     * @return O nome do item correspondente ao ID.
     */
    public String findNameItem(ID itemID) {
        return getItemRepository().getMapItemList().get(itemID).getName();
    }

    /**
     * Retorna o nó de operação correspondente ao ID da operação fornecido.
     *
     * @param operationID O ID da operação.
     * @return O nó de operação correspondente.
     */
    public Node getNodeByOperationID(ID operationID) {
        return getProductionTree().getOperationNodeID().get(operationID);
    }

    public String getItemNameByID(ID itemID) {
        return getItemRepository().getItemNameByID(itemID);
    }
}
