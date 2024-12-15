class Solution:
    def makeStringGood(self, s: str) -> int:
        cs = [0]*26
        for c in s:
            c = ord(c)-ord('a')
            cs[c]+=1
        def count(m):
            dp = [[0]*27 for _ in range(2)]
            i = 25
            while i>=0:
                # 0
                if cs[i]>=m:
                    t = cs[i]-m+dp[1][i+1]
                else:
                    c = cs[i]
                    if i:
                        c+=cs[i-1]
                    if c>=m:
                        t = dp[1][i+1]
                    else:
                        t = m-c+dp[1][i+1]
                t = min(t, cs[i]+dp[0][i+1])
                dp[0][i]=t
                # 1
                if cs[i]>=m:
                    t = cs[i]-m+dp[1][i+1]
                else:
                    c=cs[i]
                    if i and cs[i-1]>m: c+=cs[i-1]-m
                    if c>=m:
                        t = dp[1][i+1]
                    else:
                        t = m-c+dp[1][i+1]
                t = min(t, cs[i]+dp[0][i+1])
                dp[1][i]=t
                i-=1
            return dp[0][0]
            r = 0
            ccs = [0]*26
            for k in range(26): ccs[k]=cs[k]
            for k in range(26):
                c = ccs[k]
                if c==0: continue
                if c>=m:
                    ccs[k]-=m
                else:
                    t = ccs[k]
                    if k and ccs[k-1]>0:
                        d = min(ccs[k-1], m-c)
                        nt = m-c-d
                        if t>nt:
                            t=m-c
                            ccs[k-1]-=d
                    else:
                        t = min(t, m-c)
                    r+=t
                    ccs[k]=0
            for k in range(26):
                r+=ccs[k]
            return r
        n = len(s)
        r = count(1)
        for i in range(2, n+1):
            r = min(r, count(i))
        return r
                    
                        
                
        