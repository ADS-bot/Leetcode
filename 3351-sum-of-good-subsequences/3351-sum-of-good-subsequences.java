class Solution {
    public int sumOfGoodSubsequences(int[] nums) {
        final int MOD = (int) 1e9 + 7;
        Map<Integer, Long> counts = new HashMap<>();
        Map<Integer, Long> sums = new HashMap<>();

        for (int num : nums) {
            long tempCount = counts.getOrDefault(num, 0L);
            long tempSum = sums.getOrDefault(num, 0L);

            long newCount = tempCount + 1;
            long newSum = (tempSum + num) % MOD;

            if (counts.containsKey(num - 1)) {
                newCount = (newCount + counts.get(num - 1)) % MOD;
                newSum = (newSum + sums.get(num - 1) + num * counts.get(num - 1) % MOD) % MOD;
            }

            if (counts.containsKey(num + 1)) {
                newCount = (newCount + counts.get(num + 1)) % MOD;
                newSum = (newSum + sums.get(num + 1) + num * counts.get(num + 1) % MOD) % MOD;
            }

            counts.put(num, newCount);
            sums.put(num, newSum);
        }

        long totalSum = 0;
        for (long sum : sums.values()) {
            totalSum = (totalSum + sum) % MOD;
        }

        return (int) totalSum;
    }
}