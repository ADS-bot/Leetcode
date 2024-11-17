class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] p = new int[n+1];
        for(int[] q : queries) {
            p[q[0]] ++;
            p[q[1]+1] --;
        }
        long sum = 0;
        for(int i=0;i<n;i++) {
            sum  += p[i];
            if(sum<nums[i]) return false;
        }
        return true;
    }
}