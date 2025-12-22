
package cat.udl.eps.ed.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TraversalsTest {

    private LinkedBinarySearchTree<Integer, String> tree;

    @BeforeEach
    void setUp() {
        tree = new LinkedBinarySearchTree<>(Comparator.naturalOrder());
    }

    @Test
    void testRecursiveInorder() {
        tree = tree
                .put(20, "Root")
                .put(10, "Left")
                .put(30, "Right");

        List<Pair<Integer, String>> result = Traversals.inorder(tree);

        assertEquals(3, result.size());
        assertEquals(10, result.get(0).first());
        assertEquals(20, result.get(1).first());
        assertEquals(30, result.get(2).first());

    }

    @Test
    void testIterativeInorder() {

        tree = tree
                .put(50, "A").put(30, "B").put(70, "C").put(20, "D")
                .put(40, "E").put(60, "F").put(80, "G");

        List<Pair<Integer, String>> result = Traversals.inorderIterative(tree);

        int[] expectedKeys = {20, 30, 40, 50, 60, 70, 80};

        assertEquals(expectedKeys.length, result.size());

        for (int i = 0; i < expectedKeys.length; i++) {
            assertEquals(expectedKeys[i], result.get(i).first());
        }

    }

    @Test
    void testEmptyTree() {
        List<Pair<Integer, String>> result = Traversals.inorder(tree);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }

    @Test
    void testComparison() {

        tree = tree.put(100, "X").put(50, "Y").put(150, "Z").put(75, "W");

        List<Pair<Integer, String>> recResult = Traversals.inorder(tree);
        List<Pair<Integer, String>> iterResult = Traversals.inorderIterative(tree);

        // Check that they have the same amount of elements
        assertEquals(recResult.size(), iterResult.size(), "Both should have same length");

        // Compare more precisely
        for (int i = 0; i < recResult.size(); i++) {
            Pair<Integer, String> p1 = recResult.get(i);
            Pair<Integer, String> p2 = iterResult.get(i);

            //Indicates where it mismatched when initially both results should be equal
            assertEquals(p1, p2, "Mismatch at index " + i);
        }
    }


}