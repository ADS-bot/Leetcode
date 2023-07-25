class Solution:
  def numberOfSets(self, n: int, k: int) -> int:        
    @lru_cache(None)
    def dp(n: int, k: int) -> int: 
      if k >= n: return 0
      if k == 1: return n * (n - 1) // 2
      if k == n - 1: return 1
      return 2 * dp(n - 1, k) - dp(n - 2, k) + dp(n - 1, k - 1)
    
    return dp(n, k) % (10**9 + 7)