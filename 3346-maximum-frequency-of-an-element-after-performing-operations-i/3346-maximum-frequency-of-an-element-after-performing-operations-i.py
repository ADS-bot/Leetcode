class Solution:
    def maxFrequency(self, nums: List[int], k: int, numOperations: int) -> int:
        d = set()
        for x in nums:
            d.add(x - k)
            d.add(x)
            d.add(x + 1)
            d.add(x + k + 1)
        d = sorted(list(d))
        n = len(d)
        s = [0] * n
        t = [0] * n
        for x in nums:
            i = bisect_left(d, x - k)
            j = bisect_left(d, x)
            l = bisect_left(d, x + 1)
            m = bisect_left(d, x + k + 1)
            s[i] += 1
            s[j] -= 1

            t[j] += 1

            s[l] += 1
            s[m] -= 1
        ans = 0
        for i in range(n):
            if i:
                s[i] += s[i - 1]
            ans = max(ans, t[i] + min(s[i], numOperations))
        return ans