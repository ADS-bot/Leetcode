class Solution:
  # Very similar to 53. Maximum Subarray
  def maximumSum(self, arr: List[int]) -> int:
    ans = -math.inf
    zero = -math.inf  # No deletion.
    one = -math.inf   # At most 1 deletion.

    for a in arr:
      one = max(a, one + a, zero)
      zero = max(a, zero + a)
      ans = max(ans, one)

    return ans
