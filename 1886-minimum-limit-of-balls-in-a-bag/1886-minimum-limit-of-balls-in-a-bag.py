class Solution:
  def minimumSize(self, nums: List[int], maxOperations: int) -> int:
    # Returns the # of operations required to make m penalty.
    def numOperations(m: int) -> int:
      return sum((num - 1) // m for num in nums) <= maxOperations
    return bisect.bisect_left(range(1, max(nums)), True,
                              key=lambda m: numOperations(m)) + 1
