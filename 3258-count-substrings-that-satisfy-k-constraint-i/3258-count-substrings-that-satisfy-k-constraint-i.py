class Solution:
    def countKConstraintSubstrings(self, s: str, k: int) -> int:
        n, ans = len(s), 0
        
        for i in range(n):
            a, b = 0, 0
            for j in range(i, n):
                if s[j] == '1':
                    b = b+1
                else:
                    a = a+1
                
                if a <= k or b <= k:
                    ans += 1
                else:
                    break
        
        return ans