package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;

import java.util.Map;
import java.util.Objects;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

/**
 * Represents an object in the Binary Search Tree (BST) with a quantity of elements and a map of items with their quantities.
 * This class is used to store information about an object in the tree, including the quantity of elements and the
 * items associated with that quantity.
 */
public class ObjectBST implements Comparable<ObjectBST> {

    private float quantityOfElements;
    private Map<ID, Item> itemsWithQuantity;

    /**
     * Constructs an ObjectBST instance with a map of items and a quantity of elements.
     *
     * @param itemsWithQuantity A map of items with their associated quantities.
     * @param quantityOfElements The quantity of elements associated with this object.
     *
     * Time Complexity: O(1) - The constructor only initializes the instance variables with the provided values.
     */
    public ObjectBST(Map<ID, Item> itemsWithQuantity, float quantityOfElements) {
        this.itemsWithQuantity = itemsWithQuantity;
        this.quantityOfElements = quantityOfElements;
    }

    /**
     * Gets the quantity of elements associated with this object.
     *
     * @return The quantity of elements.
     *
     * Time Complexity: O(1) - Accessing the field directly takes constant time.
     */
    public float getQuantityOfElements() {
        return quantityOfElements;
    }

    /**
     * Sets the quantity of elements associated with this object.
     *
     * @param quantityOfElements The quantity to set.
     *
     * Time Complexity: O(1) - Assigning a value to a field takes constant time.
     */
    public void setQuantityOfElements(float quantityOfElements) {
        this.quantityOfElements = quantityOfElements;
    }

    /**
     * Gets the map of items with their associated quantities.
     *
     * @return The map of items.
     *
     * Time Complexity: O(1) - Accessing the map directly takes constant time.
     */
    public Map<ID, Item> getItemsWithQuantity() {
        return itemsWithQuantity;
    }

    /**
     * Sets the map of items with their associated quantities.
     *
     * @param itemsWithQuantity The map of items to set.
     *
     * Time Complexity: O(1) - Assigning a value to a field takes constant time.
     */
    public void setItemsWithQuantity(Map<ID, Item> itemsWithQuantity) {
        this.itemsWithQuantity = itemsWithQuantity;
    }

    /**
     * Adds an item to the map of items with their associated quantities.
     *
     * @param item The item to add.
     *
     * Time Complexity: O(1) - Inserting an item into a map by key is constant time on average.
     */
    public void addItem(Item item) {
        getItemsWithQuantity().put(item.getItemID(), item);
    }

    /**
     * Compares this ObjectBST with another based on the quantity of elements.
     *
     * @param o The other ObjectBST to compare.
     * @return A negative integer, zero, or a positive integer as this ObjectBST is less than, equal to, or greater than the specified ObjectBST.
     *
     * Time Complexity: O(1) - The comparison is based on a single arithmetic operation, which takes constant time.
     */
    @Override
    public int compareTo(ObjectBST o) {
        return (int) (this.quantityOfElements - o.quantityOfElements);
    }

    /**
     * Compares this ObjectBST with another for equality.
     *
     * @param o The object to compare.
     * @return true if the two ObjectBST objects are equal; false otherwise.
     *
     * Time Complexity: O(1) - The comparison is based on a simple float comparison, which takes constant time.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBST objectBST = (ObjectBST) o;
        return Float.compare(quantityOfElements, objectBST.quantityOfElements) == 0;
    }

    /**
     * Returns a hash code value for this ObjectBST.
     *
     * @return A hash code value for this ObjectBST.
     *
     * Time Complexity: O(1) - The `Objects.hashCode()` method runs in constant time for a single field.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(quantityOfElements);
    }

    /**
     * Returns a string representation of this ObjectBST, including the quantity of elements and a list of item IDs.
     * The item IDs are displayed in square brackets and separated by commas.
     *
     * @return A string representation of this ObjectBST.
     *
     * Time Complexity: O(n) - The time complexity is proportional to the number of items in `itemsWithQuantity`.
     * Each item is added to the string builder, so if there are `n` items, the time complexity is O(n).
     */
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