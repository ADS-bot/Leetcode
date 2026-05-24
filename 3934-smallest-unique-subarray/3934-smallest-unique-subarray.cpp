typedef long long ll;
class Solution {
public:
    int smallestUniqueSubarray(vector<int>& nums) {
        int n = nums.size(), lo = 1, hi = n, ans = n;
        auto check = [&](int lmt) -> bool {
            if (lmt <= 0) return 0;
            ll h1 = 0, h2 = 0, p1 = 1, p2 = 1;
            for(int i=0; i<lmt; i++) {
                h1 = (h1 * b1 + nums[i]) % mod1;
                h2 = (h2 * b2 + nums[i]) % mod2;
                if(i + 1 < lmt) { p1 = p1 * b1 % mod1; p2 = p2 * b2 % mod2; }
            }
            vector<ll> hsh;
            hsh.push_back(h1 << 32 | h2);
            for(int i=lmt; i<n; i++) {
                ll x1 = (nums[i-lmt] * p1) % mod1, x2 = (nums[i - lmt] * p2)%mod2;
                h1 = (h1 - x1 + mod1) % mod1; h2 = (h2 - x2 + mod2) % mod2;
                h1 = (h1 * b1 + nums[i]) % mod1; h2 = (h2 * b2 + nums[i]) % mod2;
                hsh.push_back(h1 << 32 | h2);
            }
            sort(hsh.begin(), hsh.end());
            if(hsh.size() == 1 || hsh[0] != hsh[1] || hsh.back() != hsh[(int)hsh.size() - 2]) return 1;
            for(int i=1; i+1 < hsh.size(); i++)
                if(hsh[i-1] != hsh[i] && hsh[i] != hsh[i+1]) return 1;
            return 0;
        };
        while(lo <= hi) {
            int mid = (lo + hi)/2;
            if(check(mid)) { ans = mid; hi = mid - 1; }
            else lo = mid + 1;
        }
        return ans;
    }
    const ll mod1 = 1e9 + 7, mod2 = 1e9 + 9, b1 = 1e6+33, b2 = 1e6 + 39;
};