import java.math.BigInteger;
import java.util.Arrays;

public class Solution {
    public boolean checkEqualPartitions(int[] nums, long trag) {
		BigInteger tot = BigInteger.ONE;
        int n = nums.length;
        for (int x : nums) {
            tot = tot.multiply(BigInteger.valueOf(x));
        }
        BigInteger tragSq = BigInteger.valueOf(trag).multiply(BigInteger.valueOf(trag));
        if (!tot.equals(tragSq)) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n/2; i++) {
            int temp = nums[i];
            nums[i] = nums[n-1-i];
            nums[n-1-i] = temp;
        }
   
        return dfs(nums, 0, 1L, trag);
    }

    private boolean dfs(int[] nums, int index, long curprd, long trag) {
        
        if (curprd == trag) {
            return true;
        }
        int n = nums.length;
  
        if (index == n || curprd > trag) {
            return false;
        }
     
        long x = nums[index];
        if (x > 0                 
            && curprd <= trag / x  
            && (trag % (curprd * x) == 0)) 
        {
            if (dfs(nums, index + 1, curprd * x, trag)) {
                return true;
            }
        }
        if (dfs(nums, index + 1, curprd, trag)) {
            return true;
        }
        return false;
    }
}