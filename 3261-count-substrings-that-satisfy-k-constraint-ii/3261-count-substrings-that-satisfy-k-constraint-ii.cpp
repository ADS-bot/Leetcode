class Solution {
public:
    vector<long long> countKConstraintSubstrings(string s, int k, vector<vector<int>>& queries) {
        int n=s.size();
        vector<int> pre(n+1,0);
        for(int i=1;i<=n;i++)
            pre[i]=pre[i-1]+(s[i-1]=='1');
        
        vector<int> left(n+1);
        for(int i=1;i<=n;i++)
        {
            int low=1;
            int high=i;
            int ans=i;
            while(low<=high)
            {
                int mid=(low+high)/2;
                
                int ones=pre[i]-pre[mid-1];
                int zeros=(i-mid+1)-ones;
                
                if(ones<=k || zeros<=k)
                {
                    ans=mid;
                    high=mid-1;
                }
                else
                    low=mid+1;
            }
            
            left[i]=ans;
        }
        
        vector<long long> sol(n+1);
        for(int i=1;i<=n;i++)
            sol[i]=sol[i-1]+left[i];
        
        vector<long long> ans;
        for(auto &query : queries)
        {
            long long l=query[0]+1;
            long long r=query[1]+1;
            
            long long total=((r-l+1)*(r+l))/2ll;
            
            int low=l;
            int high=r;
            int till=l;
            while(low<=high)
            {
                int mid=(low+high)/2;
                if( left[mid]<=l )
                {
                    till=mid;
                    low=mid+1;
                }
                else
                    high=mid-1;
            }
            long long sub=(till-l+1ll)*l +sol[r]-sol[till];
            ans.push_back(total-sub+(r-l+1ll));
        }
        return ans;
    }
};
