class Solution {
    public int minOperations(int[] nums, int k) {
        for (int num : nums) {
            if (num < k) {
                return -1;
            }
        }

        Set<Integer> uniqueGreaterThanK = new HashSet<>();
        for (int num : nums) {
            if (num > k) {
                uniqueGreaterThanK.add(num);
            }
        }

        return uniqueGreaterThanK.size();
    }
}