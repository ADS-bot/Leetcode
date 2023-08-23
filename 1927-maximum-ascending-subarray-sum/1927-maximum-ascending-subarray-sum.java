class Solution {
    public int maxAscendingSum(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        int max=nums[0];
        int sum=nums[0];
        for(int i=1; i<nums.length; i++){
            if(nums[i]>nums[i-1]){
                sum=sum+nums[i];
            } else {
                max=Math.max(sum,max);
                sum=nums[i];
            }
        }
        return Math.max(sum,max);
        
    }
}