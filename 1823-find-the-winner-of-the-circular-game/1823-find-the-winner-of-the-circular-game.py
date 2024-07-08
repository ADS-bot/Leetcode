class Solution:
  def findTheWinner(self, n: int, k: int) -> int:
    def f(n: int, k: int) -> int:
      ans = 0
      for i in range(2, n + 1):
        ans = (ans + k) % i
      return ans
    return f(n, k) + 1