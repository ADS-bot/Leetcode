from typing import List

class Solution:
    def leftRightDifference(self, nums: List[int]) -> List[int]:
        n = len(nums)
        
        # Initialize leftSum and rightSum arrays with zeros
        leftSum = [0] * n
        rightSum = [0] * n
        
        # Fill leftSum array
        for i in range(1, n):
            leftSum[i] = leftSum[i-1] + nums[i-1]
        
        # Fill rightSum array
        for i in range(n-2, -1, -1):
            rightSum[i] = rightSum[i+1] + nums[i+1]
        
        # Compute the answer array
        answer = [0] * n
        for i in range(n):
            answer[i] = abs(leftSum[i] - rightSum[i])
        
        return answer