class Solution:
    def timeTaken(self, edges: List[List[int]]) -> List[int]:
        n=len(edges)+1
        m=[[] for _ in range(n)]
        for a,b in edges:
            m[a].append(b)
            m[b].append(a)
        res=[0]*n
        @lru_cache(None)
        def dp(i,prev):
            res=0
            for j in m[i]:
                if j==prev: continue
                res = max(res, dp(j,i))
            res += 2-(i%2)
            # print(i, res)
            return res
        dp(0,0)
        def dp2(i,prev,an):
            x,y = (-1,an), (-2,0)
            for j in m[i]:
                if j==prev: continue
                z = dp(j,i)
                # if i!=0: z+=(2-(prev%2))
                if z>=x[1]: x,y = (j, z), x
                elif z>= y[1]: y = (j,z)
            # print(i, an, x, y)
            res[i] = x[1]
            for j in m[i]:
                if j==prev: continue
                if j!=x[0]: dp2(j, i, x[1] + (2-i%2))
                else: dp2(j, i, y[1] + (2-i%2))
        dp2(0,0,0)
        return res