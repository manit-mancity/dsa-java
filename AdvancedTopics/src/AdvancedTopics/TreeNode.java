package AdvancedTopics;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    // Constructors
    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // PreOrder Traversal (Root → Left → Right)
    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // InOrder Traversal (Left → Root → Right)
    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    // PostOrder Traversal (Left → Right → Root)
    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }

    // Main method
    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);

        // Construct tree
        one.left = two;
        one.right = three;
        two.left = five;
        two.right = four;

        TreeNode root = one;

        // Create object to call traversal methods
        TreeNode obj = new TreeNode();

        System.out.println("Pre Order Traversal: ");
        obj.preOrder(root);
        System.out.println();

        System.out.println("In Order Traversal: ");
        obj.inOrder(root);
        System.out.println();

        System.out.println("Post Order Traversal: ");
        obj.postOrder(root);
        System.out.println();
    }
}
