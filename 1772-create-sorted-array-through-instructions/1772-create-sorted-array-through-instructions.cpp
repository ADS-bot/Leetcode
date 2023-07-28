template <typename T>
class RangeQuery {
 public:
  virtual void update(int index, int val);
  virtual T query(int i, int j);

 private:
  virtual T merge(T a, T b);

 protected:
  int left(int treeIndex) {
    return 2 * treeIndex + 1;
  }

  int right(int treeIndex) {
    return 2 * treeIndex + 2;
  }
};

class SegmentTree : RangeQuery<int> {
 public:
  explicit SegmentTree(int n) : n(n), tree(4 * n) {}

  // Updates nums[i] to val equivalently.
  void update(int i, int val) override {
    update(0, 0, n - 1, i, val);
  }

  // Returns sum(nums[i..j]).
  int query(int i, int j) override {
    return query(0, 0, n - 1, i, j);
  }

 private:
  const int n;       // size of the input array
  vector<int> tree;  // segment tree

  void update(int treeIndex, int lo, int hi, int i, int val) {
    if (lo == i && hi == i) {
      tree[treeIndex] += val;
      return;
    }
    const int mid = (lo + hi) / 2;
    const int leftTreeIndex = left(treeIndex);
    const int rightTreeIndex = right(treeIndex);
    if (i <= mid)
      update(leftTreeIndex, lo, mid, i, val);
    else
      update(rightTreeIndex, mid + 1, hi, i, val);
    tree[treeIndex] = merge(tree[leftTreeIndex], tree[rightTreeIndex]);
  }

  int query(int treeIndex, int lo, int hi, int i, int j) {
    // [lo, hi] lies completely inside [i, j].
    if (i <= lo && hi <= j)
      return tree[treeIndex];
    // [lo, hi] lies completely outside [i, j].
    if (j < lo || hi < i)
      return 0;
    const int mid = (lo + hi) / 2;
    return merge(query(left(treeIndex), lo, mid, i, j),
                 query(right(treeIndex), mid + 1, hi, i, j));
  }

  // Merges the result of left node and right node.
  int merge(int a, int b) override {
    return a + b;
  }
};

class Solution {
 public:
  int createSortedArray(vector<int>& instructions) {
    constexpr int kMod = 1'000'000'007;
    const int max = *max_element(instructions.begin(), instructions.end());
    int ans = 0;
    SegmentTree tree(max + 1);

    for (const int i : instructions) {
      ans += min(tree.query(0, i - 1), tree.query(i + 1, max));
      ans %= kMod;
      tree.update(i, 1);
    }

    return ans;
  }
};
