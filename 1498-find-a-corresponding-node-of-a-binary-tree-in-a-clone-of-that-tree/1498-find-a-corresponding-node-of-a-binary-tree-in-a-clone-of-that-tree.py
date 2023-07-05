class Solution:
    def getTargetCopy(
        self, original: TreeNode, cloned: TreeNode, target: TreeNode
    ) -> TreeNode:
        res = None

        def dfs(original, cloned):
            nonlocal res
            if cloned is None:
                return
            if original == target:
                res = cloned
                return
            dfs(original.left, cloned.left)
            dfs(original.right, cloned.right)

        dfs(original, cloned)
        return res