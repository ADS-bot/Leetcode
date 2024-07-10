class Solution:
  def minimumMoves(self, nums: List[int], k: int, maxChanges: int) -> int:
    kNumOfIndicesWithinOneDistance = 3
    ans = math.inf
    oneIndices = [i for i, num in enumerate(nums) if num == 1]
    prefix = list(itertools.accumulate(oneIndices, initial=0))

    minOnesByTwo = max(0, k - maxChanges)
    maxOnesByTwo = min(
        k, minOnesByTwo + kNumOfIndicesWithinOneDistance, len(oneIndices))

    for onesByTwo in range(minOnesByTwo, maxOnesByTwo + 1):
      for l in range(len(prefix) - onesByTwo):
        r = l + onesByTwo  # Collect 1s in oneIndices[l - 1..r - 1].
        cost1 = (k - onesByTwo) * 2
        cost2 = (prefix[r] - prefix[(l + r) // 2]) - \
            (prefix[(l + r + 1) // 2] - prefix[l])
        ans = min(ans, cost1 + cost2)

    return ans