class Solution:
    def maxScore(self, nums: List[int]) -> int:
        from math import gcd
        n = len(nums)
        max_score = 0
        if n == 0:
            return 0
        prefix_gcd = [0]*n
        prefix_lcm = [0]*n
        prefix_gcd[0] = nums[0]
        prefix_lcm[0] = nums[0]
        for i in range(1, n):
            prefix_gcd[i] = gcd(prefix_gcd[i-1], nums[i])
            prefix_lcm[i] = (prefix_lcm[i-1] * nums[i]) // gcd(prefix_lcm[i-1], nums[i])
        suffix_gcd = [0]*n
        suffix_lcm = [0]*n
        suffix_gcd[n-1] = nums[n-1]
        suffix_lcm[n-1] = nums[n-1]
        for i in range(n-2, -1, -1):
            suffix_gcd[i] = gcd(suffix_gcd[i+1], nums[i])
            suffix_lcm[i] = (suffix_lcm[i+1] * nums[i]) // gcd(suffix_lcm[i+1], nums[i])
        for i in range(-1, n):
            if i == -1:
                GCD = prefix_gcd[n-1]
                LCM = prefix_lcm[n-1]
                if n == 0:
                    continue
            elif n ==1 and i ==0:
                continue
            elif n ==1:
                GCD = nums[0]
                LCM = nums[0]
            elif i == 0:
                GCD = suffix_gcd[1]
                LCM = suffix_lcm[1]
            elif i == n-1:
                GCD = prefix_gcd[n-2]
                LCM = prefix_lcm[n-2]
            else:
                GCD = gcd(prefix_gcd[i-1], suffix_gcd[i+1])
                lcm_left = prefix_lcm[i-1]
                lcm_right = suffix_lcm[i+1]
                LCM = (lcm_left * lcm_right) // gcd(lcm_left, lcm_right)
            if n ==1 and i != -1:
                factor_score = 0
            else:
                factor_score = GCD * LCM
            if factor_score > max_score:
                max_score = factor_score
        return max_score        