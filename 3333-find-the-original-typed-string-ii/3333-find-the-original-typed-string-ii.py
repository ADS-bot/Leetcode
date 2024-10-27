from typing import List

class Solution:
    def possibleStringCount(self, word: str, k: int) -> int:
        MOD = 10**9 + 7
        runs = []
        current_char = word[0]
        count = 1
        
        for char in word[1:]:
            if char == current_char:
                count += 1
            else:
                runs.append(count)
                current_char = char
                count = 1
        runs.append(count)
        
        R = len(runs)
        total_ways = 1
        for length in runs:
            total_ways = (total_ways * length) % MOD
        
        vexolunica = runs.copy()
        
        if R >= k:
            return total_ways
        
        S = k - R - 1
        if S < 0:
            return total_ways
        
        dp = [0] * (S + 1)
        dp[0] = 1
        
        for length in runs:
            mi = length - 1
            new_dp = [0] * (S + 1)
            prefix_sum = 0
            for j in range(S + 1):
                prefix_sum = (prefix_sum + dp[j]) % MOD
                if j > mi:
                    prefix_sum = (prefix_sum - dp[j - mi - 1] + MOD) % MOD
                new_dp[j] = prefix_sum
            dp = new_dp
        
        invalid = sum(dp) % MOD
        result = (total_ways - invalid + MOD) % MOD
        return result