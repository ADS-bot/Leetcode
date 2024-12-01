class Solution {
public:
    vector<int> maxTargetNodes(vector<vector<int>>& edges1, vector<vector<int>>& edges2, int K) {
        int n = edges1.size() + 1, N = edges2.size() + 1;
        vector<int> e[n], E[N];
        for (auto &edge : edges1) {
            e[edge[0]].push_back(edge[1]);
            e[edge[1]].push_back(edge[0]);
        }
        for (auto &edge : edges2) {
            E[edge[0]].push_back(edge[1]);
            E[edge[1]].push_back(edge[0]);
        }

        auto dfs = [&](auto &&self, int sn, int fa, int rem, vector<int> *e) -> int {
            int ret = 1;
            if (rem > 0) {
                for (int fn : e[sn]) if (fn != fa)
                    ret += self(self, fn, sn, rem - 1, e);
            }
            return ret;
        };
        vector<int> ans(n);
        for (int i = 0; i < n; i++) ans[i] = dfs(dfs, i, -1, K, e);
        if (K > 0) {
            int best = 0;
            for (int i = 0; i < N; i++) best = max(best, dfs(dfs, i, -1, K - 1, E));
            for (int i = 0; i < n; i++) ans[i] += best;
        }
        return ans;
    }
};