package cat.udl.eps.ed.trees;


public interface BinaryTree<E> {
    boolean isEmpty();
    E root();
    BinaryTree<E> left();
    BinaryTree<E> right();
}