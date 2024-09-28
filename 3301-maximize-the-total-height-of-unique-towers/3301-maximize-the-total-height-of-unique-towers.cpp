class Solution {
public:
    long long maximumTotalSum(vector<int>& a) {
        int n = a.size();
        using ll = long long;
        sort(a.rbegin(), a.rend());
        ll ans = a[0];
        int x = a[0];
        for (int i = 1; i < n; i++) {
            x = min(x - 1, a[i]);
            ans += x;
            if (x <= 0) {
                return -1;
            }
        }
        return ans;
    }
};