class Solution {
public:
    vector<int> dp;
    vector<vector<int>> adj;
    vector<int> ans;

    void dfs( int node , int par ){
        if( adj[node].size() == 1 && par != -1 ){
            dp[node] = 0;
            return;
        }
        int val = 0;
        for( auto i : adj[node] ){
            if( i == par ) continue;
            dfs( i , node );
            val = max( val , dp[i] + ( i%2 ? 1 : 2 ) );
        }
        dp[node] = val;
    }

    void rerooting( int node, int par ){
        ans[node] = dp[node];

        multiset<int> s;
        for( auto i : adj[node] ){
            s.insert(dp[i] + ( i%2 ? 1 : 2 ));
        }
        int prevnode = dp[node];

        for( auto i : adj[node] ){
            if( i == par ) continue;
            int prev = dp[i];
            int val = dp[i] + ( i%2 ? 1 : 2 );
            s.erase(s.find(val));
            int mx = ( s.size() ? *(--s.end()) : 0 );
            dp[node] = mx;
            dp[i] = max( dp[i], dp[node] + ( node%2 ? 1 : 2 ) );
            rerooting(i,node);
            dp[i] = prev;
            dp[node] = prevnode;
            s.insert(val);
        }
    }


    vector<int> timeTaken(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        dp.resize(n);
        adj.resize(n);
        ans.resize(n);
        for( auto i : edges ){
            adj[i[0]].push_back(i[1]);
            adj[i[1]].push_back(i[0]);
        }
        dfs(0,-1);
        rerooting(0,-1);
        return ans;
    }
};