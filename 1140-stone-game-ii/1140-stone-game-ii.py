class Solution:
    def stoneGameII(self, piles: List[int]) -> int:
        N = len(piles)
        @cache
        def dp(i,turn,m):
            if i>=N:
                return 0
            if turn:
                temp = 0
                cum = 0
                for j in range(2*m):
                    if i+j>=N:
                        break
                    cum+=piles[i+j]
                    temp = max(temp,cum+dp(i+j+1,False,max(m,j+1)))
            else:
                temp = inf
                for j in range(2*m):
                    if i+j>=N:
                        break
                    temp = min(temp,dp(i+j+1,True,max(m,j+1)))
            return temp
        return dp(0,True,1)