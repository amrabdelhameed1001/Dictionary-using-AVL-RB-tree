import java.util.*;

public class AVLTree<K extends Comparable<K>> {
    private class Node {
        K key;
        int height;
        Node left, right;

        Node(K key) {
            this.key = key;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean insert(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Null key not allowed");
        }

        if (search(key)) {
            return false;
        }

        root = insert(root, key);
        size++;
        return true;
    }

    private Node insert(Node node, K key) {
        if (node == null) {
            return new Node(key);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balance(node);

        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public boolean delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Null key not allowed");
        }

        if (!search(key)) {
            return false;
        }

        root = delete(root, key);
        size--;
        return true;
    }
    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node temp = findMin(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        }

        if (node == null) {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balance(node);

        if (balance > 1 && balance(node.left) >= 0) {
            return rightRotate(node);
        }

        if (balance > 1 && balance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && balance(node.right) <= 0) {
            return leftRotate(node);
        }

        if (balance < -1 && balance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public boolean search(K key) {
        return search(root, key);
    }

    private boolean search(Node node, K key) {
        if (node == null) {
            return false;
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    public int size() {
        return size;
    }

    private int balance(Node node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }


    private Node rightRotate(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));

        return newRoot;
    }

    private Node leftRotate(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));

        return newRoot;
    }



    private Node findMin(Node node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        }

        return findMin(node.left);
    }


}
