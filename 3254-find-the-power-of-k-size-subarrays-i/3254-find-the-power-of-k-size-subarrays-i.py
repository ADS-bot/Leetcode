class Solution:
    def resultsArray(self, nums: List[int], k: int) -> List[int]:
        if k == 1:
            return nums
        l = []
        bad = 0
        for i in range(len(nums)):
            if i:
                bad += nums[i] != nums[i-1] + 1
            if i >= k:
                bad -= nums[i - k] + 1 != nums[i - k + 1]
            if i >= k - 1:
                l.append(nums[i] if bad == 0 else -1)
        return l