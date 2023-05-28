class Solution {
    public int sumRootToLeaf(TreeNode root) {
        
        if(root==null){
            return 0;
        }
        int curr=0;
        
        return sumRootToLeafHelper(root,curr);
    }
    
    public int sumRootToLeafHelper(TreeNode root, int curr){
        
        if(root==null)return 0;
        
        curr=2*curr+root.val;
        
        if(root.left==null && root.right==null){
            return curr;
        }
      
        return sumRootToLeafHelper(root.left,curr)+sumRootToLeafHelper(root.right,curr);  
    }
}