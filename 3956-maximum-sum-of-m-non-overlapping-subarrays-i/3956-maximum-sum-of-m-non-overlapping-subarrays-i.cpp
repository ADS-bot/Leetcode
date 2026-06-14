class Solution {
public:
    long long maximumSum(vector<int>& nums, int m, int l, int r) {
        int n = nums.size();
        const long long NEG = -(1LL << 60);

        vector<long long> pref(n + 1, 0);
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums[i];
        }

        vector<long long> prev(n + 1, 0);
        long long ans = NEG;

        for (int used = 1; used <= m; used++) {
            vector<long long> cur(n + 1, NEG);
            deque<int> dq;

            auto val = [&](int idx) {
                return prev[idx] - pref[idx];
            };

            for (int i = 1; i <= n; i++) {
                int add = i - l;
                if (add >= 0 && prev[add] != NEG) {
                    while (!dq.empty() && val(dq.back()) <= val(add)) {
                        dq.pop_back();
                    }
                    dq.push_back(add);
                }

                while (!dq.empty() && dq.front() < i - r) {
                    dq.pop_front();
                }

                cur[i] = cur[i - 1];

                if (!dq.empty()) {
                    cur[i] = max(cur[i], pref[i] + val(dq.front()));
                }
            }

            ans = max(ans, cur[n]);
            prev = cur;
        }

        return ans;
    }
};