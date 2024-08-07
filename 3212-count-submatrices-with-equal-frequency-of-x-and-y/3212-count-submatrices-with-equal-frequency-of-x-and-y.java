class Solution {
  public int numberOfSubmatrices(char[][] grid) {
    final int m = grid.length;
    final int n = grid[0].length;
    int ans = 0;
    // x[i][j] := the number of 'X' in grid[0..i)[0..j)
    int[][] x = new int[m + 1][n + 1];
    // y[i][j] := the number of 'Y' in grid[0..i)[0..j)
    int[][] y = new int[m + 1][n + 1];

    for (int i = 0; i < m; ++i)
      for (int j = 0; j < n; ++j) {
        x[i + 1][j + 1] = (grid[i][j] == 'X' ? 1 : 0) + x[i][j + 1] + x[i + 1][j] - x[i][j];
        y[i + 1][j + 1] = (grid[i][j] == 'Y' ? 1 : 0) + y[i][j + 1] + y[i + 1][j] - y[i][j];
        if (x[i + 1][j + 1] > 0 && x[i + 1][j + 1] == y[i + 1][j + 1])
          ++ans;
      }

    return ans;
  }
}