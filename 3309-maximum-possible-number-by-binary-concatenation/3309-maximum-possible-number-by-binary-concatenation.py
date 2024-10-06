import functools
class Solution:
    def maxGoodNumber(self, a: List[int]) -> int:
        
        a=[bin(x)[2:] for x in a]


        def cmp(a,b):
            if int(a+b)>int(b+a):
                return -1
            else:
                return 1
        a.sort(key=functools.cmp_to_key(cmp))
        res=""
        for x in a:
            res+=x
        return int(res,2)