package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import pt.ipp.isep.dei.esoft.project.domain.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A generic Binary Search Tree (BST) implementation.
 */
public class BinarySearchTree  {


    /**
     * Nested static class for a binary search tree node.
     */

    protected static class Node<ObjectBST> {
        private ObjectBST element;          // an element stored at this node
        private Node<ObjectBST> left;       // a reference to the left child (if any)
        private Node<ObjectBST> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e          the element to be stored
         * @param leftChild  reference to a left child node
         * @param rightChild reference to a right child node
         */
        public Node(ObjectBST e, Node<ObjectBST> leftChild, Node<ObjectBST> rightChild) {
            element = e;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        public ObjectBST getElement() {
            return element;
        }

        public Node<ObjectBST> getLeft() {
            return left;
        }

        public Node<ObjectBST> getRight() {
            return right;
        }

        // update methods
        public void setElement(ObjectBST e) {
            element = e;
        }

        public void setLeft(Node<ObjectBST> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<ObjectBST> rightChild) {
            right = rightChild;
        }
    }

    //----------- end of nested Node class -----------

    protected Node<ObjectBST> root;     // root of the tree


    /** Constructs an empty binary search tree. */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Returns the root node of the tree.
     *
     * @return root node of the tree, or null if the tree is empty.
     */
    public Node<ObjectBST> getRoot() {
        return root;
    }

    /**
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<ObjectBST> root() {
        return root;
    }

    /**
     * Verifies if the tree is empty.
     *
     * @return true if the tree is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param node    the starting node for the search
     * @param element the element to find
     * @return the Node that contains the element, or null otherwise
     */
    protected Node<ObjectBST> find(Node<ObjectBST> node, Item element) {
        if (node == null) {
            return null;
        }

        if (node.getElement().getQuantityOfElements() == element.getQuantity()) {
            if(node.getElement().getItemsWithQuantity().containsKey(element.getItemID())) {
                return node;
            }
            return null;
        }

        if (node.getElement().getQuantityOfElements() > element.getQuantity()) {
            return find(node.getLeft(), element);
        } else {
            return find(node.getRight(), element);
        }
    }

    /**
     * Inserts an item into the binary search tree (BST).
     *<p>
     * Time Complexity:
     * - Average Case: O(log(n)) for balanced trees, as we only traverse the height of the tree.
     * - Worst Case: O(n) for unbalanced trees (e.g., all elements in sorted order).
     *
     * Space Complexity:
     * - Average Case: O(log(n)) due to recursive calls on the stack.
     * - Worst Case: O(n) for unbalanced trees, where recursion depth equals the number of nodes.
     *
     * @param item the item to insert
     */
    public void insert(Item item) {
        root = insert(item, root());
    }


    /**
     * Helper method for inserting an item into the BST.
     *
     * Time Complexity:
     * - Average Case: O(log(n)) for balanced trees, as we only traverse the height of the tree.
     * - Worst Case: O(n) for unbalanced trees.
     *
     * Space Complexity:
     * - Average Case: O(log(n)) due to recursion on the stack.
     * - Worst Case: O(n) for unbalanced trees.
     *
     * @param item the item to insert
     * @param node the current node in the recursion
     * @return the updated node after insertion
     */
    private Node<ObjectBST> insert(Item item, Node<ObjectBST> node) {

        if (node == null) {
            ObjectBST newObject = new ObjectBST(new HashMap<>(),item.getQuantity());
            newObject.getItemsWithQuantity().put(item.getItemID(), item);
            return new Node(newObject, null, null);
        }
        if(node.getElement().getQuantityOfElements() == item.getQuantity()) {
            node.getElement().addItem(item);
            return node;
        }else if (node.getElement().getQuantityOfElements() > item.getQuantity()) {
            node.setLeft(insert(item, node.getLeft()));
        } else if (node.getElement().getQuantityOfElements() < item.getQuantity()) {
            node.setRight(insert(item, node.getRight()));
        }

        return node;
    }

    /**
     * Removes an element from the binary search tree (BST) while maintaining the tree's consistency.
     *
     * Time Complexity:
     * - Average Case: O(log(n)) for balanced trees, as we only traverse the height of the tree.
     * - Worst Case: O(n) for unbalanced trees.
     *<p>
     * Space Complexity:
     * - Average Case: O(log(n)) due to recursive calls on the stack.
     * - Worst Case: O(n) for unbalanced trees.
     *
     * @param element the element to remove
     */
    public void remove(ObjectBST element) {
        root = remove(element, root());
    }

