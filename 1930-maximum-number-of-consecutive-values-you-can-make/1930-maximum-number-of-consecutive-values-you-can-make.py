class Solution:
  def getMaximumConsecutive(self, coins: List[int]) -> int:
    ans = 1  # next value we want to make

    for coin in sorted(coins):
      if coin > ans:
        return ans
      ans += coin

    return ans
