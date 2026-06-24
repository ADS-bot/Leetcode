mod = 10**9 + 7

def matmul(A, B):
    size = len(A)
    C = [[0]*size for _ in range(size)]
    for i in range(size):
        for k in range(size):
            if A[i][k]:
                for j in range(size):
                    C[i][j] = (C[i][j] + A[i][k]*B[k][j]) % mod
    return C

def matpow(M, e):
    size = len(M)
    res = [[int(i==j) for j in range(size)] for i in range(size)]
    while e:
        if e & 1:
            res = matmul(res, M)
        M = matmul(M, M)
        e >>= 1
    return res


class Solution:
    def zigZagArrays(self, n: int, l: int, r: int) -> int:
        m = r - l + 1
        if n == 1:
            return m * 2 % mod

        o = [[0] * m for _ in range(m)]
        for i in range(m):
            for j in range(i):
                o[i][j] = 1

        e = [[0] * m for _ in range(m)]
        for i in range(m):
            for j in range(i+1, m):
                e[i][j] = 1


        if (n - 1) % 2 == 0:
            M = matpow(matmul(e, o), (n - 1) // 2)
        else:
            M = matmul(o, matpow(matmul(e, o), (n - 2) // 2))
        dp = [1] * m
        dp2 = [0] * m
        for i in range(m):
            for j in range(m):
                dp2[i] = (dp2[i] + M[i][j] * dp[j]) % mod
        return sum(dp2) * 2 % mod