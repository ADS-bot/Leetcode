class Solution:
    def isScramble(self, s1: str, s2: str) -> bool:
        # If the lengths don't match, it's not possible
        if len(s1) != len(s2):
            return False
        
        # Memoization dictionary to store already computed results
        memo = {}

        def dfs(s1, s2):
            # If already computed, return the cached result
            if (s1, s2) in memo:
                return memo[(s1, s2)]

            # Base cases
            if s1 == s2:
                memo[(s1, s2)] = True
                return True
            
            # If character counts don't match, return False
            if sorted(s1) != sorted(s2):  # This can be replaced by a character count check for further optimization
                memo[(s1, s2)] = False
                return False

            n = len(s1)

            # Try to split at every possible index and check recursively
            for i in range(1, n):
                # Without swapping
                if dfs(s1[:i], s2[:i]) and dfs(s1[i:], s2[i:]):
                    memo[(s1, s2)] = True
                    return True
                # With swapping
                if dfs(s1[:i], s2[-i:]) and dfs(s1[i:], s2[:-i]):
                    memo[(s1, s2)] = True
                    return True

            # If no valid scramble is found
            memo[(s1, s2)] = False
            return False

        return dfs(s1, s2)
