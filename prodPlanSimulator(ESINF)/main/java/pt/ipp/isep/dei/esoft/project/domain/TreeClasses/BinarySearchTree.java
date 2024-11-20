package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import pt.ipp.isep.dei.esoft.project.domain.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author DEI-ESINF
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

    protected Node<ObjectBST> root = null;     // root of the tree


    /* Constructs an empty binary search tree. */
    public BinarySearchTree() {
        root = null;
    }

    public Node<ObjectBST> getRoot() {
        return root;
    }

    /*
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<ObjectBST> root() {
        return root;
    }

    /*
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element the element to find
     * @return the Node that contains the Element, or null otherwise
     * >
     * This method despite not being essential is very useful.
     * It is written here in order to be used by this class and its
     * subclasses avoiding recoding.
     * So its access level is protected
     */
    protected Node<ObjectBST> find(Node<ObjectBST> node, ObjectBST element) {
        if (node == null) {
            return null;
        }

        if (node.getElement().equals(element)) {
            return node;
        }

        if (node.getElement().compareTo(element) > 0) {
            return find(node.getLeft(), element);
        } else {
            return find(node.getRight(), element);
        }
    }

    /*
     * Inserts an element in the tree.
     */
    public void insert(Item item) {
        root = insert(item, root());
    }

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
     * Removes an element from the tree maintaining its consistency as a Binary Search Tree.
     */
    public void remove(ObjectBST element) {
        root = remove(element, root());
    }

    private Node<ObjectBST> remove(ObjectBST element, Node<ObjectBST> node) {

        if (node == null) {
            return null;    //throw new IllegalArgumentException("Element does not exist");
        }
        if (element.compareTo(node.getElement()) == 0) {
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
        } else if (element.compareTo(node.getElement()) < 0)
            node.setLeft(remove(element, node.getLeft()));
        else
            node.setRight(remove(element, node.getRight()));

        return node;
    }

    /*
     * Returns the number of nodes in the tree.
     * @return number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

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

    /*
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
     * Returns the smallest element within the tree.
     *
     * @return the smallest element within the tree
     */
    public ObjectBST smallestElement() {
        return smallestElement(root());
    }

    protected ObjectBST smallestElement(Node<ObjectBST> node) {
        if (node.getLeft() == null) {
            return node.getElement();
        }
        return smallestElement(node.getLeft());
    }

    /*
     * Returns an iterable collection of elements of the tree, reported in in-order.
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<ObjectBST> inOrder() {
        List<ObjectBST> snapshot = new ArrayList<>();
        if (root != null)
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an in-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(Node<ObjectBST> node, List<ObjectBST> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }



    /*
     * Returns an iterable collection of elements of the tree, reported in in-order.
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<ObjectBST> inOrderReverse() {
        List<ObjectBST> snapshot = new ArrayList<>();
        if (root != null)
            inOrderSubtreeReverse(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an in-order traversal
     *
     * @param node     Node serving as the root of a subtree
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
     * Returns an iterable collection of elements of the tree, reported in pre-order.
     *
     * @return iterable collection of the tree's elements reported in pre-order
     */
    public Iterable<ObjectBST> preOrder() {
        List<ObjectBST> snapshot = new ArrayList<>();
        if (root != null)
            preOrderSubtree(root, snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given
     * snapshot using an pre-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void preOrderSubtree(Node<ObjectBST> node, List<ObjectBST> snapshot) {
        if (node == null)
            return;
        snapshot.add(node.getElement());
        preOrderSubtree(node.getLeft(), snapshot);
        preOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in post-order.
     *
     * @return iterable collection of the tree's elements reported in post-order
     */
    public Iterable<ObjectBST> posOrder() {
        List<ObjectBST> snapshot = new ArrayList<>();
        if(root != null) {
            posOrderSubtree(root, snapshot);
        }
        return snapshot;
    }

    /**
     * Adds positions of the subtree rooted at Node node to the given
     * snapshot using an post-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void posOrderSubtree(Node<ObjectBST> node, List<ObjectBST> snapshot) {
        if(node == null){
            return;
        }
        posOrderSubtree(node.getLeft(), snapshot);
        posOrderSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());
    }

    /*
     * Returns a map with a list of nodes by each tree level.
     * @return a map with a list of nodes by each tree level
     */
    public Map<Integer, List<ObjectBST>> nodesByLevel() {
        Map<Integer, List<ObjectBST>> nodesByLevel = new HashMap<>();

        if (root != null) {
            processBstByLevel(root, nodesByLevel, 0);
        }

        return nodesByLevel;
    }

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
     * Draw the tree horizontally
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







