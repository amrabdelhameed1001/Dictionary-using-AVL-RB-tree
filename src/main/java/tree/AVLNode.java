package tree;

public class AVLNode<T extends Comparable<T>> {
    T key;
    int height;
    AVLNode<T> left, right;

    public AVLNode(T key) {
        this.key = key;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}
