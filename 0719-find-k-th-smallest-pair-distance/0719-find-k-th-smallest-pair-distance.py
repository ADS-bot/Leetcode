class Solution:
  def smallestDistancePair(self, nums: list[int], k: int) -> int:
    nums.sort()
    def numPairDistancesNoGreaterThan(m: int) -> int:
      count = 0
      j = 1
      for i, num in enumerate(nums):
        while j < len(nums) and nums[j] <= num + m:
          j += 1
        count += j - i - 1
      return count
    return bisect.bisect_left(
        range(0, nums[-1] - nums[0]), k,
        key=lambda m: numPairDistancesNoGreaterThan(m))