class Solution:
    def maximalNetworkRank(self, n: int, edges: List[List[int]]) -> int:
        
        cities = [0] * n
        
        for a,b in edges:
            cities[a] += 1
            cities[b] += 1
        
        s = set()
        for e in edges:
            s.add(tuple(sorted(e)))
        
        res = 0
        for u in range(n):
            for v in range(u+1, n):
                ans = cities[u] + cities[v]
                ans -= tuple(sorted([u,v])) in s
                res = max(res, ans)
    
        return res

