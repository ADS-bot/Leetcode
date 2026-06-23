class Solution:
    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        r -= l
        dp = [1] * (r + 1)
        mod = 10 ** 9 + 7
        for i in range(1, n):
            if i & 1:
                pre = 0
                for v in range(r + 1):
                    pre2 = pre + dp[v]
                    dp[v] = pre % mod
                    pre = pre2 % mod
            else:
                pre = 0
                for v in range(r, -1, -1):
                    pre2 = pre + dp[v]
                    dp[v] = pre % mod
                    pre = pre2 % mod
        return sum(dp) * 2 % mod