class Solution:
  def maximumScore(self, grid: List[List[int]]) -> int:
    n = len(grid)
    p = [[0] * (n + 1) for _ in range(n)]
    a = [0] * (n + 1)
    b = [0] * (n + 1)
    for j in range(n):
      for i in range(n):
        p[j][i + 1] = p[j][i] + grid[i][j]
    for j in range(1, n):
      c = [0] * (n + 1)
      d = [0] * (n + 1)
      for x in range(n + 1):
        for y in range(n + 1):
          if x > y:
            s = p[j - 1][x] - p[j - 1][y]
            c[x] = max(c[x], b[y] + s)
            d[x] = max(d[x], b[y] + s)
          else:
            s = p[j][y] - p[j][x]
            c[x] = max(c[x], a[y] + s)
            d[x] = max(d[x], a[y])
      a = c
      b = d
    return max(a)
