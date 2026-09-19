package Tree;

class BalancedBinarytree {
    public static boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <=1 && isBalanced(root.left)
                && isBalanced(root.right);
    }

    private static int maxDepth(TreeNode root){
        if(root == null)
            return 0;
        return 1+ Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(TreeNode node){
        TreeNode one = new TreeNode(3);
        TreeNode two = new TreeNode(9);
        TreeNode three = new TreeNode(20);
        TreeNode four = new TreeNode(15);
        TreeNode five = new TreeNode(7);
        one.left = two;
        one.right = three;
        three.left = four;
        three.right = five;
        System.out.println(isBalanced(one));
    }
}