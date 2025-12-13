package cat.udl.eps.ed.trees;

public record Pair<F, S>(F first, S second) {}
        Así pues, LinkedBinarySearchTree<K, V> tendrá el siguiente aspecto:
public class LinkedBinarySearchTree<K, V>
        implements BinarySearchTree <K, V>,
        BinaryTree<Pair<K, V>> {

    //...
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

    //...
}
Y, e