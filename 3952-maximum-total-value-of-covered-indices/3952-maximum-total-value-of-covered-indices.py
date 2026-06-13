class Solution:
    def maxTotal(self, A: List[int], s: str) -> int:
        n = len(A)
        res = i = 0

        while i < n:
            if s[i] =='0':
                i += 1
                continue

            l = i - 1 if i else i
            j = i
            while j < n and s[j] == '1':
                j += 1

            B = A[l:j]
            res += sum(B)
            if i:
                res -= min(B)
            i = j
        
        return res