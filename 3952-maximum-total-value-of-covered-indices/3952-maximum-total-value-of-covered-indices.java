class Solution {
    public long maxTotal(int[] nums, String s) {
        int n = nums.length;

        long neg = Long.MIN_VALUE / 4;
        long[] dp = {0, neg};

        for (int i = 0; i < n; i++) {
            long[] ndp = {neg, neg};

            if (s.charAt(i) == '1') {
                for (int p = 0; p < 2; p++) {
                    if (dp[p] == neg) continue;

                    long add = (i > 0) ? nums[i - 1] : 0;
                    ndp[0] = Math.max(ndp[0], dp[p] + add);

                    add = (i > 0 && p == 1) ? nums[i - 1] : 0;
                    ndp[1] = Math.max(ndp[1], dp[p] + add);
                }
            } else {
                for (int p = 0; p < 2; p++) {
                    if (dp[p] == neg) continue;

                    long add = (i > 0 && p == 1) ? nums[i - 1] : 0;
                    ndp[0] = Math.max(ndp[0], dp[p] + add);
                }
            }

            dp = ndp;
        }

        return Math.max(dp[0], dp[1] + nums[n - 1]);
    }
}