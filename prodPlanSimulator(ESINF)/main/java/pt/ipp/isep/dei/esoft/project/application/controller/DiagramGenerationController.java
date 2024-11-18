package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ProductionTree;
import pt.ipp.isep.dei.esoft.project.domain.sprint2.WriterTree;

/**
 * Controlador responsável pela geração de diagramas a partir de uma árvore de produção.
 * Este controlador interage com a classe {@link WriterTree} para gerar arquivos UML a partir
 * de um arquivo BOO ou BOM, ambos representando diferentes modelos de produção.
 */
public class DiagramGenerationController {

    private WriterTree writerTree;  // Instância da classe WriterTree para gerar diagramas
    private ProductionTree productionTree;  // Instância da árvore de produção

    /**
     * Construtor da classe DiagramGenerationController. Inicializa as variáveis de instância.
     */
    public DiagramGenerationController() {
    }

    /**
     * Obtém as informações necessárias para gerar a árvore de produção a partir de um arquivo.
     *
     * @param path Caminho do arquivo contendo os dados para a árvore de produção.
     * @return {@code true} se a leitura e a construção da árvore de produção forem bem-sucedidas,
     *         {@code false} caso contrário.
     */
    public boolean getInformations(String path){
        productionTree = new ProductionTree();  // Criação de uma nova árvore de produção
        return productionTree.getInformations(path);  // Carrega as informações do arquivo na árvore
    }

    /**
     * Gera um arquivo UML com base no modelo BOO (Bill of Operations) e o escreve no formato adequado.
     * A função interage com a classe {@link WriterTree} para realizar a escrita do arquivo.
     */
    public void writeBooToUmlFile(){
        writerTree = new WriterTree(productionTree);  // Criação de um novo escritor para a árvore de produção
        WriterTree.writeBOOToUmlFile();  // Geração e escrita do arquivo UML para o modelo BOO
    }

    /**
     * Gera um arquivo UML com base no modelo BOM (Bill of Materials) e o escreve no formato adequado.
     * A função interage com a classe {@link WriterTree} para realizar a escrita do arquivo.
     */
    public void writeBOMToUmlFile(){
        writerTree = new WriterTree(productionTree);  // Criação de um novo escritor para a árvore de produção
        WriterTree.writeBOMToUmlFile();  // Geração e escrita do arquivo UML para o modelo BOM
    }
}
