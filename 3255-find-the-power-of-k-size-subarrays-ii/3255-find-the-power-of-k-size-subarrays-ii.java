class Solution {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] ct = new int[n];
        ct[0] = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i]==nums[i-1]+1) {
                ct[i] = ct[i-1]+1;
            } else {
                ct[i] = 1;
            }
        }
        int[] ans = new int[n-k+1];
        for (int i = 0; i < n-k+1; ++i) {
            if (ct[i+k-1]>=k) {
                ans[i] = nums[i+k-1];
            } else {
                ans[i] = -1;
            }
        }
        return ans;
    }
}