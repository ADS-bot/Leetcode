class Solution:
    def getLongestSubsequence(self, words, groups):
        n = len(words)
        if n == 0:
            return []

        # Initialize dynamic programming tables
        dp = [1] * n  # dp[i] will store the length of longest subsequence ending at index i
        prev = [-1] * n  # prev[i] will store the previous index in the subsequence ending at index i

        # Fill the dp and prev tables
        for i in range(1, n):
            for j in range(i):
                if groups[j] != groups[i] and dp[j] + 1 > dp[i]:
                    dp[i] = dp[j] + 1
                    prev[i] = j

        # Find the maximum length of subsequence and its last index
        max_len = max(dp)
        max_index = dp.index(max_len)

        # Reconstruct the longest alternating subsequence
        subsequence_indices = []
        while max_index != -1:
            subsequence_indices.append(max_index)
            max_index = prev[max_index]

        subsequence_indices.reverse()
        return [words[i] for i in subsequence_indices]
