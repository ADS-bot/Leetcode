class Solution {
public:
    long long maxRatings(vector<vector<int>>& u) {
        long long s = 0, t = 0;
        int m = 1e6, g = 1e6, h = 1e6, k = 1e6;

        for (auto& r : u) {
            int a = 1e6, b = 1e6;

            for (int x : r)
                x < a ? (b = a, a = x) : x < b ? b = x : 0;

            int d = b < 1e6 && b > a ? b - a : 0;
            int e = a + d;

            s += a;
            t += d;

            a < m
                ? (k = min(k, h), m = a, g = d, h = e)
                : a == m
                    ? (g = min(g, d), h = min(h, e))
                    : k = min(k, e);
        }

        return s + t - min((long long)g, (long long)k - m);
    }
};