class Solution:
    def dfs(self,a,dp,i,d):
        if dp[i]!=0:
            return dp[i]

        mx=1
        n=len(a)

        for j in range(1,d+1):
            if i+j>=n:
                break

            if a[i]>a[i+j]:
                mx=max(mx,1+self.dfs(a,dp,i+j,d))
            else:
                break

        for j in range(1,d+1):
            if i-j<0:
                break

            if a[i]>a[i-j]:
                mx=max(mx,1+self.dfs(a,dp,i-j,d))
            else:
                break

        dp[i]=mx
        return mx

    def maxJumps(self,arr,d):
        n=len(arr)
        dp=[0]*n

        ans=0

        for i in range(n):
            ans=max(ans,self.dfs(arr,dp,i,d))

        return ans