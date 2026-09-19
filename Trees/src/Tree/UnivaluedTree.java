package Tree;

public class UnivaluedTree {
    public static boolean isUniValued(TreeNode root){
        if(root == null) return false;
        return traverse(root, root.val);
    }
    public static boolean traverse(TreeNode node, int target){
        if(node == null) return true;
        if(node.val!=target) return false;
        return traverse(node.left, target) && traverse(node.right, target);
    }
}