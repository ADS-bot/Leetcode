import java.math.BigInteger;
class Solution {
    public long maxScore(int[] nums) {
        long max = 0;
        for (int i = -1; i < nums.length; i++) {
            BigInteger gcd = BigInteger.ZERO, lcm = BigInteger.ONE;
            for (int j = 0; j < nums.length; j++) {
                gcd = i == j ? gcd : gcd.gcd(BigInteger.valueOf(nums[j]));
                lcm = i == j ? lcm : lcm.multiply(BigInteger.valueOf(nums[j])).divide(lcm.gcd(BigInteger.valueOf(nums[j])));
            }
            max = Math.max(max, gcd.multiply(lcm).longValue());
        }
        return max;
    }
}