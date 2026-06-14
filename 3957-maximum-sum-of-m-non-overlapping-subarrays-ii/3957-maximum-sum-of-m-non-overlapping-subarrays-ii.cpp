class Solution {
public:
    struct State {
        long long val;
        int cnt;
    };

    bool better(State a, State b, bool preferMore) {
        if (a.val != b.val) return a.val > b.val;
        return preferMore ? a.cnt > b.cnt : a.cnt < b.cnt;
    }

    long long maximumSum(vector<int>& nums, int m, int l, int r) {
        int n = nums.size();

        vector<long long> pref(n + 1, 0);
        long long bound = 1;
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums[i];
            bound += llabs((long long)nums[i]);
        }

        auto bestOne = [&]() {
            deque<int> dq;
            long long best = LLONG_MIN / 4;

            for (int i = 1; i <= n; i++) {
                int add = i - l;
                if (add >= 0) {
                    while (!dq.empty() && pref[dq.back()] >= pref[add]) dq.pop_back();
                    dq.push_back(add);
                }

                while (!dq.empty() && dq.front() < i - r) dq.pop_front();

                if (!dq.empty()) {
                    best = max(best, pref[i] - pref[dq.front()]);
                }
            }

            return best;
        };

        long long one = bestOne();
        if (one <= 0) return one;

        auto calc = [&](long long cost, bool preferMore) {
            vector<State> dp(n + 1, {0, 0});
            deque<int> dq;

            auto key = [&](int idx) {
                return State{dp[idx].val - pref[idx], dp[idx].cnt};
            };

            for (int i = 1; i <= n; i++) {
                int add = i - l;
                if (add >= 0) {
                    while (!dq.empty() && better(key(add), key(dq.back()), preferMore)) {
                        dq.pop_back();
                    }
                    dq.push_back(add);
                }

                while (!dq.empty() && dq.front() < i - r) dq.pop_front();

                dp[i] = dp[i - 1];

                if (!dq.empty()) {
                    State bestStart = key(dq.front());
                    State cand = {
                        pref[i] - cost + bestStart.val,
                        bestStart.cnt + 1
                    };

                    if (better(cand, dp[i], preferMore)) {
                        dp[i] = cand;
                    }
                }
            }

            return dp[n];
        };

        State freeTake = calc(0, false);
        if (freeTake.cnt <= m) return freeTake.val;

        long long lo = 0, hi = bound;
        while (lo < hi) {
            long long mid = (lo + hi + 1) / 2;
            if (calc(mid, true).cnt >= m) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        State res = calc(lo, true);
        return res.val + lo * m;
    }
};