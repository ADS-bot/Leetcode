class Solution:
    def minOperations(self, queries: List[List[int]]) -> int:
        t = 0
        w = queries
        
        for l, r in w:
            s = 0
            k = 1
            while True:
                lower_bound = 4 ** (k - 1)
                upper_bound = 4 ** k - 1
                
                if lower_bound > r:
                    break
                
                L = max(l, lower_bound)
                R = min(r, upper_bound)
                if L <= R:
                    c = R - L + 1
                    s += c * k
                k += 1
            
            t += (s + 1) // 2
        
        return t