class Solution {
  public long maximumScore(int[][] grid) {
    final int n = grid.length;
    long[][] prefix = new long[n][n + 1];
    long[] prevPick = new long[n + 1];
    long[] prevSkip = new long[n + 1];
    for (int j = 0; j < n; ++j)
      for (int i = 0; i < n; ++i)
        prefix[j][i + 1] = prefix[j][i] + grid[i][j];
    for (int j = 1; j < n; ++j) {
      long[] currPick = new long[n + 1];
      long[] currSkip = new long[n + 1];
      for (int curr = 0; curr <= n; ++curr)
        for (int prev = 0; prev <= n; ++prev)
          if (curr > prev) {
            final long score = prefix[j - 1][curr] - prefix[j - 1][prev];
            currPick[curr] = Math.max(currPick[curr], prevSkip[prev] + score);
            currSkip[curr] = Math.max(currSkip[curr], prevSkip[prev] + score);
          } else {
            final long score = prefix[j][prev] - prefix[j][curr];
            currPick[curr] = Math.max(currPick[curr], prevPick[prev] + score);
            currSkip[curr] = Math.max(currSkip[curr], prevPick[prev]);
          }
      prevPick = currPick;
      prevSkip = currSkip;
    }
    return Arrays.stream(prevPick).max().getAsLong();
  }
}