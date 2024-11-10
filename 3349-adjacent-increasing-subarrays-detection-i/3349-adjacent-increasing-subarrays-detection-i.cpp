class Solution {
public:
    vector<int>dp;
    int n;
    bool check(int m) {
        for (int i = 0; i < n - m; i++) {
            if (dp[i] >= m && dp[i+m] >= m) return true;
        }
        return false;
    }
    bool hasIncreasingSubarrays(vector<int>& nums, int k) {
        n = nums.size();
        dp = vector<int>(n);
        dp[n-1] = 1;
        for (int i = n-2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) dp[i] = dp[i+1] + 1;
            else dp[i] = 1;
        }
        return check(k);
    }
};