package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import java.util.ArrayList;
import java.util.List;

public class Node<E > {
    String name;
    int quantity;
    List<Node> children;

    public Node(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public String getName() {return name;}
    public int getQuantity() {return quantity;}
    public List<Node> getChildren() {return children;}


}
