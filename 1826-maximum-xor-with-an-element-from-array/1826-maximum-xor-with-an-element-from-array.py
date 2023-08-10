class TrieNode:
  def __init__(self):
    self.children: List[Optional[TrieNode]] = [None] * 2


class BitTrie:
  def __init__(self, maxBit: int):
    self.maxBit = maxBit
    self.root = TrieNode()

  def insert(self, num: int) -> None:
    node = self.root
    for i in range(self.maxBit, -1, -1):
      bit = num >> i & 1
      if not node.children[bit]:
        node.children[bit] = TrieNode()
      node = node.children[bit]

  def getMaxXor(self, num: int) -> int:
    maxXor = 0
    node = self.root
    for i in range(self.maxBit, -1, -1):
      bit = num >> i & 1
      toggleBit = bit ^ 1
      if node.children[toggleBit]:
        maxXor = maxXor | 1 << i
        node = node.children[toggleBit]
      elif node.children[bit]:
        node = node.children[bit]
      else:  # Nothing in the Bit Trie.
        return 0
    return maxXor


class Solution:
  def maximizeXor(self, nums: List[int], queries: List[List[int]]) -> List[int]:
    ans = [-1] * len(queries)
    queryAndIndexes = sorted([(query, i) for i, query in enumerate(queries)],
                             key=lambda x: x[0][1])
    maxBit = int(math.log2(max(max(nums), max(x for x, _ in queries))))
    bitTrie = BitTrie(maxBit)

    nums.sort()

    i = 0  # nums' index
    for (x, m), index in queryAndIndexes:
      while i < len(nums) and nums[i] <= m:
        bitTrie.insert(nums[i])
        i += 1
      if i > 0 and nums[i - 1] <= m:
        ans[index] = bitTrie.getMaxXor(x)

    return ans
