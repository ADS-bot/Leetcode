class Solution:
  def minimumAddedInteger(self, nums1: List[int], nums2: List[int]) -> int:
    ans = math.inf
    nums1.sort()
    nums2.sort()
    for i in range(3):
      inc = nums2[0] - nums1[i]
      if self._isValidDiff(nums1, nums2, inc):
        ans = min(ans, inc)
    return ans
  def _isValidDiff(self, nums1: List[int], nums2: List[int], inc: int) -> bool:
    removed = 0
    i = 0
    for num in nums1:
      if num + inc == nums2[i]:
        i += 1
        if i == len(nums2):
          break
      else:
        removed += 1
    return removed <= 2