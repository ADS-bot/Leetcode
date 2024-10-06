class Solution {
public:
    vector<vector<int>> constructGridLayout(int n, vector<vector<int>>& edges) {
        vector<vector<int>> adjl(n);
        for (auto &e : edges) {
            adjl[e[0]].push_back(e[1]);
            adjl[e[1]].push_back(e[0]);
        }

        vector<int> uj;
        for (int i=0; i<n; i++) {
            if (adjl[i].size() == 1) uj.push_back(i);
        }
        if (!uj.empty()) {
            assert(uj.size() == 2);
            vector<vector<int>> ans(1, vector<int>(n));
            auto dfs = [&](auto &self, int u, int p, int i) -> void {
                ans[0][i] = u;
                for (int v : adjl[u]) {
                    if (v == p) continue;
                    self(self, v, u, i+1);
                }
            };
            dfs(dfs, uj[0], -1, 0);
            return ans;
        }

        vector<int> cor;
        for (int i=0; i<n; i++) {
            if (adjl[i].size() == 2) cor.push_back(i);
        }
        assert(cor.size() == 4);

        bool tipis2 = false;
        sort(cor.begin(), cor.end());
        for (int v : adjl[cor[0]]) {
            if (binary_search(cor.begin(), cor.end(), v)) tipis2 = true;
        }
        if (tipis2) {
            int h = n/2, w = 2;
            vector<vector<int>> ans(h, vector<int>(w));
            ans[0][0] = cor[0];
            for (int v : adjl[cor[0]]) {
                if (binary_search(cor.begin(), cor.end(), v)) ans[0][1] = v;
            }

            vector<bool> udah(n, false);
            udah[ans[0][0]] = udah[ans[0][1]] = true;
            for (int i=1; i<h; i++) {
                for (int j=0; j<w; j++) {
                    for (int v : adjl[ans[i-1][j]]) {
                        if (!udah[v]) {
                            ans[i][j] = v;
                            udah[v] = true;
                        }
                    }
                }
            }

            return ans;
        }

        vector<int> atas;
        auto dfs1 = [&](auto &self, int u, int p) -> void {
            atas.push_back(u);
            if (adjl[u].size() == 2 && u != cor[0]) return;
            for (int v : adjl[u]) {
                if (v == p || adjl[v].size() > 3) continue;
                self(self, v, u);
                return;
            }
        };
        dfs1(dfs1, cor[0], adjl[cor[0]][0]);

        int h = n / atas.size(), w = atas.size();
        vector<vector<int>> ans(h, vector<int>(w));
        ans[0] = atas;

        auto dfs2 = [&](auto &self, int u, int p, int r) -> void {
            ans[r][0] = u;
            if (adjl[u].size() == 2 && u != cor[0]) return;
            for (int v : adjl[u]) {
                if (v == p || adjl[v].size() > 3) continue;
                self(self, v, u, r+1);
                return;
            }
        };
        dfs2(dfs2, cor[0], adjl[cor[0]][1], 0);

        vector<bool> udah(n);
        for (int i=0; i<w; i++) udah[ans[0][i]] = true;
        for (int i=0; i<h; i++) udah[ans[i][0]] = true;
        for (int i=1; i<h; i++) {
            for (int j=1; j<w; j++) {
                for (int u : adjl[ans[i-1][j]]) {
                    if (!udah[u]) {
                        ans[i][j] = u;
                        udah[u] = true;
                    }
                }
            }
        }

        return ans;
    }
};