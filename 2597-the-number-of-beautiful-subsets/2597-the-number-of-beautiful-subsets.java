class Solution {
  public int beautifulSubsets(int[] nums, int k) {
    final int kMaxNum = 1000;
    int[] count = new int[kMaxNum + 1];
    Map<Integer, Set<Integer>> modToSubset = new HashMap<>();

    for (final int num : nums) {
      ++count[num];
      modToSubset.putIfAbsent(num % k, new TreeSet<>());
      modToSubset.get(num % k).add(num);
    }

    int prevNum = -k;
    int skip = 0;
    int pick = 0;

    for (Set<Integer> subset : modToSubset.values())
      for (final int num : subset) {
        final int nonEmptyCount = (int) Math.pow(2, count[num]) - 1;
        final int cacheSkip = skip;
        skip += pick;
        pick = nonEmptyCount * (1 + cacheSkip + (num - prevNum == k ? 0 : pick));
        prevNum = num;
      }

    return skip + pick;
  }
}