    /**
     * Helper method to remove an element from the BST.
     *<p>
     * Time Complexity:
     * - Average Case: O(log(n)) for balanced trees, as we only traverse the height of the tree.
     * - Worst Case: O(n) for unbalanced trees.
     *<p>
     * Space Complexity:
     * - Average Case: O(log(n)) due to recursion on the stack.
     * - Worst Case: O(n) for unbalanced trees.
     *<p>
     * @param element the element to remove
     * @param node the current node in the recursion
     * @return the updated node after removal
     */
    private Node<ObjectBST> remove(ObjectBST element, Node<ObjectBST> node) {

        if (node == null) {
            return null;    //throw new IllegalArgumentException("Element does not exist");
        }
        if (element.getQuantityOfElements() - node.getElement().getQuantityOfElements() == 0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            ObjectBST min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
        } else if (element.getQuantityOfElements() < node.getElement().getQuantityOfElements())
            node.setLeft(remove(element, node.getLeft()));
        else
            node.setRight(remove(element, node.getRight()));

        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *<p>
     * This method calculates the total number of nodes by performing a recursive traversal
     * of the tree, visiting each node exactly once.
     *<p>
     * Time Complexity:
     * - O(n), where n is the number of nodes in the tree. In the worst case, we need to visit
     *   every node in the tree.
     *<p>
     * Space Complexity:
     * - O(h), where h is the height of the tree. This is due to the recursion depth in the
     *   worst case, which could be equal to the height of the tree.
     *   - For a balanced tree, h = O(log(n)).
     *   - For an unbalanced tree, h = O(n).
     *
     * @return the number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

    /**
     * Helper method that recursively calculates the size of the subtree rooted at the given node.
     *<p>
     * Time Complexity:
     * - O(n), where n is the number of nodes in the subtree. Each node is visited exactly once.
     *<p>
     * Space Complexity:
     * - O(h), where h is the height of the tree. The recursion depth can go up to the height
     *   of the tree, which is O(log(n)) for balanced trees and O(n) for unbalanced trees.
     *
     * @param node the root of the subtree
     * @return the number of nodes in the subtree
     */
    private int size(Node<ObjectBST> node) {
        if (node != null) {
            return 1 + size(node.getLeft()) + size(node.getRight());
        }
        return 0;
    }

    /*
     * Returns the height of the tree
     * @return height
     */
    public int height() {
        return height(root);
    }

    /**
     * Returns the height of the subtree rooted at Node node.
     * @param node A valid Node within the tree
     * @return height
     */
    protected int height(Node<ObjectBST> node) {
        if(node == null) {
            return -1;
        }

        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Returns the height of the tree.
     *<p>
     * This method calculates the height of the tree by recursively determining the height of
     * each subtree and returning the maximum height among them.
     * The height of an empty tree is defined as -1, and the height of a tree with just one node
     * is 0.
     *<p>
     * Time Complexity:
     * - O(n), where n is the number of nodes in the tree. In the worst case, we need to visit
     *   every node to calculate the height.
     *<p>
     * Space Complexity:
     * - O(h), where h is the height of the tree. This is due to the recursion depth, which can
     *   go up to the height of the tree.
     *   - For a balanced tree, h = O(log(n)).
     *   - For an unbalanced tree, h = O(n).
     *<p>
     * @return the height of the tree
     */
    public ObjectBST smallestElement() {
        return smallestElement(root());
    }


    /**
     * Helper method that recursively calculates the height of the subtree rooted at the given node.
     *<p>
     * Time Complexity:
     * - O(n), where n is the number of nodes in the subtree. Each node is visited exactly once
     *   to calculate the height.
     *<p>
     * Space Complexity:
     * - O(h), where h is the height of the tree. The recursion depth can go up to the height
     *   of the tree.
     *   - For a balanced tree, h = O(log(n)).
     *   - For an unbalanced tree, h = O(n).
     *<p>
     * @param node the root of the subtree
     * @return the height of the subtree
     */
    protected ObjectBST smallestElement(Node<ObjectBST> node) {
        if (node.getLeft() == null) {
            return node.getElement();
        }
        return smallestElement(node.getLeft());
    }

    /**
     * Returns an iterable collection of elements of the tree in in-order traversal.
     *<p>
     * This method performs an in-order traversal of the binary search tree (BST),
     * meaning it visits nodes in the following order: left subtree, root, right subtree.
     * The result is returned as an iterable collection (a snapshot of the tree elements)
     * in the order defined by the in-order traversal.
     *<p>
     * Time Complexity:
     * - O(n), where n is the number of nodes in the tree. The method visits each node
     *   exactly once to add its element to the snapshot.
     *<p>
     * Space Complexity:
     * - O(n), where n is the number of nodes in the tree. The method uses a list to store
     *   the elements of the tree, which requires O(n) space.
     *
     * @return an iterable collection of the tree's elements in in-order
     */
    public Iterable<ObjectBST> inOrder() {
        List<ObjectBST> snapshot = new ArrayList<>();
        if (root != null)
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot using an in-order traversal.
     *<p>
     * This is a helper method that performs the actual recursive traversal of the tree.
     * It adds the elements of the subtree rooted at the provided node to the given snapshot
     * in the order defined by the in-order traversal: left subtree, root, right subtree.
     *<p>
     * Time Complexity:
     * - O(n), where n is the number of nodes in the subtree. The method visits each node
     *   exactly once during the traversal.
     *<p>
     * Space Complexity:
     * - O(h), where h is the height of the tree. The space complexity is determined by the
     *   depth of the recursion stack. In the worst case (unbalanced tree), this could be O(n).
     *   In a balanced tree, this will be O(log n).
     *
     * @param node the root node of the subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(Node<ObjectBST> node, List<ObjectBST> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }



    /**
     * Returns an iterable collection of elements of the tree, reported in in-order reverse traversal.
     *<p>
     * This method performs a reverse in-order traversal of the binary search tree (BST),
     * meaning it visits nodes in the following order: right subtree, root, left subtree.
     * The result is returned as an iterable collection (a snapshot of the tree elements)
     * in the reverse in-order order, which is the reverse of the standard in-order traversal.
     *<p>
     * Time Complexity:
     * - O(n), where n is the number of nodes in the tree. The method visits each node
     *   exactly once to add its element to the snapshot.
     * <p>
     * Space Complexity:
     * - O(n), where n is the number of nodes in the tree. The method uses a list to store
     *   the elements of the tree, which requires O(n) space.
     *
     * @return an iterable collection of the tree's elements in reverse in-order
     */
    public Iterable<ObjectBST> inOrderReverse() {
        List<ObjectBST> snapshot = new ArrayList<>();
        if (root != null)
            inOrderSubtreeReverse(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot using reverse in-order traversal.
     *<p>
     * This is a helper method that performs the actual recursive traversal of the tree.
     * It adds the elements of the subtree rooted at the provided node to the given snapshot
     * in the reverse in-order order: right subtree, root, left subtree.
     *<p>
     * Time Complexity:
     * - O(n), where n is the number of nodes in the subtree. The method visits each node
     *   exactly once during the traversal.
     *<p>
     * Space Complexity:
     * - O(h), where h is the height of the tree. The space complexity is determined by the
     *   depth of the recursion stack. In the worst case (unbalanced tree), this could be O(n).
     *   In a balanced tree, this will be O(log n).
     *
     * @param node the root node of the subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtreeReverse(Node<ObjectBST> node, List<ObjectBST> snapshot) {
        if (node == null)
            return;

        inOrderSubtreeReverse(node.getRight(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtreeReverse(node.getLeft(), snapshot);
    }


    /**
     * Returns a map of nodes grouped by their level in the tree.
     * <p>
     * This method performs a level-order traversal (or breadth-first traversal) of the binary search tree (BST),
     * grouping the nodes by their respective levels. The result is returned as a map where the key is the level (an integer)
     * and the value is a list of nodes at that level. The levels start at 0, which corresponds to the root of the tree.
     *
     * Time Complexity:
     * - O(n), where n is the number of nodes in the tree. Each node is visited exactly once during the traversal.
     *
     * Space Complexity:
     * - O(n), where n is the number of nodes in the tree. The method uses a map to store the nodes grouped by their levels,
     *   and the map will hold all the nodes in the tree.
     *
     * @return a map where the key is the level and the value is a list of nodes at that level
     */
    public Map<Integer, List<ObjectBST>> nodesByLevel() {
        Map<Integer, List<ObjectBST>> nodesByLevel = new HashMap<>();

        if (root != null) {
            processBstByLevel(root, nodesByLevel, 0);
        }

        return nodesByLevel;
    }


    /**
     * Adds nodes of the subtree rooted at Node node to the result map, grouped by level.
     *
     * This is a helper method that performs a recursive traversal of the tree and groups the nodes
     * based on their levels. It updates the result map by adding the nodes at each level.
     *
     * Time Complexity:
     * - O(n), where n is the number of nodes in the subtree. The method visits each node exactly once.
     *
     * Space Complexity:
     * - O(h), where h is the height of the tree. The space complexity is determined by the depth of the recursion stack.
     *   In the worst case (unbalanced tree), this could be O(n). In a balanced tree, this would be O(log n).
     *
     * @param node   the root node of the subtree
     * @param result a map to store the nodes grouped by their level
     * @param level  the level of the current node
     */
    private void processBstByLevel(Node<ObjectBST> node, Map<Integer, List<ObjectBST>> result, int level) {
        if (node == null) {
            return;
        }

        result.putIfAbsent(level, new ArrayList<>());
        result.get(level).add(node.getElement());

        processBstByLevel(node.getLeft(), result, level + 1);
        processBstByLevel(node.getRight(), result, level + 1);
    }


//#########################################################################


    /**
     * Returns a string representation of the tree.
     * Draws the tree horizontally.
     *
     * Time Complexity: O(n), where n is the number of nodes.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    private void toStringRec(Node<ObjectBST> root, int level, StringBuilder sb) {
        if (root == null)
            return;
        toStringRec(root.getRight(), level + 1, sb);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++)
                sb.append("|\t");
            sb.append("|-------" + root.getElement() + "\n");
        } else
            sb.append(root.getElement() + "\n");
        toStringRec(root.getLeft(), level + 1, sb);
    }

}







