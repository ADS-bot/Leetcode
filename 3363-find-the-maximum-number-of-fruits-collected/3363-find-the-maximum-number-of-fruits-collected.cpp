class Solution {
public:
    int maxCollectedFruits(vector<vector<int>>& fruits) {
        int n = fruits.size();

        const long long INF = 1e18;
        long long f[n][n];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) f[i][j] = -INF;

        f[0][n - 1] = fruits[0][n -1];
        for (int i = 1; i < n; i++) for (int j = i + 1; j < n; j++) {
            f[i][j] = max(f[i - 1][j - 1], f[i - 1][j]) + fruits[i][j];
            if (j + 1 < n) f[i][j] = max(f[i][j], f[i - 1][j + 1] + fruits[i][j]);
        }

        f[n - 1][0] = fruits[n - 1][0];
        for (int j = 1; j < n; j++) for (int i = j + 1; i < n; i++) {
            f[i][j] = max(f[i - 1][j - 1], f[i][j - 1]) + fruits[i][j];
            if (i + 1 < n) f[i][j] = max(f[i][j], f[i + 1][j - 1] + fruits[i][j]);
        }

        long long ans = f[n - 2][n - 1] + f[n - 1][n - 2];
        for (int i = 0; i < n; i++) ans += fruits[i][i];
        return ans;
    }
};