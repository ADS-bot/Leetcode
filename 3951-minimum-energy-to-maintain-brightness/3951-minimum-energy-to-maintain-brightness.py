class Solution:
    def minEnergy(self, n: int, B: int, T: list[list[int]]) -> int:
        res = 0
        r = -1
        for l, e in sorted(T):
            if l > r:
                res += e - l + 1
            elif e > r:
                res += e - r
            r = max(r, e)
        return res * ((B + 2) // 3)