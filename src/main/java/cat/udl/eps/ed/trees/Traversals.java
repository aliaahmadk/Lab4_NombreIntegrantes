package cat.udl.eps.ed.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Traversals {

    public static <E> List<E> inorder(BinaryTree<E> tree) {
        List<E> result = new ArrayList<>();
        inorderRec(result, tree);
        return result;
    }

    private static <E> void inorderRec(List<E> result,
                                       BinaryTree<E> tree) {
        if (!tree.isEmpty()) {
            inorderRec(result, tree.left());
            result.add(tree.root());
            inorderRec(result, tree.right());
        }
    }

    public static <E> List<E> inorderIterative(BinaryTree<E> tree) {

        List<E> result = new ArrayList<>();
        Deque<BinaryTree<E>> stack = new ArrayDeque<>();
        BinaryTree<E> node = tree;

        while (!stack.isEmpty() || !node.isEmpty()) {

            while (!node.isEmpty()) {
                stack.push(node);
                node = node.left();
            }
            node = stack.pop();
            result.add(node.root());
            node = node.right();
        }
        return result;

    }
}

