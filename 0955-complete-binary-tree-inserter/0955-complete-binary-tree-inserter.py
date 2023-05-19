class CBTInserter:
    def __init__(self, root: Optional[TreeNode]):
        self.root = root
        self.deque = deque()
        queue = deque([root])
        while queue:
            node = queue.popleft()
            if not node.left or not node.right:
                self.deque.append(node)
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        
    def insert(self, val: int) -> int:
        new_node = TreeNode(val)
        parent = self.deque[0]
        if not parent.left:
            parent.left = new_node
        else:
            parent.right = new_node
            self.deque.popleft()
        self.deque.append(new_node)
        return parent.val

    def get_root(self) -> Optional[TreeNode]:
        return self.root