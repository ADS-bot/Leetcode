class Solution {
public:
    vector<int> shortestDistanceAfterQueries(int n, vector<vector<int>>& queries) {
        int f[505];
        memset(f,127/3,sizeof f);
        vector<int> oo;
        vector<int> L[505];
        for(int i=0;i<queries.size();++i) {
            L[queries[i][1]].push_back(queries[i][0]);
            int pt=0;
            f[0]=0;
            for(int j=i?queries[i][1]:1;j<n;++j) {
                f[j]=min(f[j],f[j-1]+1);
                for(auto l:L[j]) {
                    f[j]=min(f[j],f[l]+1);
                }
            }
            oo.push_back(f[n-1]);
        }
        return oo;
    }
};