package cat.udl.eps.ed.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    private BinarySearchTree<Integer, String> emptyTree;
    private BinarySearchTree<Integer, String> tree;

    @BeforeEach
    void setUp() {
        Comparator<Integer> cmp = Comparator.naturalOrder();
        emptyTree = new LinkedBinarySearchTree<>(cmp);
        tree = emptyTree
                .put(8, "Ocho")
                .put(3, "Tres")
                .put(10, "Diez")
                .put(1, "Uno")
                .put(6, "Seis")
                .put(14, "Catorce")
                .put(4, "Cuatro")
                .put(7, "Siete")
                .put(13, "Trece");
    }

    @Test
    void testIsEmpty() {
        assertTrue(emptyTree.isEmpty());
        assertFalse(tree.isEmpty());
    }

    @Test
    void testGetAndContainsKey() {
        assertEquals("Ocho", tree.get(8));
        assertEquals("Uno", tree.get(1));
        assertEquals("Trece", tree.get(13));
        assertTrue(tree.containsKey(6));

        assertNull(tree.get(99));
        assertFalse(tree.containsKey(99));
    }

    @Test
    void testPutNewKey() {
        BinarySearchTree<Integer, String> newTree = tree.put(5, "Cinco");


        assertTrue(newTree.containsKey(5));
        assertEquals("Cinco", newTree.get(5));

        assertFalse(tree.containsKey(5));
        assertNull(tree.get(5));
    }

    @Test
    void testPutExistingKey() {
        BinarySearchTree<Integer, String> updatedTree = tree.put(8, "OCHO-MODIFICADO");

        assertEquals("OCHO-MODIFICADO", updatedTree.get(8));
        assertTrue(updatedTree.containsKey(8));
        assertEquals("Ocho", tree.get(8));
    }

    @Test
    void testRemoveLeafNode() {
        BinarySearchTree<Integer, String> newTree = tree.remove(1);

        assertFalse(newTree.containsKey(1));
        assertNull(newTree.get(1));
        assertTrue(tree.containsKey(1));
    }

    @Test
    void testRemoveNodeWithOneChild() {
        BinarySearchTree<Integer, String> newTree = tree.remove(10);

        assertFalse(newTree.containsKey(10));
        assertTrue(newTree.containsKey(14));
        assertTrue(tree.containsKey(10));
    }

    @Test
    void testRemoveNodeWithTwoChildren() {
        BinarySearchTree<Integer, String> newTree = tree.remove(3);

        assertFalse(newTree.containsKey(3));
        assertTrue(newTree.containsKey(1));
        assertTrue(newTree.containsKey(6));
        assertTrue(tree.containsKey(3));
    }
    
    @Test
    void testRemoveRoot() {
        BinarySearchTree<Integer, String> newTree = tree.remove(8);

        assertFalse(newTree.containsKey(8));
        assertEquals("Trece", newTree.get(13));
        assertTrue(tree.containsKey(8));
    }

    @Test
    void testRemoveNonExistentKey() {
        BinarySearchTree<Integer, String> newTree = tree.remove(99);
        assertSame(tree, newTree, "Removing a non-existent key should not change the tree");
    }
    
    @Test
    void testChainedOperations() {
        BinarySearchTree<Integer, String> finalTree = tree
                .put(20, "Veinte")
                .remove(3)
                .put(8, "OCHO-RE-MODIFICADO");

        assertFalse(finalTree.containsKey(3));
        assertTrue(finalTree.containsKey(20));
        assertEquals("OCHO-RE-MODIFICADO", finalTree.get(8));
        assertEquals("Ocho", tree.get(8));
    }
}



