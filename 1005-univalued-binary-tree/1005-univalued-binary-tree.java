/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int val = -1;
    public boolean isUnivalTree(TreeNode root) {
        if(root == null){
            return true;
        }
        if(val == -1){
            val = root.val;
        }
        if(val != root.val){
            return false;
        }
        if(root.left == null && root.right == null){
            return true;
        }
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }
}