from collections import Counter
from typing import List

class Solution:
    def getLength(self, nums: List[int]) -> int:
        n = len(nums)
        ml=1
        for i in range(n):
            ct=Counter()
            fm=Counter()
            for j in range(i,n):
                val = nums[j]
                if ct[val]>0:
                    oldf=ct[val]
                    fm[oldf]-=1
                    if fm[oldf]==0:
                        del fm[oldf]
                ct[val]+=1
                newf=ct[val]
                fm[newf]+=1
                cl=j-i+1
                if cl==1:
                    continue
                if len(fm)==1:
                    freq=next(iter(fm))
                    if fm[freq]==1:
                        ml=max(ml,cl)
                elif len(fm)== 2:
                    sf=sorted(fm.keys())
                    minf,maxf=sf[0],sf[1]
                    if maxf==2*minf:
                        ml=max(ml,cl)
            
                    
        return ml