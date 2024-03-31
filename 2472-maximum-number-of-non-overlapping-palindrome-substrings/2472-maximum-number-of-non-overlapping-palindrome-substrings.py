class Solution:
  def maxPalindromes(self, s: str, k: int) -> int:
    n = len(s)
    # dp[i] := the maximum number of substrings in the first i chars of s
    dp = [0] * (n + 1)

    def isPalindrome(l: int, r: int) -> bool:
      """Returns True is s[i..j) is a palindrome."""
      if l < 0:
        return False
      while l < r:
        if s[l] != s[r]:
          return False
        l += 1
        r -= 1
      return True
    for i in range(k, n + 1):
      dp[i] = dp[i - 1]
      # Consider palindrome with length k.
      if isPalindrome(i - k, i - 1):
        dp[i] = max(dp[i], 1 + dp[i - k])
      # Consider palindrome with length k + 1.
      if isPalindrome(i - k - 1, i - 1):
        dp[i] = max(dp[i], 1 + dp[i - k - 1])

    return dp[n]