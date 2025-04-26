

class Solution {
public:
    int maxProfit(int n, vector<vector<int>>& edges, vector<int>& score) {
        vector<int> pre(n, 0);
        for (auto& e : edges)
            pre[e[1]] |= (1 << e[0]);
        int N = 1 << n;
        vector<long long> dp(N, -1);
        dp[0] = 0;
        for (int mask = 0; mask < N; mask++) {
            if (dp[mask] < 0) continue;
            int cnt = __builtin_popcount(mask);
            for (int i = 0; i < n; i++) {
                if (mask & (1 << i)) continue;
                if ((mask & pre[i]) != pre[i]) continue;
                int next = mask | (1 << i);
                dp[next] = max(dp[next], dp[mask] + (long long)(cnt + 1) * score[i]);
            }
        }
        return (int)dp[N - 1];
    }
};