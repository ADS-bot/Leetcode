class Solution:
    def maxRatings(self, units: list[list[int]]) -> int:
        if len(units[0]) == 1:
            return sum(row[0] for row in units)
        
        sum_u2 = 0
        min_u2 = float('inf')
        min_u1 = float('inf')
        
        for row in units:
            u1 = float('inf')
            u2 = float('inf')
            for val in row:
                if val < u1:
                    u2, u1 = u1, val
                elif val < u2:
                    u2 = val
                    
            sum_u2 += u2
            if u2 < min_u2:
                min_u2 = u2
            if u1 < min_u1:
                min_u1 = u1
                
        return sum_u2 - min_u2 + min_u1