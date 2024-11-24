from typing import *


class Solution:
    def minimumSumSubarray(self, nums: List[int], l: int, r: int) -> int:
        n = len(nums)
        ans = -1
        for i in range(n):
            length = 0
            s = 0
            for j in range(i, n):
                length += 1
                s += nums[j]
                if l <= length <= r and s > 0:
                    if ans == -1 or ans > s:
                        ans = s
        return ans