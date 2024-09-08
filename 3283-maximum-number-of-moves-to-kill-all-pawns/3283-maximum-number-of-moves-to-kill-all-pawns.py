class Solution:
    def maxMoves(self, kx: int, ky: int, pos: List[List[int]]) -> int:
        n = len(pos)
        mv = [(-2, -1), (-2, 1), (-1, -2), (-1, 2), (1, -2), (1, 2), (2, -1), (2, 1)]
        @cache
        def moves(u, v):
            q = deque([(u, v)])
            d = [[inf] * 50 for _ in range(50)]
            d[u][v] = 0
            while q:
                i, j = q.popleft()
                for di, dj in mv:
                    if 0 <= i+di < 50 and 0 <= j+dj < 50 and d[i+di][j+dj] == inf:
                        d[i+di][j+dj] = d[i][j] + 1
                        q.append((i+di, j+dj))
            return lambda x, y: d[x][y]

        @cache
        def solve(b, x, y):
            if b == 0: return 0
            f = [max, min][(n - b.bit_count()) % 2]
            return f(
                solve(b - (1 << j), *pos[j]) + moves(x, y)(*pos[j])
                for j in range(n)
                if b >> j & 1
            )
        return solve(2 ** n - 1, kx, ky)