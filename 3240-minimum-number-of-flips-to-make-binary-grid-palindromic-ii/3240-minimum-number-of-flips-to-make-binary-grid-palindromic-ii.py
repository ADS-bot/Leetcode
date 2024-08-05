from collections import Counter
from typing import List


class Solution:
    def minFlips(self, grid: List[List[int]]) -> int:
        m,n=len(grid),len(grid[0])
        res=0
        for i in range(m//2):
            for j in range(n//2):
                ct=Counter([grid[i][j], grid[i][n-1-j],grid[m-1-i][j],grid[m-1-i][n-1-j]])
                res+=min(ct[1],ct[0])-0
        if m%2==1 and n%2==1 and grid[m//2][n//2] == 1:
            res+=1
        pairs = 0
        pairsd = 0
        pairs1 = 0
        if m%2==1:
            pairs += n//2
            for i in range(n//2):
                if grid[m//2][i] != grid[m//2][n-1-i]:
                    pairsd+=1
                elif grid[m//2][i]==1:
                    pairs1+=1
        if n%2==1:
            pairs += m//2
            for i in range(m//2):
                if grid[i][n//2] != grid[m-1-i][n//2]:
                    pairsd+=1
                elif grid[i][n//2]==1:
                    pairs1+=1
        if pairsd!=0:
            res+=pairsd
        elif pairs1%2!=0:
            res+=2
        return res