class Solution:
    def minTime(self, skill: List[int], mana: List[int]) -> int:
        k = (skill, mana)
        n, m = len(skill), len(mana)
        pre = [0] * n
        pre[0] = skill[0]
        
        for i in range(1, n):
            pre[i] = pre[i-1] + skill[i]
        prev = [pre[i] * mana[0] for i in range(n)]
        
        for j in range(1, m):
            S = prev[0]
            for i in range(1, n):
                cand = prev[i] - pre[i-1] * mana[j]
                if cand > S:
                    S = cand
            for i in range(n):
                prev[i] = S + pre[i] * mana[j]
                
        return prev[-1]