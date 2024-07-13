class Solution {
  // Similar to 1521. Find a Value of a Mysterious Function Closest to Target
  public long countSubarrays(int[] nums, int k) {
    long ans = 0;
    Map<Integer, Integer> prev = new HashMap<>();

    for (final int num : nums) {
      Map<Integer, Integer> curr = new HashMap<>() {
        { put(num, 1); }
      };
      for (Map.Entry<Integer, Integer> entry : prev.entrySet()) {
        final int val = entry.getKey();
        final int freq = entry.getValue();
        curr.merge(val & num, freq, Integer::sum);
      }
      ans += curr.getOrDefault(k, 0);
      prev = curr;
    }

    return ans;
  }
}