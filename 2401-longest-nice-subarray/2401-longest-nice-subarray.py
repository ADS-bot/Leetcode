class Solution:
    def longestNiceSubarray(self, nums: List[int]) -> int:
        a=0
        used=0
        
        l=0
        for r, num in enumerate(nums):
            while used & num:
                used ^= nums[l]
                l+=1
            used|=num
            a=max(a,r-l+1)
        return a
            
        
        