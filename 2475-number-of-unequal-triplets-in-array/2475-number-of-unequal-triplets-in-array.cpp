
class Solution {
 public:
  int unequalTriplets(vector<int>& nums) {
    int ans = 0;
    int prev = 0;
    int next = nums.size();
    unordered_map<int, int> count;

    for (const int num : nums)
      ++count[num];

    for (const auto& [_, freq] : count) {
      next -= freq;
      ans += prev * freq * next;
      prev += freq;
    }

    return ans;
  }
};