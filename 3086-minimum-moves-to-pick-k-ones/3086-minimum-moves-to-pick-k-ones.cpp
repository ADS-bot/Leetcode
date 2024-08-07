class Solution {
 public:
  long long minimumMoves(vector<int>& nums, int k, int maxChanges) {
    constexpr int kNumOfIndicesWithinOneDistance = 3;
    long ans = LONG_MAX;
    vector<int> oneIndices;  // the indices of 1s
    vector<long> prefix{0};  // the accumulated indices of 1s

    for (int i = 0; i < nums.size(); ++i)
      if (nums[i] == 1)
        oneIndices.push_back(i);

    for (const int oneIndex : oneIndices)
      prefix.push_back(prefix.back() + oneIndex);

    const int minOnesByTwo = max(0, k - maxChanges);
    const int maxOnesByTwo =
        min({k, minOnesByTwo + kNumOfIndicesWithinOneDistance,
             static_cast<int>(oneIndices.size())});

    for (int onesByTwo = minOnesByTwo; onesByTwo <= maxOnesByTwo; ++onesByTwo)
      for (int l = 0; l + onesByTwo < prefix.size(); ++l) {
        const int r = l + onesByTwo;  // Collect 1s in oneIndices[l - 1..r - 1].
        const long cost1 = (k - onesByTwo) * 2;
        const long cost2 = (prefix[r] - prefix[(l + r) / 2]) -
                           (prefix[(l + r + 1) / 2] - prefix[l]);
        ans = min(ans, cost1 + cost2);
      }

    return ans;
  }
};