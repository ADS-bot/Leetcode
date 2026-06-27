class Solution {
public:
    long long countAtLeast(vector<int>& value, vector<int>& decay, long long lam) {
        long long total = 0;
        int n = value.size();
        for (int i = 0; i < n; i++) {
            if (value[i] < lam) continue;
            long long t = (value[i] - lam) / decay[i];
            total += t + 1;
        }
        return total;
    }

    pair<long long,long long> countAndSumAtLeast(vector<int>& value, vector<int>& decay, long long lam) {
        long long cnt = 0, sum = 0;
        int n = value.size();
        for (int i = 0; i < n; i++) {
            if (value[i] < lam) continue;
            long long t = (value[i] - lam) / decay[i];
            long long c = t + 1;
            long long first = value[i];
            long long last = value[i] - t * (long long)decay[i];
            cnt += c;
            sum += c * (first + last) / 2;
        }
        return {cnt, sum};
    }

    int maxTotalValue(vector<int>& value, vector<int>& decay, int m) {
        int n = value.size();
        long long M = m;
        long long totalAvailable = 0, maxVal = 0;
        for (int i = 0; i < n; i++) {
            totalAvailable += (long long)value[i] / decay[i] + 1;
            maxVal = max(maxVal, (long long)value[i]);
        }
        long long K = min(M, totalAvailable);

        long long lo = 0, hi = maxVal;
        while (lo < hi) {
            long long mid = lo + (hi - lo + 1) / 2;
            if (countAtLeast(value, decay, mid) >= K) lo = mid;
            else hi = mid - 1;
        }
        long long lambda = lo;

        auto [C1, S1] = countAndSumAtLeast(value, decay, lambda + 1);
        long long remaining = K - C1;
        long long totalSum = S1 + remaining * lambda;

        const long long MOD = 1000000007;
        return (int)(totalSum % MOD);
    }
};