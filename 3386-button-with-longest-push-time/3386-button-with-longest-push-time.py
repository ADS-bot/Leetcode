class Solution:
    def buttonWithLongestTime(self, events: List[List[int]]) -> int:
        longest = events[0][1]
        lindex = events[0][0]
        
        for (i1, t1), (i2, t2) in zip(events, events[1:]):
            if t2 - t1 > longest:
                longest = t2 - t1
                lindex = i2
            elif t2 - t1 == longest:
                if lindex > i2:
                    lindex = i2
                
        return lindex