class Solution {

    private static final long MOD = 1_000_000_007L;

    public int maxTotalValue(int[] value, int[] decay, int m) {

        long lo = 1;
        long hi = 1_000_000_000L;

        while (lo <= hi) {
            long mid = (lo + hi) >>> 1;

            if (countTerms(value, decay, mid) >= m) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        long threshold = hi;

        long totalCount = 0;
        long totalSum = 0;

        for (int i = 0; i < value.length; i++) {

            long v = value[i];
            long d = decay[i];

            if (v < threshold) continue;

            long cnt;

            if (d == 0) {
                continue;
            } else {
                cnt = (v - threshold) / d + 1;
            }

            totalCount += cnt;

            long last = v - (cnt - 1) * d;

            totalSum += cnt * (v + last) / 2;
        }

        long extra = totalCount - m;

        totalSum -= extra * threshold;

        totalSum %= MOD;

        if (totalSum < 0) totalSum += MOD;

        return (int) totalSum;
    }

    private long countTerms(int[] value, int[] decay, long threshold) {

        long count = 0;

        for (int i = 0; i < value.length; i++) {

            long v = value[i];
            long d = decay[i];

            if (v < threshold) continue;

            long cnt;

            if (d == 0) {
                cnt = Long.MAX_VALUE / 4;
            } else {
                cnt = (v - threshold) / d + 1;
            }

            count += cnt;

            if (count > 1_000_000_000L) {
                return count;
            }
        }

        return count;
    }
}