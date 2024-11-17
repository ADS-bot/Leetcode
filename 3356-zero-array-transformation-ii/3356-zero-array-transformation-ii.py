from typing import List


class Solution:
    def minZeroArray(self, nums: List[int], queries: List[List[int]]) -> int:
        n = len(nums)
        q = len(queries)

        def helper(m):
            actions = []
            for i in range(m):
                left, right, val = queries[i]
                actions.append((left, val))
                actions.append((right + 1, -val))
            actions.sort()
            tmp = [0] * n
            idx = 0
            a = len(actions)
            add = 0
            for i in range(n):
                while idx < a and actions[idx][0] == i:
                    add += actions[idx][1]
                    idx += 1
                tmp[i] = add
                if idx == a:
                    break
            return all(t >= num for num, t in zip(nums, tmp))

        if not helper(q):
            return -1

        left = 0
        right = q
        while left < right:
            mid = (left + right) >> 1
            if helper(mid):
                right = mid
            else:
                left = mid + 1
        return left