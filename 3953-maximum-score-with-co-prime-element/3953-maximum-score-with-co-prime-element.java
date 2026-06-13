class Solution {
    public int maxScore(int[] nums, int maxVal) {
        int n = nums.length;
        int mx = maxVal;

        for (int x : nums) mx = Math.max(mx, x);

        int[] freq = new int[mx + 1];
        for (int x : nums) freq[x]++;

        int[] cnt = new int[mx + 1];
        for (int d = 1; d <= mx; d++) {
            for (int m = d; m <= mx; m += d) {
                cnt[d] += freq[m];
            }
        }

        int[] mu = new int[mx + 1];
        int[] primes = new int[mx + 1];
        boolean[] comp = new boolean[mx + 1];
        int pc = 0;
        mu[1] = 1;

        for (int i = 2; i <= mx; i++) {
            if (!comp[i]) {
                primes[pc++] = i;
                mu[i] = -1;
            }
            for (int j = 0; j < pc && i * primes[j] <= mx; j++) {
                int p = primes[j];
                comp[i * p] = true;
                if (i % p == 0) {
                    mu[i * p] = 0;
                    break;
                }
                mu[i * p] = -mu[i];
            }
        }

        long ans = Long.MIN_VALUE;

        for (int v = 1; v <= mx; v++) {
            int cop = 0;

            for (int d = 1; d * d <= v; d++) {
                if (v % d != 0) continue;

                cop += mu[d] * cnt[d];

                int e = v / d;
                if (e != d) cop += mu[e] * cnt[e];
            }

            long base = (long) v - n + 1 + cop;

            if (freq[v] > 0) {
                long pen = (v == 1) ? 1 : 0;
                ans = Math.max(ans, base - pen);
            }

            if (v <= maxVal && freq[v] == 0) {
                long pen = (cop == n) ? 2 : 1;
                ans = Math.max(ans, base - pen);
            }
        }

        return (int) ans;
    }
}