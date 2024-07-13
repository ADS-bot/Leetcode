class Solution:
  def isArraySpecial(self, nums: List[int], queries: List[List[int]]) -> List[bool]:
    ans = []
    id = 0
    parityIds = [id]
    for i, (a, b) in enumerate(itertools.pairwise(nums)):
      if a % 2 == b % 2:
        id += 1
      parityIds.append(id)

    for _from, to in queries:
      ans.append(parityIds[_from] == parityIds[to])

    return ans