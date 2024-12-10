class Solution:
  def maximumLength(self, s: str) -> int:
    n = len(s)
    runningLen = 0
    prevLetter = '@'
    counts = [[0] * (n + 1) for _ in range(26)]
    for c in s:
      if c == prevLetter:
        runningLen += 1
        counts[ord(c) - ord('a')][runningLen] += 1
      else:
        runningLen = 1
        counts[ord(c) - ord('a')][runningLen] += 1
        prevLetter = c
    def getMaxFreq(count: List[int]) -> int:
      times = 0
      for freq in range(n, 0, -1):
        times += count[freq]
        if times >= 3:
          return freq
      return -1
    return max(getMaxFreq(count) for count in counts)