class Solution:
  def maximumTotalCost(self, nums: List[int]) -> int:
    keep = -math.inf
    flip = 0
    for num in nums:
      keep, flip = max(keep, flip) + num, keep - num
    return max(keep, flip)