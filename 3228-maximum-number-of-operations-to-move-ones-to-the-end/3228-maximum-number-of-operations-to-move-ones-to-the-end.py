class Solution:
  def maxOperations(self, s: str) -> int:
    a = 0
    o = 0
    for i, c in enumerate(s):
      if c == '1':
        o += 1
      elif i + 1 == len(s) or s[i + 1] == '1':
        a += o
    return a