
class Solution {

    boolean ok(int[] a)
    {
        Set<Integer> s = new HashSet<>();
        for(int v : a){
            if(!s.add(v))return false;
        }
        return true;
    }
    public int minimumOperations(int[] nums) {
        int step = 0;
        while(true){
            if(ok(nums)){
                return step;
            }
            step++;
            if(nums.length <= 2){
                nums = new int[0];
            }else{
                nums = Arrays.copyOfRange(nums, 3, nums.length);
            }
        }
    }
}