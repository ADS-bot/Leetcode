
class Solution:
  def unequalTriplets(self, nums: List[int]) -> int:
    ans = 0
    prev = 0
    next = len(nums)

    for freq in collections.Counter(nums).values():
      next -= freq
      ans += prev * freq * next
      prev += freq

    return ans