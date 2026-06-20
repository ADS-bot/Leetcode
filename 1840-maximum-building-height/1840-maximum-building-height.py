class Solution:
    def maxBuilding(self, n: int, rr: List[List[int]]) -> int:
        rr.append([1, 0])
        rr.sort()

        if rr[-1][0] != n:
            rr.append([n, n - 1])

        m = len(rr)

        for i in range(1, m):
            dt = rr[i][0] - rr[i - 1][0]
            rr[i][1] = min(rr[i][1],rr[i - 1][1] + dt)

        for i in range(m - 2, -1, -1):
            dt = rr[i + 1][0] - rr[i][0]
            rr[i][1] = min(rr[i][1],rr[i + 1][1] + dt)

        a = 0

        for i in range(1, m):
            x1, h1 = rr[i - 1]
            x2, h2 = rr[i]

            d = x2 - x1
            a = max(a, (h1 + h2 + d) // 2)

        return a