class Solution {
 public:
  int minOperations(vector<int>& target, vector<int>& arr) {
    vector<int> indices;
    unordered_map<int, int> numToIndex;

    for (int i = 0; i < target.size(); ++i)
      numToIndex[target[i]] = i;

    for (const int a : arr)
      if (const auto it = numToIndex.find(a); it != numToIndex.end())
        indices.push_back(it->second);

    return target.size() - lengthOfLIS(indices);
  }

 private:
  // Same as 300. Longest Increasing Subsequence
  int lengthOfLIS(vector<int>& nums) {
    // tail[i] := the min tail of all increasing subseqs having length i + 1
    // It's easy to see that tail must be an increasing array.
    vector<int> tail;

    for (const int num : nums)
      if (tail.empty() || num > tail.back())
        tail.push_back(num);
      else
        tail[firstGreaterEqual(tail, num)] = num;

    return tail.size();
  }

 private:
  int firstGreaterEqual(const vector<int>& A, int target) {
    return lower_bound(A.begin(), A.end(), target) - A.begin();
  }
};
