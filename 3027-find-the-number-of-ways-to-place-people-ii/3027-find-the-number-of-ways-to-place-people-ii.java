class Solution {
  // Same as 3025. Find the Number of Ways to Place People I
  public int numberOfPairs(int[][] points) {
    int ans = 0;

    Arrays.sort(points, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

    for (int i = 0; i < points.length; ++i) {
      int maxY = Integer.MIN_VALUE;
      for (int j = i + 1; j < points.length; ++j)
        if (points[i][1] >= points[j][1] && points[j][1] > maxY) {
          ++ans;
          maxY = points[j][1];
        }
    }

    return ans;
  }
}