class Solution {
public:
    int maxRemoval(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size(), q = queries.size();
        sort(queries.begin(), queries.end());
        int now = 0, ans = 0;
        priority_queue<int> pq;
        int f[n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 0, j = 0; i < n; i++) {
            now -= f[i];
            while (j < q && queries[j][0] <= i) pq.push(queries[j][1]), j++;
            while (now < nums[i] && !pq.empty()) {
                int t = pq.top(); pq.pop();
                if (t >= i) {
                    now++;
                    ans++;
                    f[t + 1]++;
                }
            }
            if (now < nums[i]) return -1;
        }
        return q - ans;
    }
};