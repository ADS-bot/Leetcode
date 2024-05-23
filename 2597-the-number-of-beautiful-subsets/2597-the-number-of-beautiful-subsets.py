class Solution:
  def beautifulSubsets(self, nums: List[int], k: int) -> int:
    count = collections.Counter(nums)
    modToSubset = collections.defaultdict(set)

    for num in nums:
      modToSubset[num % k].add(num)

    prevNum = -k
    skip = 0
    pick = 0

    for subset in modToSubset.values():
      for num in sorted(subset):
        nonEmptyCount = 2**count[num] - 1
        skip, pick = skip + pick, nonEmptyCount * \
            (1 + skip + (0 if num - prevNum == k else pick))
        prevNum = num

    return skip + pick