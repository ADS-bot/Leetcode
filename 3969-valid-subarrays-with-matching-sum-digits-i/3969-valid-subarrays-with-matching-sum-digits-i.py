class Solution:
    def countValidSubarrays(self, nums: list[int], x: int) -> int:
        n = len(nums)
        ans = 0
        cx = str(x)
        for i in range(n):
            s = 0
            for j in range(i, n):
                s += nums[j]
                t = str(s)
                if t[0] == cx and t[-1] == cx:
                    ans += 1
        return ans