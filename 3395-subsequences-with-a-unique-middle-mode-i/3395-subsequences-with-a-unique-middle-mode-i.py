from collections import defaultdict
from typing import List
class Solution:
    memo = []
    inv = []
    def subsequencesWithMiddleMode(self, nums: List[int]) -> int:
        memo = Solution.memo
        inv = Solution.inv
        divisor = 1000000007
        if not memo:
            memo.append(1)
            inv.append(1)
            for i in range(1, 1001):
                memo.append(memo[-1] * i % divisor)
                inv.append(inv[-1] * pow(i, divisor - 2, divisor) % divisor)
        left = [defaultdict(int)]
        n = len(nums)
        for i in range(1, n):
            left.append(left[-1].copy())
            left[-1][nums[i - 1]] += 1
        right = [defaultdict(int)]
        for i in range(n - 2, -1, -1):
            right.append(right[-1].copy())
            right[-1][nums[i + 1]] += 1
        right.reverse()
        ans = 0
        for i in range(2, n - 2):
            l = left[i]
            r = right[i]
            num = nums[i]
            ll = i - l[num]
            rr = n - i - 1 - r[num]
            if l[num] == r[num] == 0:
                continue
            if l[num] >= 2 and r[num] >= 2:
                ans = (ans + l[num] * (l[num] - 1) // 2 * r[num] * (r[num] - 1) // 2) % divisor
            if l[num] >= 2 and r[num] >= 1:
                ans = (ans + l[num] * (l[num] - 1) // 2 * r[num] * rr) % divisor
            if l[num] >= 1 and r[num] >= 2:
                ans = (ans + l[num] * ll * r[num] * (r[num] - 1) // 2) % divisor
            if l[num] >= 2:
                ans = (ans + l[num] * (l[num] - 1) // 2 * rr * (rr - 1) // 2) % divisor
            if r[num] >= 2:
                ans = (ans + ll * (ll - 1) // 2 * r[num] * (r[num] - 1) // 2) % divisor
            if l[num] >= 1 and r[num] >= 1:
                ans = (ans + l[num] * ll * r[num] * rr) % divisor
            if l[num] >= 1 and ll >= 1 and rr >= 2:
                tmp = ll * rr * (rr - 1) // 2
                for k, v2 in r.items():
                    if k == num:
                        continue
                    v = l[k]
                    if v >= 1 and v2 >= 2:
                        tmp -= v * v2 * (v2 - 1) // 2
                    if v >= 1 and v2 >= 1:
                        tmp -= v * v2 * (rr - v2)
                    if v2 >= 2:
                        tmp -= (ll - v) * v2 * (v2 - 1) // 2
                ans = (ans + l[num] * tmp) % divisor
            if ll >= 2 and r[num] >= 1 and rr >= 1:
                tmp = ll * (ll - 1) // 2 * rr
                for k, v2 in l.items():
                    if k == num:
                        continue
                    v = r[k]
                    if v >= 1 and v2 >= 2:
                        tmp -= v * v2 * (v2 - 1) // 2
                    if v >= 1 and v2 >= 1:
                        tmp -= v * v2 * (ll - v2)
                    if v2 >= 2:
                        tmp -= (rr - v) * v2 * (v2 - 1) // 2
                ans = (ans + r[num] * tmp) % divisor
        return ans