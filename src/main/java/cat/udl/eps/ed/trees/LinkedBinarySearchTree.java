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

            int cmp = comparator.compare(key, present_node.key); // We use comparator because it can be anything


            if (cmp == 0) {
                return present_node.value; // we found it

            //The search will be going till finding the key
            } else if (cmp < 0) {
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
        int cmp = comparator.compare(key, node.key);

        if (cmp < 0) {
            // as the key is smaller, we go on the left side
            return new Node<>(node.key, node.value, put(node.left, key, value), node.right);
        } else if (cmp > 0) {
            // as the key is bigger, we go on the right side.
            return new Node<>(node.key, node.value, node.left, put(node.right, key, value));
        } else {
            // if already exists, simply replace.
            return new Node<>(key, value, node.left, node.right);
        }

    }

    @Override
    public LinkedBinarySearchTree<K, V> remove(K key) {
        Node<K, V> newRoot = remove(root, key);
        return new LinkedBinarySearchTree<>(comparator, newRoot);
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null; // Key not found
        }
        int cmp = comparator.compare(key, node.key);
        if (cmp < 0) {
            return new Node<>(node.key, node.value, remove(node.left, key), node.right);
        } else if (cmp > 0) {
            return new Node<>(node.key, node.value, node.left, remove(node.right, key));
        } else { // Key found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node<K, V> minNode = findMin(node.right);
            return new Node<>(minNode.key, minNode.value, node.left, removeMin(node.right));
        }
    }

    private Node<K, V> findMin(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<K, V> removeMin(Node<K, V> node) {
        if (node.left == null) {
            return node.right;
        }
        return new Node<>(node.key, node.value, removeMin(node.left), node.right);
    }

    @Override
    public Pair<K, V> root(){
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }
        return new Pair<>(root.key, root.value);
    }

    @Override
    public LinkedBinarySearchTree<K, V> left() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }
        return new LinkedBinarySearchTree<>(comparator, root.left);
    }
    @Override
    public LinkedBinarySearchTree<K, V> right() {
        if (isEmpty()) {
            throw new IllegalStateException("Tree is empty");
        }
        return new LinkedBinarySearchTree<>(comparator, root.right);
    }
}
