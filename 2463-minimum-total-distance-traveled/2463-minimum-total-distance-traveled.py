class Solution:
  def minimumTotalDistance(self, robot: List[int], factory: List[List[int]]) -> int:
    robot.sort()
    factory.sort()
    @functools.lru_cache(None)
    def dp(i: int, j: int, k: int) -> int:
      if i == len(robot):
        return 0
      if j == len(factory):
        return math.inf
      skipFactory = dp(i, j + 1, 0)
      position, limit = factory[j]
      useFactory = dp(i + 1, j, k + 1) + abs(robot[i] - position) \
          if limit > k else math.inf
      return min(skipFactory, useFactory)
    return dp(0, 0, 0)