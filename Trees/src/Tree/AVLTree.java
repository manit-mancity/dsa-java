package Tree;

import java.util.*;

public class AVLTree<T extends Comparable<T>> {

    private static class Node<T> {
        T key;
        Node<T> left, right;
        int height;

        Node(T key) {
            this.key = key;
        }
    }

    private Node<T> root;

    public void insert(T key) {
        root = insert(root, key);
    }

    public void delete(T key) {
        root = delete(root, key);
    }

    public boolean contains(T key) {
        Node<T> cur = root;
        while (cur != null) {
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) return true;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return false;
    }

    public List<T> inOrder() {
        List<T> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    private void inOrder(Node<T> n, List<T> out) {
        if (n == null) return;
        inOrder(n.left, out);
        out.add(n.key);
        inOrder(n.right, out);
    }

    private int height(Node<T> n) {
        return (n == null) ? -1 : n.height;
    }

    private void updateHeight(Node<T> n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int balanceFactor(Node<T> n) {
        return height(n.left) - height(n.right);
    }

    private Node<T> rotateRight(Node<T> n) {
        Node<T> x = n.left;
        Node<T> y = x.right;

        // rotate
        x.right = n;
        n.left = y;

        updateHeight(n);
        updateHeight(x);
        return x;
    }

    private Node<T> rotateLeft(Node<T> n) {
        Node<T> x = n.right;
        Node<T> y = x.left;

        // rotate
        x.left = n;
        n.right = y;

        updateHeight(n);
        updateHeight(x);
        return x;
    }

    private Node<T> insert(Node<T> node, T key) {
        if (node == null) return new Node<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key);
        } else if (cmp > 0) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        return rebalance(node);
    }

    private Node<T> rebalance(Node<T> n) {
        updateHeight(n);
        int bf = balanceFactor(n);

        if (bf > 1) { // left heavy
            if (balanceFactor(n.left) < 0) {
                // LR case
                n.left = rotateLeft(n.left);
            }
            // LL
            return rotateRight(n);
        } else if (bf < -1) { // right heavy
            if (balanceFactor(n.right) > 0) {
                // RL case
                n.right = rotateRight(n.right);
            }
            // RR
            return rotateLeft(n);
        }
        return n;
    }

    private Node<T> delete(Node<T> node, T key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node<T> succ = minNode(node.right);
                node.key = succ.key;
                node.right = delete(node.right, succ.key);
            }
        }

        if (node == null) return null;

        return rebalance(node);
    }

    private Node<T> minNode(Node<T> n) {
        while (n.left != null) n = n.left;
        return n;
    }

    // MAIN METHOD
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] values = {20, 10, 30, 5, 15, 25, 35};

        // Insert values
        for (int val : values) {
            tree.insert(val);
        }

        System.out.println("Inorder before deletion:");
        List<Integer> elements = tree.inOrder();

        for(int val:elements) {
            System.out.print(val+" ");
        }

        System.out.println();
        // Delete node with predecessor logic
        System.out.println("Deleting 20 (has two children)...");
        tree.delete(20);

        System.out.println("Inorder after deletion:");
        elements = tree.inOrder();

        for(int val:elements) {
            System.out.print(val+" ");
        }
    }
}