package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.BSTController;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class BinarySearchTreeTest {

    private BinarySearchTree binarySearchTree;
    private BinarySearchTree binarySearchTreeEmpty;
    private BSTController controller;

    /**
     * Initializes the test environment before each test case.
     * It sets up the BinarySearchTree instances for both a populated and an empty tree.
     */
    @BeforeEach
    void setUp() {
        controller = new BSTController();
        binarySearchTree = controller.getBinarySearchTree();
        binarySearchTreeEmpty = new BinarySearchTree();
    }

    /**
     * Test for checking the root of the BinarySearchTree.
     * Verifies that the root of a populated tree is not null,
     * and the root of an empty tree is null.
     */
    @Test
    void root() {
        System.out.println("Test root");
        BinarySearchTree.Node<ObjectBST> root1 =  binarySearchTree.getRoot();
        assertEquals(root1, binarySearchTree.getRoot());
        assertEquals(null, binarySearchTreeEmpty.getRoot());
    }

    /**
     * Test to check if the BinarySearchTree is empty.
     * Verifies that the isEmpty method returns the correct value
     * depending on whether the tree is empty or not.
     */
    @Test
    void isEmpty() {
        System.out.println("Test isEmpty");
        assertFalse(binarySearchTree.isEmpty());
        assertTrue(binarySearchTreeEmpty.isEmpty());
    }

    /**
     * Test for inserting items into the BinarySearchTree and finding them.
     * Verifies that inserted items can be found in the tree,
     * and non-inserted items cannot.
     */
    @Test
    void insertAndFind() {
        System.out.println("Test insert and find");
        Item newItem = new Item(new ID(111, TypeID.ITEM),"Door",new LinkedList<>());
        Item newItem1 = new Item(new ID(333, TypeID.ITEM),"Wheel",new LinkedList<>());
        newItem1.setQuantity(32);
        Item newItem2 = new Item(new ID(444, TypeID.ITEM),"Window",new LinkedList<>());
        newItem2.setQuantity(64);
        binarySearchTree.insert(newItem);
        binarySearchTree.insert(newItem1);
        binarySearchTree.insert(newItem2);

        Item itemNotFound = new Item(new ID(555, TypeID.ITEM),"screwdriver",new LinkedList<>());


        assertNotNull(binarySearchTree.find(binarySearchTree.root(),newItem));
        assertNotNull(binarySearchTree.find(binarySearchTree.root(),newItem1));
        assertNotNull(binarySearchTree.find(binarySearchTree.root(),newItem2));
        assertNull(binarySearchTree.find(binarySearchTree.root(),itemNotFound));
    }


    /**
     * Test for checking the size of the BinarySearchTree.
     * Verifies that the size method correctly tracks the number of elements in the tree,
     * including handling duplicates and inserts of the same item with different quantities.
     */
    @Test
    void size() {
        System.out.println("Test size");
        BinarySearchTree binarySearchTreeNew = new BinarySearchTree();
        assertEquals(0, binarySearchTreeNew.size());

        Item firstItem = new Item(new ID(232, TypeID.ITEM),"Window",new LinkedList<>());
        binarySearchTreeNew.insert(firstItem);
        assertEquals(1, binarySearchTreeNew.size());

        Item secondItem = new Item(new ID(333, TypeID.ITEM),"Window",new LinkedList<>());
        secondItem.setQuantity(64);
        binarySearchTreeNew.insert(secondItem);
        assertEquals(2, binarySearchTreeNew.size());

        Item sameQtdFirst = new Item(new ID(456, TypeID.ITEM),"Window",new LinkedList<>());
        binarySearchTreeNew.insert(sameQtdFirst);
        assertEquals(2, binarySearchTreeNew.size());
    }

    /**
     * Test for checking the height of the BinarySearchTree.
     * Verifies that the height method correctly calculates the height of the tree
     * after inserting nodes at different levels, including handling balanced trees.
     */
    @Test
    void height() {
        System.out.println("Test height");
        BinarySearchTree binarySearchTreeNew = new BinarySearchTree();
        assertEquals(-1, binarySearchTreeNew.height());

        Item firstItem = new Item(new ID(232, TypeID.ITEM),"Window",new LinkedList<>());
        binarySearchTreeNew.insert(firstItem);
        assertEquals(0, binarySearchTreeNew.height());
        Item secondItem = new Item(new ID(333, TypeID.ITEM),"Window",new LinkedList<>());
        secondItem.setQuantity(64);
        binarySearchTreeNew.insert(secondItem);
        assertEquals(1, binarySearchTreeNew.height());

        Item thirdItem = new Item(new ID(456, TypeID.ITEM),"Window",new LinkedList<>());
        thirdItem.setQuantity(64);
        binarySearchTreeNew.insert(thirdItem);
        assertEquals(1, binarySearchTreeNew.height());

        Item fourthItem = new Item(new ID(456, TypeID.ITEM),"Window",new LinkedList<>());
        fourthItem.setQuantity(2);
        binarySearchTreeNew.insert(fourthItem);
        assertEquals(2, binarySearchTreeNew.height());
    }

    /**
     * Test for retrieving the smallest element from the BinarySearchTree.
     * Verifies that the smallest element is correctly identified based on the item’s quantity.
     */
    @Test
    void smallestElement() {
        System.out.println("Test smallest element");

        ObjectBST samllests = binarySearchTree.smallestElement();
        System.out.println(samllests);

        ObjectBST smallestObj = new ObjectBST(new HashMap<>(),0.0576F);
        ItemRepository itemRepository = Repositories.getInstance().getItemRepository();
        ID search = new ID(1015, TypeID.ITEM);
        Item item = itemRepository.getMapItemList().get(search);
        smallestObj.getItemsWithQuantity().put(item.getItemID(),item);
        System.out.println();

        assertNotNull(samllests);
        assertEquals(smallestObj,samllests);
    }

    /**
     * Test for in-order traversal of the BinarySearchTree.
     * Verifies that the elements are traversed in ascending order of their quantity.
     */
    @Test
    void inOrder() {
        System.out.println("Test inOrder");
        List<ObjectBST> list = (List<ObjectBST>) binarySearchTree.inOrder();
        for(int i = 1; i < list.size();i++ ){
            assertTrue(list.get(i-1).getQuantityOfElements() < list.get(i).getQuantityOfElements());
        }
    }

    /**
     * Test for in-order traversal of the BinarySearchTree.
     * Verifies that the elements are traversed in ascending order of their quantity.
     */
    @Test
    void inOrderReverse() {
        List<ObjectBST> list = (List<ObjectBST>) binarySearchTree.inOrderReverse();
        for(int i = 1; i < list.size();i++ ){
            assertTrue(list.get(i-1).getQuantityOfElements() > list.get(i).getQuantityOfElements());
        }
    }

    /**
     * Test for checking the nodes at each level of the BinarySearchTree.
     * Verifies that the method returns the correct nodes by level.
     */
    @Test
    void nodesByLevel() {
        System.out.println("Test nodesByLevel");
        Map<Integer, List<ObjectBST>> map = binarySearchTree.nodesByLevel();
        int altura = 0;

        for (Map.Entry<Integer, List<ObjectBST>> entry : map.entrySet()) {
            assertEquals(altura, entry.getKey());
            altura++;
        }
    }

    /**
     * Test for the toString method of the BinarySearchTree.
     * Verifies that the string representation of the tree is not null
     * for a populated tree and is an empty string for an empty tree.
     */
    @Test
    void testToString() {
        System.out.println("Test toString");
        String string = binarySearchTree.toString();
        assertNotNull(string);
        String string1 = binarySearchTreeEmpty.toString();
        assertEquals("", string1);
    }

    /**
     * Test for the extended remove functionality.
     * Verifies different removal cases, including:
     * - Removing a leaf node
     * - Removing a node with one child
     * - Removing a node with two children
     * - Trying to remove a non-existent node
     */
    @Test
    void testRemoveExtended() {
        System.out.println("Test remove extended");

        // Case 1: Remove a leaf node
        System.out.println("Removing a leaf node...");
        Item leafItem = new Item(new ID(999, TypeID.ITEM), "Leaf", new LinkedList<>());
        leafItem.setQuantity(10);
        binarySearchTree.insert(leafItem);
        int initialSize = binarySearchTree.size();
        binarySearchTree.remove(new ObjectBST(new HashMap<>(), 10F));
        assertEquals(initialSize - 1, binarySearchTree.size());
        assertNull(binarySearchTree.find(binarySearchTree.root(), leafItem));

        // Case 2: Remove a node with one child
        System.out.println("Removing a node with one child...");
        Item singleChildItem = new Item(new ID(888, TypeID.ITEM), "SingleChild", new LinkedList<>());
        singleChildItem.setQuantity(50);
        binarySearchTree.insert(singleChildItem);
        Item childItem = new Item(new ID(887, TypeID.ITEM), "Child", new LinkedList<>());
        childItem.setQuantity(45);
        binarySearchTree.insert(childItem);

        initialSize = binarySearchTree.size();
        binarySearchTree.remove(new ObjectBST(new HashMap<>(), 50F));
        assertEquals(initialSize - 1, binarySearchTree.size());
        assertNull(binarySearchTree.find(binarySearchTree.root(), singleChildItem));
        assertNotNull(binarySearchTree.find(binarySearchTree.root(), childItem)); // Ensure child remains in the tree

        // Case 3: Remove a node with two children
        System.out.println("Removing a node with two children...");
        Item twoChildrenItem = new Item(new ID(777, TypeID.ITEM), "TwoChildren", new LinkedList<>());
        twoChildrenItem.setQuantity(100);
        binarySearchTree.insert(twoChildrenItem);
        Item leftChild = new Item(new ID(776, TypeID.ITEM), "LeftChild", new LinkedList<>());
        leftChild.setQuantity(90);
        binarySearchTree.insert(leftChild);
        Item rightChild = new Item(new ID(778, TypeID.ITEM), "RightChild", new LinkedList<>());
        rightChild.setQuantity(110);
        binarySearchTree.insert(rightChild);

        initialSize = binarySearchTree.size();
        binarySearchTree.remove(new ObjectBST(new HashMap<>(), 100F));
        assertEquals(initialSize - 1, binarySearchTree.size());
        assertNull(binarySearchTree.find(binarySearchTree.root(), twoChildrenItem));
        assertNotNull(binarySearchTree.find(binarySearchTree.root(), leftChild)); // Ensure left child remains
        assertNotNull(binarySearchTree.find(binarySearchTree.root(), rightChild)); // Ensure right child remains

        // Case 4: Try to remove a non-existent element
        System.out.println("Removing a non-existent node...");
        initialSize = binarySearchTree.size();
        binarySearchTree.remove(new ObjectBST(new HashMap<>(), 9999F)); // Element with quantity 9999 does not exist
        assertEquals(initialSize, binarySearchTree.size()); // Size should remain unchanged
    }


}