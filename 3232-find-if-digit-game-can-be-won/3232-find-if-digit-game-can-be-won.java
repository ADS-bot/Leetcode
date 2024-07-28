class Solution {

    public boolean canAliceWin(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count += num > 9 ? num : -num;
        }
        return count != 0;
    }
}