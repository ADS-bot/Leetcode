class Solution:
    def maximumValueSum(self, board: List[List[int]]) -> int:
        n, m = len(board), len(board[0])
        l = []
        for i in range(n):
            cur = [(board[i][j], j) for j in range(m)]
            cur.sort(reverse=True)
            l.append(cur[:3])
        def process(x):
            best = []
            r = []
            for i in x:
                best += i
                best = sorted(best, reverse=True)
                t = []
                for e in best:
                    if any(j[1] == e[1] for j in t):
                        continue
                    t.append(e)
                    if len(t) == 3:
                        break
                best = t[:]
                r.append(best[:])
            return r
        pref, suff = process(l), process(l[::-1])[::-1]
        #print(pref)
        res = -10 ** 18
        for i in range(1, n - 1):
            for e, f in l[i]:
                for a, b in pref[i - 1]:
                    for c, d in suff[i + 1]:
                        if b != d and b != f and d != f:
                            res = max(res, a + c + e)
        return res