class Solution:
  def minChanges(self, nums: List[int], k: int) -> int:
    p = len(nums) // 2
    d = collections.Counter()
    o = [0] * (k + 1)
    for i in range(p):
      a = nums[i]
      b = nums[-i - 1]
      d[abs(a - b)] += 1
      o[max(a, b, k - a, k - b)] += 1
    po = list(
        itertools.accumulate(reversed(o)))[::-1]
    return min(po[di] - f +
               (p - po[di]) * 2
               for di, f in d.items())