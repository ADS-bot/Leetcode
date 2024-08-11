class Solution {
public:
    int countGoodNodes(vector<vector<int>>& e) {
        int n = e.size()+1;
        vector<vector<int>>g(n);
        for (vector<int> v : e) {
            int i=v[0], j=v[1];
            g[i].push_back(j), g[j].push_back(i);
        }
        vector<int> s(n);
        int good=0;
        auto dfs = [&](auto dfs, int p, int i)  -> void {
            s[i] = 1;
            int f = -1, ok = 1;
            for (int j : g[i]) if (p != j) {
                dfs(dfs, i, j);
                s[i] += s[j];
                if (~f && f != s[j]) ok = 0;
                f = s[j];
            }
            good += ok;
        };
        dfs(dfs, -1, 0);
        return good;
    }
};
