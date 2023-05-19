class CBTInserter {
    TreeNode* root;
    int nodeCnt;
    int count(TreeNode* root) {
        if (!root) return 0;
        if (!root->left) root->right = NULL;
        return 1 + count(root->left) + count(root->right);
    }
public:
    CBTInserter(TreeNode* root) {
        this->root = root;
        this->nodeCnt = count(root);
    }
    int insert(int val) {
        int nextPos = this->nodeCnt + 1;
        int level = (int) log2(nextPos);
        int levelCnt = pow(2, level);
        int levelIdx = nextPos - levelCnt;

        TreeNode* cur = this->root;
        int curIdx = 0;

        for (int i = 0; i < level - 1; ++i) {
            if (levelIdx < levelCnt / 2) {
                cur = cur->left;
                curIdx = curIdx * 2;
            } else {
                cur = cur->right;
                curIdx = curIdx * 2 + 1;
                levelIdx -= (levelCnt / 2);
            }
            levelCnt /= 2;
        }
        if (!cur) return 0;
        TreeNode* newNode = new TreeNode(val);
        if (levelIdx < levelCnt / 2) {
            cur->left = newNode;
        } else {
            cur->right = newNode;
        }
        ++this->nodeCnt;
        return cur->val;
    }
    TreeNode* get_root() {
        return this->root;
    }
};