package pt.ipp.isep.dei.esoft.project.domain.TreeClasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.BSTController;

import static org.junit.jupiter.api.Assertions.*;
class BinarySearchTreeTest {

    private BinarySearchTree binarySearchTree;
    private BinarySearchTree binarySearchTreeEmpty;
    private BSTController controller;


    @BeforeEach
    void setUp() {
        controller = new BSTController();
        binarySearchTree = controller.getBinarySearchTree();
        binarySearchTreeEmpty = new BinarySearchTree();
    }

    @Test
    void root() {
        System.out.println("Test root");
        BinarySearchTree.Node<ObjectBST> root1 =  binarySearchTree.getRoot();
        assertEquals(root1, binarySearchTree.getRoot());
        assertEquals(null, binarySearchTreeEmpty.getRoot());
    }

    @Test
    void isEmpty() {
        System.out.println("Test isEmpty");
        assertFalse(binarySearchTree.isEmpty());
        assertTrue(binarySearchTreeEmpty.isEmpty());

    }

    @Test
    void find() {
    }

    @Test
    void insert() {
    }

    @Test
    void remove() {
    }

    @Test
    void size() {
    }

    @Test
    void height() {
    }

    @Test
    void testHeight() {
    }

    @Test
    void smallestElement() {
    }

    @Test
    void testSmallestElement() {
    }

    @Test
    void inOrder() {
    }

    @Test
    void inOrderReverse() {
    }

    @Test
    void preOrder() {
    }

    @Test
    void posOrder() {
    }

    @Test
    void nodesByLevel() {
    }

    @Test
    void testToString() {
    }
}