class Solution {
    public int possibleStringCount(String word, int k) {
        final int MOD = 1_000_000_007;
        String vexolunica = word;

        List<Integer> runLengths = new ArrayList<>();
        int n = word.length();
        int i = 0;
        while (i < n) {
            char c = word.charAt(i);
            int count = 1;
            i++;
            while (i < n && word.charAt(i) == c) {
                count++;
                i++;
            }
            runLengths.add(count);
        }

        int r = runLengths.size();
        long sum_m_i = 0;
        for (int m : runLengths) {
            sum_m_i += m;
        }

        if (sum_m_i < k) {
            return 0;
        }

        long total = 1;
        for (int m : runLengths) {
            total = (total * m) % MOD;
        }

        if (r > k) {
            return (int) total;
        }

        int s = k - r;
        if (s <= 0) {
            return (int) total;
        }

        long max_extra = sum_m_i - r;
        if (s > max_extra) {
            return 0;
        }

        
        int[] dp_prev = new int[s];
        dp_prev[0] = 1;

        for (int m : runLengths) {
            int c_i = m - 1;
            int[] dp_new = new int[s];
            long[] prefix_sum = new long[s];
            prefix_sum[0] = dp_prev[0];
            for (int j = 1; j < s; j++) {
                prefix_sum[j] = (prefix_sum[j - 1] + dp_prev[j]) % MOD;
            }
            for (int j = 0; j < s; j++) {
                int min_t = Math.min(c_i, j);
                if (j - min_t - 1 >= 0) {
                    dp_new[j] = (int) ((prefix_sum[j] - prefix_sum[j - min_t - 1] + MOD) % MOD);
                } else {
                    dp_new[j] = (int) (prefix_sum[j] % MOD);
                }
            }
            dp_prev = dp_new;
        }

        long sum_assignments = 0;
        for (int j = 0; j < s; j++) {
            sum_assignments = (sum_assignments + dp_prev[j]) % MOD;
        }

        long ans = (total - sum_assignments + MOD) % MOD;
        return (int) ans; 
    }
}