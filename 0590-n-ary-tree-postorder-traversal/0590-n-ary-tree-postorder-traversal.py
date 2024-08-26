class Solution:
    def postorder(self, root: 'Node') -> List[int]:
        result = []
        def traverse(node):
            if node:
                for child in node.children:
                    traverse(child)
                result.append(node.val)
        traverse(root)
        return result
