class Solution:
  def doesAliceWin(self, s: str) -> bool:
    k = 'aeiou'
    return any(c in k for c in s)