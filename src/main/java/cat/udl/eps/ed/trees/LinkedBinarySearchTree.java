package cat.udl.eps.ed.trees;
import java.util.Comparator;

public class LinkedBinarySearchTree<K, V> implements BinarySearchTree<K, V> {


    private final Node<K, V> root;
    private final Comparator<? super K> comparator;

    private static class Node<K, V> {
        private final K key;
        private final V value;
        private final Node<K, V> left;
        private final Node<K, V> right;
        // ¿?
    }


    public LinkedBinarySearchTree(Comparator<? super K> comparator) {

    }


    private LinkedBinarySearchTree(Comparator<? super K> comparator,
                                   Node<K, V> root) {
        // ¿?
    }

    @Override
    public boolean isEmpty() {
        // ¿?
    }

    @Override
    public boolean containsKey(K key) {
        // ¿?
    }

    @Override
    public V get(K key) {
        // ¿?
    }

    @Override
    public LinkedBinarySearchTree<K, V> put(K key, V value) {
        // ¿?
    }

    @Override
    public LinkedBinarySearchTree<K, V> remove(K key) {
        // ¿?
    }
}


