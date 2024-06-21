class Solution:
    def lastVisitedIntegers(self, nums):
        seen = []
        ans = []
        k = 0  # Counter for consecutive -1s

        for num in nums:
            if num != -1:
                seen.insert(0, num)
                k = 0  # Reset k since we encountered a positive integer
            else:
                k += 1
                if k <= len(seen):
                    ans.append(seen[k - 1])
                else:
                    ans.append(-1)
        
        return ans
