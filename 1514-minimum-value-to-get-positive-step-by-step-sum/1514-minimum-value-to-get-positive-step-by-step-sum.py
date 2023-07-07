class Solution:
    def minStartValue(self, nums: List[int]) -> int:
        min_sum = 0
        cur_sum = 0
        for num in nums:
            cur_sum += num
            min_sum = min(min_sum, cur_sum)
        return 1 if min_sum > 0 else 1 - min_sum