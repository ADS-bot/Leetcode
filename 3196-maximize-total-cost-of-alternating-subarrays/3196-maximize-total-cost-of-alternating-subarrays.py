class Solution:
  def maximumTotalCost(self, nums: List[int]) -> int:
    keep = nums[0]
    flip = nums[0]
    for i in range(1, len(nums)):
      keepCurr = max(keep, flip) + nums[i]
      flipCurr = keep - nums[i]
      keep = keepCurr
      flip = flipCurr
    return max(keep, flip)