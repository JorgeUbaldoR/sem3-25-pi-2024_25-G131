package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;

import java.util.Map;
import java.util.Objects;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class ObjectBST implements Comparable<ObjectBST> {
    private float quantityOfElements;
    private Map<ID,Item> itemsWithQuantity;

    public ObjectBST(Map<ID, Item> itemsWithQuantity, float quantityOfElements) {
        this.itemsWithQuantity = itemsWithQuantity;
        this.quantityOfElements = quantityOfElements;
    }

    public float getQuantityOfElements() {
        return quantityOfElements;
    }

    public void setQuantityOfElements(float quantityOfElements) {
        this.quantityOfElements = quantityOfElements;
    }

    public Map<ID, Item> getItemsWithQuantity() {
        return itemsWithQuantity;
    }

    public void setItemsWithQuantity(Map<ID, Item> itemsWithQuantity) {
        this.itemsWithQuantity = itemsWithQuantity;
    }

    public void addItem(Item item) {
         getItemsWithQuantity().put(item.getItemID(),item);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBST objectBST = (ObjectBST) o;
        return Float.compare(quantityOfElements, objectBST.quantityOfElements) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(quantityOfElements);
    }

    @Override
    public int compareTo(ObjectBST o) {
        return (int) (this.quantityOfElements - o.quantityOfElements);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ANSI_BRIGHT_BLACK+"• Quantity "+ANSI_RESET+"-> ").append(ANSI_BRIGHT_WHITE+quantityOfElements+ANSI_RESET).append("\n[ ");

        int num = itemsWithQuantity.size();
        int qtd = 1;
        for (Item item : itemsWithQuantity.values()) {
            stringBuilder.append(item.getItemID());
            if (qtd < num) {
                stringBuilder.append(", ");
            }
            qtd++;
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

}
