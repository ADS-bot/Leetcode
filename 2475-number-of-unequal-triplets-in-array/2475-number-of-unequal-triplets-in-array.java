
class Solution {
  public int unequalTriplets(int[] nums) {
    int ans = 0;
    int prev = 0;
    int next = nums.length;
    Map<Integer, Integer> count = new HashMap<>();

    for (final int num : nums)
      count.merge(num, 1, Integer::sum);

    for (final int freq : count.values()) {
      next -= freq;
      ans += prev * freq * next;
      prev += freq;
    }

    return ans;
  }
}