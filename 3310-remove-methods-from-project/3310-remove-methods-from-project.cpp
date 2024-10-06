class Solution {
public:
    vector<int> remainingMethods(int n, int k, vector<vector<int>>& invocations) {
        vector<vector<int>> adjl(n);
        for (auto &e : invocations) {
            adjl[e[0]].push_back(e[1]);
        }

        vector<bool> sus(n, false);
        auto dfs = [&](auto &self, int u) -> void {
            if (sus[u]) return;
            sus[u] = true;
            for (int v : adjl[u]) self(self, v);
        };
        dfs(dfs, k);

        vector<int> ans;
        for (int i=0; i<n; i++) {
            if (sus[i]) continue;
            bool ok = true;
            for (int v : adjl[i]) {
                if (sus[v]) ok = false;
            }
            if (!ok) {
                ans.clear();
                for (int j=0; j<n; j++) ans.push_back(j);
                return ans;
            }
            ans.push_back(i);
        }

        return ans;
    }
};