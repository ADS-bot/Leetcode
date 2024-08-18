class Solution {
    int solve(int index,int currSum,int n,int k,vector<vector<int>>&dp,vector<int>&mul)
    {
        if(index>n-1-index)
            return (currSum==0);
        if(dp[index][currSum]!=-1)
            return dp[index][currSum];
        dp[index][currSum]=0;
        for(int j=(index?0:1);j<=9;j++)
        {
            int ncurrSum=currSum;
            ncurrSum=(ncurrSum+ j*mul[n-1-index] )%k;
            if(n-1-index != index)
                ncurrSum=(ncurrSum+ j*mul[index] )%k;
            dp[index][currSum]=std::max(dp[index][currSum],solve(index+1,ncurrSum,n,k,dp,mul));
        }
        return dp[index][currSum];
    }
    void solve2(int index,int currSum,int n,int k,vector<vector<int>>&dp,string& ans,vector<int>&mul)
    {
        if(index>n-1-index)
            return;
        
        for(int j=9;;j--)
        {
            int ncurrSum=currSum;
            ncurrSum=(ncurrSum+ j*mul[n-1-index] )%k;
            if(n-1-index != index)
                ncurrSum=(ncurrSum+ j*mul[index] )%k;
            if(solve(index+1,ncurrSum,n,k,dp,mul))
            {
                ans+=(char)('0'+j);
                solve2(index+1,ncurrSum,n,k,dp,ans,mul);
                return;
            }
        }
        
    }
public:
    string largestPalindrome(int n, int k) {
        vector<int> mul;
        int factor=1;
        for(int i=0;i<=n;i++)
        {
            mul.push_back(factor);
            factor=(factor*10)%k;
        }
        
        vector<vector<int>> dp(n,vector<int>(k,-1));
        solve(0,0,n,k,dp,mul);
        string ans="";
        solve2(0,0,n,k,dp,ans,mul);
        
        for(int j=n/2-1;j>=0;j--)
            ans+=ans[j];
        
        return ans;
    }
};