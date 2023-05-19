class CBTInserter {
	private Queue<TreeNode> queue;
	private TreeNode root;

	public CBTInserter(TreeNode root) {
		this.root = root;
		this.queue = new LinkedList<>();
		this.queue.offer(this.root);
		while (this.queue.peek().left != null && this.queue.peek().right != null) {
			TreeNode treeNode = this.queue.poll();
			this.queue.offer(treeNode.left);
			this.queue.offer(treeNode.right);
		}
		if (this.queue.peek().left != null) {
			this.queue.offer(this.queue.peek().left);
		}
	}
	public int insert(int val) {
		TreeNode treeNode = this.queue.peek();
		if (treeNode.left == null) {
			treeNode.left = new TreeNode(val);
			this.queue.offer(treeNode.left);
		} else {
			treeNode.right = new TreeNode(val);
			this.queue.poll();
			this.queue.offer(treeNode.right);
		}
		return treeNode.val;
	}
	public TreeNode get_root() {
		return this.root;
	}
}