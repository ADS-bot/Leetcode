class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < n;i++){
            int s = 0;
            for(int j = i;j < n;j++){
                s += nums.get(j);
                if(s > 0 && j-i+1 >= l && j-i+1 <= r){
                    min = Math.min(min, s);
                }
            }
        }
        if(min == Integer.MAX_VALUE)return -1;
        return min;
    }
}