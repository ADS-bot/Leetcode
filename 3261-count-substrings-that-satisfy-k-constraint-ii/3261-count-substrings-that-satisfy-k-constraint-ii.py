class Solution:
    def countKConstraintSubstrings(self, s: str, k: int, query: List[List[int]]) -> List[int]:
        n, m = len(s), len(query)
        pre_sum = [0] * (n + 1)
        
        def viable(l, r):
            ln = r - l + 1
            ones = pre_sum[r + 1] - pre_sum[l]
            return ones <= k or ln - ones <= k

        for i in range(n):
            pre_sum[i + 1] = pre_sum[i] + int(s[i])

        lo, rp, cnt = [0] * n, 0, [0] * (n+1)
        for i in range(n):
            while rp < n and viable(i, rp):
                rp += 1
            lo[i] = rp - 1

        for i in range(n):
            cnt[i + 1] = cnt[i] + lo[i] - i + 1

        ret = []
        for q in query:
            L, R = q[0], q[1]
            l, r = L - 1, R + 1
            while l + 1 < r:
                mid = (l + r) // 2
                if lo[mid] <= R:
                    l = mid
                else:
                    r = mid

            ans = cnt[l + 1] - cnt[L]
            if r <= R:
                ans += (R - r + 2) * (R - r + 1) // 2

            ret.append(ans)

        return ret
