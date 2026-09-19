package AdvancedTopics;

import java.util.ArrayList;
import java.util.List;

public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static final class Node {
        int key;
        boolean color; // RED or BLACK
        Node left, right, parent;

        Node(int key, boolean color) {
            this.key = key;
            this.color = color;
        }
    }

    // Single shared BLACK sentinel for all null children
    private final Node NIL = new Node(0, BLACK);
    private Node root = NIL;

    public RedBlackTree() {
        NIL.left = NIL.right = NIL.parent = NIL;
    }

    /** Insert a key (duplicates ignored). O(log n). */
    public void insert(int key) {
        Node node = new Node(key, RED); // 5, R
        node.left = node.right = node.parent = NIL;

        Node parent = NIL;
        Node cur = root;

        // Standard BST descent
        while (cur != NIL) {
            parent = cur;
            if (key == cur.key)
                return; // ignore duplicates
            cur = (key < cur.key) ? cur.left : cur.right;
        }

        // Link under parent
        node.parent = parent;

        if (parent == NIL) {
            // Empty tree: new node becomes root — make it BLACK explicitly
            root = node;
            node.color = BLACK; // ← explicit node.color = BLACK
            return;
        } else if (key < parent.key) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        // Restore RB properties if parent was RED
        insertFixup(node);
    }

    /** RB insert fix-up (CLRS cases). */
    private void insertFixup(Node node) {
        while (node.parent.color == RED) {
            boolean parentIsLeft = (node.parent == node.parent.parent.left);
            Node grandparent = node.parent.parent;
            Node uncle = parentIsLeft ? grandparent.right : grandparent.left;

            if (uncle.color == RED) {
                // Case 1: parent & uncle RED → recolor & climb
                node.parent.color = BLACK;
                uncle.color = BLACK;
                grandparent.color = RED;
                node = grandparent; // climb up and continue
            } else {
                // Uncle BLACK: shape decides Case 2 then Case 3
                if (parentIsLeft && node == node.parent.right) {
                    // Left–Right (triangle) → rotate at parent
                    // to make it a line
                    node = node.parent;
                    rotateLeft(node);
                } else if (!parentIsLeft && node == node.parent.left) {
                    // Right–Left (triangle) → rotate at parent
                    node = node.parent;
                    rotateRight(node);
                }

                // Case 3 (line): rotate at grandparent, recolor
                node.parent.color = BLACK; // new top of subtree
                grandparent.color = RED; // pushed down
                if (parentIsLeft) {
                    rotateRight(grandparent);
                } else {
                    rotateLeft(grandparent);
                }
            }
        }
        // Always ensure root is BLACK
        root.color = BLACK;
    }

    // ---------- Rotations ----------
    private void rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        if (x.left != NIL) {
            x.left.parent = node;
        }
        x.parent = node.parent;
        if (node.parent == NIL) {
            root = x;
        } else if (node == node.parent.left) {
            node.parent.left = x;
        } else {
            node.parent.right = x;
        }
        x.left = node;
        node.parent = x;
    }

    private void rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        if (x.right != NIL) {
            x.right.parent = node;
        }
        x.parent = node.parent;
        if (node.parent == NIL) {
            root = x; // deletion of root
        } else if (node == node.parent.left) {
            node.parent.left = x;
        } else {
            node.parent.right = x;
        }

        x.right = node;
        node.parent = x;
    }

    // ---------- Public utilities ----------
    public boolean contains(int key) {
        Node cur = root;
        while (cur != NIL) {
            if (key == cur.key)
                return true;
            cur = (key < cur.key) ? cur.left : cur.right;
        }
        return false;
    }

    public List<Integer> inOrder() {
        List<Integer> out = new ArrayList<>();
        inOrder(root, out);
        return out;
    }

    public int heightEdges() {
        return heightEdges(root);
    }

    // ---------- Helpers ----------
    private void inOrder(Node n, List<Integer> out) {
        if (n == NIL)
            return;
        inOrder(n.left, out);
        out.add(n.key);
        inOrder(n.right, out);
    }

    private int heightEdges(Node n) {
        if (n == NIL)
            return -1;
        return 1 + Math.max(heightEdges(n.left), heightEdges(n.right));
    }

    // Quick sanity checks (optional for class)
    public boolean validateRootBlack() {
        return root == NIL || root.color == BLACK;
    }

    public boolean validateNoRedRed() {
        return noRedRed(root);
    }

    private boolean noRedRed(Node n) {
        if (n == NIL)
            return true;
        if (n.color == RED && (n.left.color == RED || n.right.color == RED))
            return false;
        return noRedRed(n.left) && noRedRed(n.right);
    }

    // Demo
    public static void main(String[] args) {
        RedBlackTree t = new RedBlackTree();
        int[] keys = { 10, 5, 15, 12, 3, 7, 18, 1, 6 };
        for (int k : keys)
            t.insert(k);

        System.out.println("In-order: " + t.inOrder());
        System.out.println("Contains 12? " + t.contains(12));
        System.out.println("Height (edges): " + t.heightEdges());
        System.out.println("Root black? " + t.validateRootBlack());
        System.out.println("No red-red? " + t.validateNoRedRed());
    }
}