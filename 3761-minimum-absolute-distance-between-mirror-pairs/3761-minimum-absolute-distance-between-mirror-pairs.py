class Solution:
    def minMirrorPairDistance(self, nums: List[int]) -> int:
        def rev(x):
            r = 0
            while x > 0:
                r = r * 10 + x % 10
                x //= 10
            return r
        last = {}
        mind = float('inf')
        for j in range(len(nums)):
            if nums[j] in last:
                mind = min(mind, j - last[nums[j]])
            rj = rev(nums[j])
            last[rj] = j
        return -1 if mind == float('inf') else mind