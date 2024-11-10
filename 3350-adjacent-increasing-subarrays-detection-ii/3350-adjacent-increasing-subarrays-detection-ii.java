import java.util.List;

class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        if (n < 2) return 0;

        int[] incLen = new int[n];
        for (int i = 0; i < n; i++) {
            incLen[i] = 1;
        }
        for (int i = n - 2; i >= 0; --i) {
            if (nums.get(i) < nums.get(i + 1)) {
                incLen[i] = incLen[i + 1] + 1;
            }
        }

        int low = 1, high = n / 2, result = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(nums, incLen, mid)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    private boolean check(List<Integer> nums, int[] incLen, int k) {
        int n = nums.size();
        for (int i = 0; i <= n - 2 * k; ++i) {
            if (incLen[i] >= k && incLen[i + k] >= k) {
                return true;
            }
        }
        return false;
    }
}