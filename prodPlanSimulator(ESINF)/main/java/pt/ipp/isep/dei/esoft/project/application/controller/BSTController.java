package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.BinarySearchTree;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ObjectBST;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Comparator;
import java.util.List;

/**
 * Controller responsible for managing a Binary Search Tree (BST) built from a list of items.
 */
public class BSTController {

    private ItemRepository itemRepository;
    private BinarySearchTree binarySearchTree;

    /**
     * Constructor that initializes the item repository and the BST.
     * The tree is filled in a balanced manner based on item quantities.
     * Complexity: O(n log n), considering sorting and balanced insertion.
     */
    public BSTController() {
        itemRepository = getItemRepository();
        binarySearchTree = new BinarySearchTree();
        fillTreeByQuantity();
    }

    /**
     * Fills the BST in a balanced manner with items from the repository, sorted by quantity.
     * Complexity: O(n log n), where:
     *  - Sorting the item list: O(n log n)
     *  - Balanced insertion into the tree: O(n)
     */
    private void fillTreeByQuantity() {
        List<Item> itemList = itemRepository.getItemList();

        // Sort items by quantity (O(n log n))
        itemList.sort(Comparator.comparingDouble(Item::getQuantity));

        // Insert items into the tree in a balanced way (O(n))
        insertBalanced(itemList, 0, itemList.size() - 1);
    }

    /**
     * Inserts items into the BST in a balanced manner.
     * Complexity: O(n), where n is the number of items in the list, as each element is inserted once.
     *
     * @param sortedList List of items sorted by quantity.
     * @param start Start index of the subarray.
     * @param end End index of the subarray.
     */
    private void insertBalanced(List<Item> sortedList, int start, int end) {
        if (start > end) {
            return;
        }

        // Find the middle element (O(1))
        int mid = (start + end) / 2;

        // Insert the middle element into the BST (O(log n) per insertion)
        binarySearchTree.insert(sortedList.get(mid));

        // Recursively insert elements into the left and right subtrees
        insertBalanced(sortedList, start, mid - 1);
        insertBalanced(sortedList, mid + 1, end);
    }

    /**
     * Retrieves the item repository (lazy initialization).
     * Complexity: O(1).
     *
     * @return Item repository instance.
     */
    private ItemRepository getItemRepository() {
        if (itemRepository == null) {
            Repositories repositories = Repositories.getInstance();
            itemRepository = repositories.getItemRepository();
        }
        return itemRepository;
    }

    /**
     * Generates a representation of the BST in increasing order.
     * Complexity: O(n), where n is the number of nodes in the tree.
     *
     * @return Iterable with the BST objects in increasing order.
     */
    public Iterable<ObjectBST> generateTreeIncrescingOrder() {
        return binarySearchTree.inOrder();
    }

    /**
     * Generates a representation of the BST in decreasing order.
     * Complexity: O(n), where n is the number of nodes in the tree.
     *
     * @return Iterable with the BST objects in decreasing order.
     */
    public Iterable<ObjectBST> generateTreeDecrescingOrder() {
        return binarySearchTree.inOrderReverse();
    }

    /**
     * Retrieves the constructed BST.
     * Complexity: O(1).
     *
     * @return The binary search tree instance.
     */
    public BinarySearchTree getBinarySearchTree() {
        return binarySearchTree;
    }
}
