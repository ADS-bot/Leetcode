int __FAST_IO__ = []() { std::ios::sync_with_stdio(0); std::cin.tie(0); std::cout.tie(0); return 0; }();

class Solution {
public:
    int maxFrequency(vector<int>& nums, int k, int numOperations) {
        sort(nums.begin(), nums.end());
        int n = nums.size(), ret = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            while (nums[i] - nums[j] > 2 * k) ++j;
            ret = max(ret, min(i - j + 1, numOperations));
        }

        for (int i = 0, l = 0, r = 0; i < n; ++i) {
            int last_i = i;
            while (i + 1 < n && nums[i] == nums[i + 1]) i++;
            while (r < n && nums[r] <= nums[i] + k) ++r;
            while (nums[i] > nums[l] + k) ++l;

            int same = i - last_i + 1;
            int len = r - l;
            ret = max(ret, same + min(len - same, numOperations));
        }
        return ret;
    }
};