class Solution {
public:
  vector<int> getSneakyNumbers(vector<int>& nums) {
    int n = nums.size();
    vector<int> cnt(n - 2);
    for (auto e: nums)
      ++ cnt[e];
    vector<int> v;
    for (int i = 0; i < n - 2; i ++) if (cnt[i] == 2)
      v.push_back(i);
    return v;
  }
};