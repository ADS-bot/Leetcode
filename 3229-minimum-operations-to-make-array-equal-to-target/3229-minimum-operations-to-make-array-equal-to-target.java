class Solution {
  public long minimumOperations(int[] nums, int[] target) {
    long ans = Math.abs((long) nums[0] - target[0]);
    for (int i = 1; i < nums.length; ++i) {
      final int currDiff = target[i] - nums[i];
      final int prevDiff = target[i - 1] - nums[i - 1];
      if (currDiff >= 0 && prevDiff >= 0)
        ans += Math.max(0, currDiff - prevDiff);
      else if (currDiff <= 0 && prevDiff <= 0)
        ans += Math.max(0, Math.abs((long) currDiff) - Math.abs((long) prevDiff));
      else
        ans += Math.abs((long) currDiff);
    }
    return ans;
  }
}