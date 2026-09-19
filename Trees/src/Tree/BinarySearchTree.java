package Tree;

public class BinarySearchTree {
    static TreeNode root;
    public void insert(int val){
        root = insertRecord(root, val);
    }
    public TreeNode insertRecord(TreeNode node, int val){
        if(node  == null){
            node = new TreeNode(val);
            return node;
        }
        if(val <= node.val){
            return insertRecord(node.left, val);
        } else {
            return insertRecord(node.right, val);
        }
    }
    public boolean search(TreeNode root, int val){
        if(root == null) return false;
        if(root.val == val) return true;
        if(val < root.val) return search(root.left, val);
        else return search(root.right, val);
    }
}