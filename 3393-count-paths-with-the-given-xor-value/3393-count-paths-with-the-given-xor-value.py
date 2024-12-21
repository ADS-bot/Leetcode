from typing import List
class Solution:
    def countPathsWithXorValue(self, grid: List[List[int]], k: int) -> int:
        n = len(grid)
        m = len(grid[0])
        ans = [[0] * 16]
        ans[0][grid[0][0]] = 1
        divisor = 1000000007
        for i in range(1, m):
            tmp = []
            for j in range(16):
                tmp.append(ans[i - 1][grid[0][i] ^ j])
            ans.append(tmp)
        for i in range(1, n):
            tmp = [[]]
            for j in range(16):
                tmp[0].append(ans[0][grid[i][0] ^ j])
            for j in range(1, m):
                now = []
                for kk in range(16):
                    target = kk ^ grid[i][j]
                    now.append((tmp[-1][target] + ans[j][target]) % divisor)
                tmp.append(now)
            ans = tmp
        return ans[-1][k]