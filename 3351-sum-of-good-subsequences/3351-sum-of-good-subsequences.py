mod = 10 ** 9 + 7

class Solution:
    def sumOfGoodSubsequences(self, nums: List[int]) -> int:
        dp = Counter()
        cnt = Counter()
        
        for num in nums:
            v1 = (dp[num - 1] + dp[num + 1] + (cnt[num - 1] + cnt[num + 1] + 1) * num % mod) % mod
            v2 = (cnt[num - 1] + cnt[num + 1] + 1) % mod
            dp[num] += v1
            dp[num] %= mod
            cnt[num] += v2
            cnt[num] %= mod
        
        return sum(dp.values()) % mod