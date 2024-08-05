class Solution {

    private int solveRow(int[][] grid) {
        int i , n = grid.length , ans = 0;
        for (i = 0;i < n;i ++) {
            int[] row = grid[i];
            int l = 0 , r = row.length - 1;
            while (l < r) {
                if (row[l] != row[r]) {
                    ans ++;
                }
                l ++;
                r --;
            }
        }
        return ans;
    }

    private int solveCol(int[][] grid) {
        int j , n = grid.length , m = grid[0].length , ans = 0;
        for (j = 0;j < m;j ++) {
            int l = 0 , r = n - 1;
            while (l < r) {
                if (grid[l][j] != grid[r][j]) {
                    ans ++;
                }
                l ++;
                r --;
            }
        }
        return ans;
    }

    public int minFlips(int[][] grid) {
        return Math.min(solveRow(grid) , solveCol(grid));
    }

}