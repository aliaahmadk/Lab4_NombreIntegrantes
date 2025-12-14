package cat.udl.eps.ed.trees;


import java.util.Comparator;

public class LinkedBinarySearchTree<K, V> implements BinarySearchTree<K, V>,BinaryTree<Pair<K, V>> {


    private final Node<K, V> root;
    private final Comparator<? super K> comparator;

    private static class Node<K, V> {
        private final K key;
        private final V value;
        private final Node<K, V> left;
        private final Node<K, V> right;


        Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }


    public LinkedBinarySearchTree(Comparator<? super K> comparator) {
        this.comparator = comparator;
        this.root = null;

    }


    private LinkedBinarySearchTree(Comparator<? super K> comparator, Node<K, V> root) {
        this.comparator = comparator;
        this.root = root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {

        Node<K, V> present_node = root;

        while (present_node != null) {

            int comparation = comparator.compare(key, present_node.key); // We use comparator because it can be anything


            if (comparation == 0) {
                return present_node.value; // we found it

            //The search will be going till finding the key
            } else if (comparation < 0) {
                present_node = present_node.left;

            } else {
                present_node = present_node.right;
            }
        }
        return null;

    }

    @Override
    public LinkedBinarySearchTree<K, V> put(K key, V value) {

        Node<K, V> newRoot = put(root, key, value);
        return new LinkedBinarySearchTree<>(comparator, newRoot);

    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value, null, null);
        }

    //falta acabar


    }

    @Override
    public LinkedBinarySearchTree<K, V> remove(K key) {
        // ¿?
    }

    @Override
    public Pair<K, V> root(){
        // ¿?
    }

    @Override
    public LinkedBinarySearchTree<K, V> left() {
        // ¿?
    }
    @Override
    public LinkedBinarySearchTree<K, V> right() {
        // ¿?
    }




}




