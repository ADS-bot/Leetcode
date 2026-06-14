class Solution {
    long[] pref;
    int n;
    long[] dp;
    int[] cnt;
    int[] dq; 
    
    class Result {
        long val;
        int cnt;
        Result(long val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }
    }
    
    public long maximumSum(int[] nums, int m, int l, int r) {
        n = nums.length;
        pref = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums[i];
        }
        
        dp = new long[n + 1];
        cnt = new int[n + 1];
        dq = new int[n + 1];
        
        long maxSingle = (long) -1e18;
        int head = 0, tail = 0;
        for (int i = l; i <= n; i++) {
            int p = i - l;
            while (tail > head && pref[dq[tail - 1]] >= pref[p]) {
                tail--;
            }
            dq[tail++] = p;
            while (tail > head && dq[head] < i - r) {
                head++;
            }
            maxSingle = Math.max(maxSingle, pref[i] - pref[dq[head]]);
        }
        
        if (maxSingle <= 0) {
            return maxSingle;
        }
        
        Result res0 = check(0, l, r);
        if (res0.cnt <= m) {
            return res0.val;
        }
        
        long low = 0, high = (long) 2e14, ansC = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (check(mid, l, r).cnt >= m) {
                ansC = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return check(ansC, l, r).val + m * ansC;
    }
    
    private Result check(long C, int l, int r) {
        for (int i = 0; i <= n; i++) {
            dp[i] = 0;
            cnt[i] = 0;
        }
        int head = 0, tail = 0;
        
        for (int i = l; i <= n; i++) {
            int p = i - l;
            
            while (tail > head) {
                int back = dq[tail - 1];
                long val1 = dp[p] - pref[p];
                long val2 = dp[back] - pref[back];
                if (val1 > val2 || (val1 == val2 && cnt[p] > cnt[back])) {
                    tail--;
                } else {
                    break;
                }
            }
            dq[tail++] = p;
            
            while (tail > head && dq[head] < i - r) {
                head++;
            }
            
            dp[i] = dp[i - 1];
            cnt[i] = cnt[i - 1];
            
            if (tail > head) {
                int best = dq[head];
                long takeVal = dp[best] - pref[best] + pref[i] - C;
                int takeCnt = cnt[best] + 1;
                
                if (takeVal > dp[i] || (takeVal == dp[i] && takeCnt > cnt[i])) {
                    dp[i] = takeVal;
                    cnt[i] = takeCnt;
                }
            }
        }
        return new Result(dp[n], cnt[n]);
    }
}