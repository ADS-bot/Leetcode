class Solution:
  def minEnd(self, n: int, x: int) -> int:
    n -= 1
    mask = 1
    ans = x
    while n:
        while (mask & x):
            mask <<= 1
        ans |= mask*(n & 1)
        mask <<= 1
        n >>= 1
    return ans