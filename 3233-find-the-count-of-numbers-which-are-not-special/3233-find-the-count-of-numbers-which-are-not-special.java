class Solution {
    private static HashSet<Integer> set = new HashSet<>() {
        {
            boolean[] flag = new boolean[31608];
            for (int i = 2; i < 31608; i++) {
                if (!flag[i]) {
                    add(i);
                    for (int j = 2 * i; j < 31608; j += i) {
                        flag[j] = true;
                    }
                }
            }
        }
    };

    public int nonSpecialCount(int l, int r) {
        int count = r - l + 1;
        for (int i : set) {
            count -= i * i < l || i * i > r ? 0 : 1;
        }
        return count;
    }
}