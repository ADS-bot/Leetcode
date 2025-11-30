class Solution {
    public int minMirrorPairDistance(int[] nums) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];

            if (map.containsKey(val)) {
                int d = i - map.get(val);
                if (d < ans) ans = d;
            }

            int rev = 0, t = val;
            while (t > 0) {
                rev = rev * 10 + t % 10;
                t /= 10;
            }

            map.put(rev, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}