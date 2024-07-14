class Solution:
  def maximumLength(self, nums: List[int], k: int) -> int:
    dp = [collections.Counter() for _ in range(k + 1)]
    maxLen = [0] * (k + 1)
    for num in nums:
      for count in range(k, -1, -1):
        dp[count][num] += 1
        if count > 0:
          dp[count][num] = max(dp[count][num], maxLen[count - 1] + 1)
        maxLen[count] = max(maxLen[count], dp[count][num])
    return maxLen[k]