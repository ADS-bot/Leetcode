class Solution {
    private static ArrayList<Integer> list = new ArrayList<>() {
        {
            boolean[] flag = new boolean[1000000];
            for (int i = 2; i < 1000000; i++) {
                if (!flag[i]) {
                    add(i);
                    for (int j = 2 * i; j < 1000000; j += i) {
                        flag[j] = true;
                    }
                }
            }
        }
    };
    public int minOperations(int[] nums) {
        int count = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                for (int j : list) {
                    if (nums[i] % j == 0) {
                        if ((nums[i] = j) > nums[i + 1]) {
                            return -1;
                        }
                        break;
                    }
                }
                count++;
            }
        }
        return count;
    }
}