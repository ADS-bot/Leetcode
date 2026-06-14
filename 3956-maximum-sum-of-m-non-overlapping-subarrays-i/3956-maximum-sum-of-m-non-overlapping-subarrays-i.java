class Solution {
    public long maximumSum(int[] nums, int m, int l, int r) {
        int n = nums.length;
        
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums[i];
        }
        
        long INFTY = (long) -1e17; 
        long[][] dp = new long[m + 1][n + 1];
        
        for (int j = 1; j <= m; j++) {
            for (int i = 0; i <= n; i++) {
                dp[j][i] = INFTY;
            }
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }
        
        long maxSum = INFTY;
        
        for (int j = 1; j <= m; j++) {
            Deque<Integer> dq = new ArrayDeque<>();
            
            for (int i = l; i <= n; i++) {
                int p = i - l; 
                
                if (dp[j - 1][p] != INFTY) {
                    long curVal = dp[j - 1][p] - pref[p];
                    while (!dq.isEmpty() && (dp[j - 1][dq.peekLast()] - pref[dq.peekLast()]) <= curVal) {
                        dq.pollLast();
                    }
                    dq.addLast(p);
                }
                
                while (!dq.isEmpty() && dq.peekFirst() < i - r) {
                    dq.pollFirst();
                }
                
                dp[j][i] = dp[j][i - 1];
                
                if (!dq.isEmpty()) {
                    int bestP = dq.peekFirst();
                    long bestWindowVal = dp[j - 1][bestP] - pref[bestP];
                    dp[j][i] = Math.max(dp[j][i], bestWindowVal + pref[i]);
                }
            }
            maxSum = Math.max(maxSum, dp[j][n]);
        }
        
        return maxSum;
    }
}