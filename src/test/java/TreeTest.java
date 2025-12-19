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
        // Test keys that exist
        assertEquals("Ocho", tree.get(8));
        assertEquals("Uno", tree.get(1));
        assertEquals("Trece", tree.get(13));
        assertTrue(tree.containsKey(6));

        // Test key that does not exist
        assertNull(tree.get(99));
        assertFalse(tree.containsKey(99));
    }

    @Test
    void testPutNewKey() {
        BinarySearchTree<Integer, String> newTree = tree.put(5, "Cinco");

        // Check new key exists in the new tree
        assertTrue(newTree.containsKey(5));
        assertEquals("Cinco", newTree.get(5));

        // Check original tree is not modified (immutability)
        assertFalse(tree.containsKey(5));
        assertNull(tree.get(5));
    }

    @Test
    void testPutExistingKey() {
        BinarySearchTree<Integer, String> updatedTree = tree.put(8, "OCHO-MODIFICADO");

        // Check the value is updated in the new tree
        assertEquals("OCHO-MODIFICADO", updatedTree.get(8));
        assertTrue(updatedTree.containsKey(8));

        // Check original tree is not modified (immutability)
        assertEquals("Ocho", tree.get(8));
    }

    @Test
    void testRemoveLeafNode() {
        BinarySearchTree<Integer, String> newTree = tree.remove(1); // Node with no children

        assertFalse(newTree.containsKey(1));
        assertNull(newTree.get(1));
        assertTrue(tree.containsKey(1)); // Immutability check
    }

    @Test
    void testRemoveNodeWithOneChild() {
        BinarySearchTree<Integer, String> newTree = tree.remove(10); // Node with one child (14)

        assertFalse(newTree.containsKey(10));
        assertTrue(newTree.containsKey(14)); // Check child is still there
        assertTrue(tree.containsKey(10)); // Immutability check
    }

    @Test
    void testRemoveNodeWithTwoChildren() {
        BinarySearchTree<Integer, String> newTree = tree.remove(3); // Node with two children (1, 6)

        assertFalse(newTree.containsKey(3));
        assertTrue(newTree.containsKey(1)); // Check children's context
        assertTrue(newTree.containsKey(6));
        assertTrue(tree.containsKey(3)); // Immutability check
    }
    
    @Test
    void testRemoveRoot() {
        BinarySearchTree<Integer, String> newTree = tree.remove(8); // Root node

        assertFalse(newTree.containsKey(8));
        assertEquals("Trece", newTree.get(13)); // A successor should be the new root
        assertTrue(tree.containsKey(8)); // Immutability check
    }

    @Test
    void testRemoveNonExistentKey() {
        BinarySearchTree<Integer, String> newTree = tree.remove(99);
        // The tree should be identical to the original
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
        assertEquals("Ocho", tree.get(8)); // Immutability check on original
    }
}
