class Solution {
    public int winningPlayerCount(int n, int[][] pick) {
        int[][] count = new int[n][11];
        for (int[] p : pick) count[p[0]][p[1]]++;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 10; j++) {
                if (count[i][j] > i) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}