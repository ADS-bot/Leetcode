class Solution:
    def maxScore(self, A: List[int], V: int) -> int:
        U = max(max(A), V)
        C = [0] * (U + 1)
        for x in A:
            C[x] += 1

        spf = list(range(U + 1))
        for i in range(2, int(U**0.5) + 1):
            if spf[i] == i:
                for j in range(i * i, U + 1, i):
                    if spf[j] == j:
                        spf[j] = i

        D = [0] + [sum(C[d::d]) for d in range(1, U + 1)]

        def gao(x):
            q = [(1, -1)]
            while x > 1:
                p = spf[x]
                q += [(v * p, -s) for v, s in q]
                while x % p == 0:
                    x //= p
            return sum(s * D[v] for v, s in q[1:])

        res = 0
        for x in set(A) | set(range(1, V + 1)):
            b = gao(x)
            cost = b - (x > 1) if C[x] else b + (b == 0)
            res = max(res, x - cost)

        return res