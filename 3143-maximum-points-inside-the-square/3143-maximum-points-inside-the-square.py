class Solution:
  def maxPointsInsideSquare(self, points: List[List[int]], s: str) -> int:
    secondMinSize = math.inf
    minSizes = {}
    for (x, y), c in zip(points, s):
      sz = max(abs(x), abs(y))
      if c not in minSizes:
        minSizes[c] = sz
      elif sz < minSizes[c]:
        secondMinSize = min(secondMinSize, minSizes[c])
        minSizes[c] = sz
      else:
        secondMinSize = min(secondMinSize, sz)

    return sum(sz < secondMinSize for sz in minSizes.values())