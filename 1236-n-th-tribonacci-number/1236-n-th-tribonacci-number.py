class Solution:
    
    def tribonacci(self, n: int) -> int:
        
        t0, t1, t2 = 0, 1, 1
        
        # base case:
        if n == 0 :
            # T( 0 ) = 0
            return t0
        
        # base case:
        if n == 1 or n == 2:
            # T( 1 ) = 1
            # T( 2 ) = 1
            return t1
        
        # general case:
        # T( n ) = T( n-1 ) + T( n-2 ) + T( n-3 )
        for i in range(3, n+1):
            
            t_n = t0 + t1 + t2
            t0 = t1
            t1 = t2
            t2 = t_n
            
        return t_n