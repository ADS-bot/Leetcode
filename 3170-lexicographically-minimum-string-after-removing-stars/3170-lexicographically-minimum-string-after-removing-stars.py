class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        poss = set()
        ans = abs(nums[0]-k)
        for i in range(len(nums)):
            next_pos = set()
            for p in poss:
                next_pos.add(p&nums[i])
            next_pos.add(nums[i])

            for i in next_pos:
                ans = min(ans, abs(i-k))
            
            poss = next_pos
        
        return ans