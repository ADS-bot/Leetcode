class Solution {
  public int waysToReachStair(int k) {

    final int kMaxJump = 29;
    final int[][] comb = getComb(kMaxJump + 1, kMaxJump + 1);
    int ans = 0;

    for (int jump = 0; jump <= kMaxJump; ++jump) {
      final int down = (1 << jump) - k;
      if (down < 0 || down > jump + 1)
        continue;
      ans += comb[jump + 1][down];
    }

    return ans;
  }

  // C(n, k) = C(n - 1, k) + C(n - 1, k - 1)
  private int[][] getComb(int n, int k) {
    int[][] comb = new int[n + 1][k + 1];

    for (int i = 0; i <= n; ++i)
      comb[i][0] = 1;
    for (int i = 1; i <= n; ++i)
      for (int j = 1; j <= k; ++j)
        comb[i][j] = comb[i - 1][j] + comb[i - 1][j - 1];
    return comb;
  }
}