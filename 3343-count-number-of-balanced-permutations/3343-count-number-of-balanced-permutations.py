M = 10**9+7
F = [1] * 100
for i in range(1, len(F)):
    F[i] = F[i-1]*i%M
I = [pow(x, -1, M) for x in F]
class Solution:
    def countBalancedPermutations(self, num: str) -> int:
        a = [0] * 10
        for n in map(int, num):
            a[n] += 1
        s, t = len(num[::2]), len(num[1::2])
        @cache
        def f(i, d, m, n):
            if m > s or n > t: return 0
            if i == 10:
                return int(d == 0)
            r = 0
            for j in range(a[i]+1):
                k = a[i] - j
                r += I[j] * I[k] * f(i+1, d+i*(j-k), m+j, n+k) % M
            return r % M
        return F[s] * F[t] * f(0, 0, 0, 0) % M