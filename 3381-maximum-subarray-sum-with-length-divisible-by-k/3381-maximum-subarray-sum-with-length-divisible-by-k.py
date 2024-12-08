class Solution:
    def maxSubarraySum(self, nums, k):
        n = len(nums)
        prefix = [0]*(n+1)
        for i in range(n):
            prefix[i+1] = prefix[i] + nums[i]
        
        minPrefix = [float('inf')]*k
        minPrefix[0] = 0
        ans = float('-inf')
        
        for i in range(1, n+1):
            r = i % k
            if minPrefix[r] != float('inf'):
                cur = prefix[i] - minPrefix[r]
                if cur > ans:
                    ans = cur
            if prefix[i] < minPrefix[r]:
                minPrefix[r] = prefix[i]
        
        return ans