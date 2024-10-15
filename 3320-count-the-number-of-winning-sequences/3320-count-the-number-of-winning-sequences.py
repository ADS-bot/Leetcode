class Solution:
    def countWinningSequences(self, s: str) -> int:
        n = len(s)
        wdict = {'F':'E','W':'F','E':'W'}
        ldict = {'E':'F','F':'W','W':'E'}
        @cache
        def dp(i,last,diff):
            if n - i + diff <=0:return 0
            if i == n:
                if diff > 0:
                    return 1
                else:
                    return 0
            res = 0
            curr = s[i]
            for p in 'WEF':
                if p==last:
                    continue
                if curr == wdict[p]:
                    res += dp(i+1,p,diff+1) % (10**9+7)
                elif curr == ldict[p]:
                    res += dp(i+1,p,diff-1) % (10**9+7)
                else:
                    res += dp(i+1,p,diff) % (10**9+7)
            return res % (10**9+7)
        return dp(0,'',0)