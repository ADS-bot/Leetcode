class Solution:
    def maxEnergyBoost(self, a: List[int], b: List[int]) -> int:
        n = len(a)
        da, db = [0] * n, [0] * n
        if n == 1:
            return max(a[0], b[0])
        elif n == 0:
            return 0
        
        da[0], db[0], da[1], db[1] = a[0], b[0], sum(a[:2]), sum(b[:2])
        
        for i in range(2, n):
            da[i] = a[i] + max(da[i-1], db[i-2])
            db[i] = b[i] + max(db[i-1], da[i-2])
        
        ans = max(da[-1], db[-1])
        return ans