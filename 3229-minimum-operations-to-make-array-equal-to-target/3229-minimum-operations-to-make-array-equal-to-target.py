class Solution:
  def minimumOperations(self, nums: List[int], target: List[int]) -> int:
    a = abs(nums[0] - target[0])
    for (n1, t1), (n2, t2) in itertools.pairwise(zip(nums, target)):
      d1 = t2 - n2
      d2 = t1 - n1
      if d1 >= 0 and d2 >= 0:
        a += max(0, d1 - d2)
      elif d1 <= 0 and d2 <= 0:
        a += max(0, abs(d1) - abs(d2))
      else:
        a += abs(d1)
    return a
