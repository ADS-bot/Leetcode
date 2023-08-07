class Solution:
    def numberOfMatches(self, n: int) -> int:
        match_count=0
        while n>1:
            if n%2==0:
                match_count+=int(n/2)
                
                n=int(n/2)
            else :
                match_count+=int((n-1)/2)
                
                n=int((n-1)/2)+1
                
        return match_count