class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        res = 0
        N = len(nums)
        for i in range(N):
            for j in range(i + 1, N):
                res = max(res, (nums[i] - 1) * (nums[j] - 1))
        return res