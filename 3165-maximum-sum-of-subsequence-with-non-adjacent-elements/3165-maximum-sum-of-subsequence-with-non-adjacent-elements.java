class SegmentTreeNode {
    int[][] s;
    int l;
    int r;
    SegmentTreeNode leftNode;
    SegmentTreeNode rightNode;
    SegmentTreeNode(int l, int r) {
        s = new int[2][2];
        this.l = l;
        this.r = r;
        leftNode = null;
        rightNode = null;
    }
    String getObjectiveString() {
        return String.format("[00]=%s, [01]=%s, [10]=%s, [11]=%s", s[0][0], s[0][1], s[1][0], s[1][1]);
    }
    @Override
    public String toString() {
        String lnodestr = "";
        String rnodestr = "";
        if (leftNode != null) {
            lnodestr = leftNode.toString();
        }
        if (rightNode != null) {
            rnodestr = rightNode.toString();
        }
        String mystr = String.format("l: %s, r: %s, obj: <%s>, {lNode: %s, rNode: %s}", l, r, getObjectiveString(), lnodestr, rnodestr);
        return mystr;
    }
}
class Solution {
    long MODULO = (long)(1e9 + 7);
    int max(long a, long b, long c) {
        return (int)(Math.max(a, Math.max(b, c)) % MODULO);
    }
    void setObjectives(SegmentTreeNode n, SegmentTreeNode l, SegmentTreeNode r) {
        n.s[1][1] = max(
            l.s[1][0] + r.s[0][1],
            l.s[1][1] + r.s[0][1],
            l.s[1][0] + r.s[1][1]
        );
        n.s[0][1] = max(
            l.s[0][0] + r.s[1][1],
            l.s[0][0] + r.s[0][1],
            l.s[0][1] + r.s[0][1]
        );
        n.s[1][0] = max(
            l.s[1][0] + r.s[0][0],
            l.s[1][0] + r.s[1][0],
            l.s[1][1] + r.s[0][0]
        );
        n.s[0][0] = max(
            l.s[0][0] + r.s[0][0],
            l.s[0][1] + r.s[0][0],
            l.s[0][0] + r.s[1][0]
        );
    }
    SegmentTreeNode buildTree(int l, int r, int[] nums) {
        if (l == r) {
            SegmentTreeNode node = new SegmentTreeNode(l, r);
            node.s[0][0] = 0;
            node.s[1][1] = nums[l];
            node.s[0][1] = -1000000;
            node.s[1][0] = -1000000;
            return node;
        }
        SegmentTreeNode leftNode = buildTree(l, (l+r)/2, nums);
        SegmentTreeNode rightNode = buildTree(((l+r)/2)+1, r, nums);
        SegmentTreeNode node = new SegmentTreeNode(l, r);
        node.leftNode = leftNode;
        node.rightNode = rightNode;
        setObjectives(node, leftNode, rightNode);
        return node;
    }
    void incrementalUpdate(SegmentTreeNode root, int pos, int value) {
        if (root.l == root.r && root.r == pos) {
            root.s[1][1] = value;
            return;
        }
        if (pos >= root.leftNode.l && pos <= root.leftNode.r) {
            incrementalUpdate(root.leftNode, pos, value);
        } else {
            incrementalUpdate(root.rightNode, pos, value);
        }
        setObjectives(root, root.leftNode, root.rightNode);
    }
    public int getMaxObjective(SegmentTreeNode n) {
        return Math.max(
            Math.max(n.s[0][0], n.s[0][1]),
            Math.max(n.s[1][0], n.s[1][1])
        );
    }
    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        SegmentTreeNode root = buildTree(0, nums.length-1, nums);
        long ans = 0;
        for (int[] q : queries) {
            incrementalUpdate(root, q[0], q[1]);
            ans += getMaxObjective(root) % MODULO;
        }
        return (int)(ans % MODULO);
    }
}