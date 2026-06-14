import math
from collections import deque
from typing import List

class Solution:
    def maximumSum(self, nums: List[int], m: int, l: int, r: int) -> int:
        n = len(nums)
        pref = [0] * (n + 1)
        for i in range(n):
            pref[i + 1] = pref[i] + nums[i]
            
        prev_dp = [0] * (n + 1)
        ans = -math.inf
        
        for j in range(1, m + 1):
            curr_dp = [-math.inf] * (n + 1)
            dq = deque()
            for i in range(l, n + 1):
                k = i - l
                if prev_dp[k] != -math.inf:
                    val = prev_dp[k] - pref[k]
                    while dq and dq[-1][1] <= val:
                        dq.pop()
                    dq.append((k, val))
                    
                while dq and dq[0][0] < i - r:
                    dq.popleft()
                    
                curr_dp[i] = curr_dp[i - 1]
                if dq:
                    curr_dp[i] = max(curr_dp[i], pref[i] + dq[0][1])
                    
            ans = max(ans, curr_dp[n])
            prev_dp = curr_dp
            
        return ans