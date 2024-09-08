class Solution {
public:
    int maxMoves(int kx, int ky, vector<vector<int>>& positions) {
        positions.push_back({kx, ky});
        int n = 50, m = 50, w = positions.size();
        
        short dir[8][2] = {1, 2, 2, 1, 1, -2, 2, -1, -1, 2, -2, 1, -1, -2, -2, -1};
        int dis[w][n][m];
        memset(dis, -1, sizeof(dis));

        auto bfs = [&](int S) {
            typedef pair<int, int> pii;
            queue<pii> q;
            q.push({positions[S][0], positions[S][1]});
            dis[S][positions[S][0]][positions[S][1]] = 0;
            while (!q.empty()) {
                pii p = q.front(); q.pop();
                int i = p.first, j = p.second;
                for (int k = 0; k < 8; k++) {
                    int ii = i + dir[k][0], jj = j + dir[k][1];
                    if (ii < 0 || jj < 0 || ii >= n || jj >= m || dis[S][ii][jj] >= 0) continue;
                    q.push({ii, jj});
                    dis[S][ii][jj] = dis[S][i][j] + 1;
                }
            }
        };
        for (int i = 0; i < w; i++) bfs(i);

        int f[1 << w][w];
        memset(f, -1, sizeof(f));
        auto dp = [&](auto &&self, int msk, int last) {
            if (msk == (1 << w) - 1) return 0;
            if (f[msk][last] >= 0) return f[msk][last];
            int cnt = __builtin_popcount(msk);
            int ret = (cnt & 1 ? -1e9 : 1e9);
            for (int i = 0; i < w; i++) if (msk >> i & 1 ^ 1) {
                int t = self(self, msk | (1 << i), i) + dis[last][positions[i][0]][positions[i][1]];
                if (cnt & 1) ret = max(ret, t);
                else ret = min(ret, t);
            }
            return f[msk][last] = ret;
        };
        return dp(dp, 1 << (w - 1), w - 1);
    }
};
