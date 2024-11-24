class Solution {
public:
    long long maximizeSumOfWeights(vector<vector<int>>& edges, int K) {
        int n = edges.size() + 1;
        vector<int> e[n], v[n];
        for (auto &edge : edges) {
            e[edge[0]].push_back(edge[1]); v[edge[0]].push_back(edge[2]);
            e[edge[1]].push_back(edge[0]); v[edge[1]].push_back(edge[2]);
        }

        long long f[n][2];
        auto dfs = [&](auto &&self, int sn, int fa, int from) -> void {
            vector<long long> vec;
            long long base = 0;
            for (int i = 0; i < e[sn].size(); i++) {
                int fn = e[sn][i];
                if (fn == fa) continue;
                self(self, fn, sn, v[sn][i]);
                base += f[fn][0];
                vec.push_back(f[fn][1] - f[fn][0]);
            }
            
            sort(vec.begin(), vec.end(), greater<long long>());
            long long sm = 0;
            for (int i = 0; i < K - 1 && i < vec.size(); i++) if (vec[i] > 0) sm += vec[i];
            f[sn][1] = base + sm + from;
            if (vec.size() >= K && vec[K - 1] > 0) sm += vec[K - 1];
            f[sn][0] = base + sm;
        };
        dfs(dfs, 0, -1, 0);
        return f[0][0];
    }
};