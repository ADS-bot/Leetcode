class Solution:
    def numberOfPermutations(self, n: int, requirements: List[List[int]]) -> int:
        requirements.sort()
        dp = [0] * (requirements[-1][-1] + 1)
        dp[0] = 1
        r_map = {}
        for r in requirements:
            r_map[r[0]] = r[1]
        for i in range(1, n):
            for j in range(len(dp) - 1, -1, -1):
                if i - 1 not in r_map:
                    dp[j] = sum(dp[max(0, j - i):j + 1])
                elif 0 <= j - r_map[i - 1] <= min(i, j):
                    dp[j] = dp[r_map[i - 1]]
                else:
                    dp[j] = 0
        return dp[-1] % 1000000007