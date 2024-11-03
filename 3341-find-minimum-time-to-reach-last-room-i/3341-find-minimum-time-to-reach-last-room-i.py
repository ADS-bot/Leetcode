class Solution:
    def minTimeToReach(self, moveTime: List[List[int]]) -> int:
        n, m = len(moveTime), len(moveTime[0])
        d = [[[inf] * m for _ in range(n)] for _ in range(2)]
        d[0][0][0] = 0
        q = [(0, 0, 0, 0)]
        ds = [(0, 1), (0, -1), (1, 0), (-1, 0)]
        while q:
            w, i, j, k = heappop(q)
            if w > d[k][i][j]: continue
            for di, dj in ds:
                x, y, z = i+di, j+dj, k ^ 1
                if not (0 <= x < n and 0 <= y < m): continue
                v = max(w, moveTime[x][y]) + 1
                if v < d[z][x][y]:
                    d[z][x][y] = v
                    heappush(q, (d[z][x][y], x, y, z))
        return min(d[0][n-1][m-1], d[1][n-1][m-1])