class Solution:
    def minTaps(self, n: int, ranges: List[int]) -> int:
      dp = [0] + [n + 2] * n   # dp[i] stores the minimum taps required to reach i
      for i in range(n + 1):  
          for j in range(max(0, i - ranges[i]), min(n, i + ranges[i]) + 1): 
              dp[j] = min(dp[j], dp[max(0, i - ranges[i])] + 1)  
      return dp[n] if dp[n] < n + 2 else -1