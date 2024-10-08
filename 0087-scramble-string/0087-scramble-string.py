class Solution:
    def isScramble(self, s1: str, s2: str) -> bool:
        if len(s1) != len(s2):
            return False
        
        n = len(s1)

        # dp[k][i][j] is True if s1[i:i+k] is a scrambled string of s2[j:j+k]
        dp = [[[False] * n for _ in range(n)] for _ in range(n + 1)]

        # Initialize the base case for substrings of length 1
        for i in range(n):
            for j in range(n):
                dp[1][i][j] = (s1[i] == s2[j])

        # Build up the solution for substrings of length 2 to n
        for length in range(2, n + 1):
            for i in range(n - length + 1):
                for j in range(n - length + 1):
                    for k in range(1, length):
                        # Case 1: No swap
                        if dp[k][i][j] and dp[length - k][i + k][j + k]:
                            dp[length][i][j] = True
                            break
                        # Case 2: With swap
                        if dp[k][i][j + length - k] and dp[length - k][i + k][j]:
                            dp[length][i][j] = True
                            break

        return dp[n][0][0]
