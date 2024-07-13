class Solution:
  # Similar to 1521. Find a Value of a Mysterious Function Closest to Target
  def countSubarrays(self, nums: List[int], k: int) -> int:
    ans = 0
    prev = collections.Counter()

    for num in nums:
      curr = collections.Counter({num: 1})
      for val, freq in prev.items():
        curr[val & num] += freq
      ans += curr[k]
      prev = curr

    return ans