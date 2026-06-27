class Solution:
    def maxTotalValue(self, value: list[int], decay: list[int], m: int) -> int:
        MOD = 10**9 + 7
        tp = 0
        ts = 0
        for v, d in zip(value, decay):
            c = (v - 1) // d + 1
            tp += c
            ts += c * v - d * c * (c - 1) // 2
        if tp <= m:
            return ts % MOD

        def f1(val):
            cnt = 0
            sm = 0
            for v, d in zip(value, decay):
                if v < val:
                    continue
                c = (v - val) // d + 1
                cnt += c
                sm += c * v - d * c * (c - 1) // 2
            return cnt, sm
        lo, hi = 1, max(value)
        while lo < hi:
            mid = (lo + hi+1) // 2
            if f1(mid)[0] >= m:
                lo = mid
            else:
                hi = mid - 1
        sc, sv = f1(hi + 1)
        ans = sv + (m-sc) * hi
        return ans % MOD