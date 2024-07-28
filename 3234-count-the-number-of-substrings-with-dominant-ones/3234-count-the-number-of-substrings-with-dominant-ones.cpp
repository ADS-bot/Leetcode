class Solution {
public:
    int numberOfSubstrings(string s) {
        int ans = 0, n = s.size();
        vector<int> pref(n);
        for (int i = 0; i < n; ++i) {
            pref[i] = s[i] == '1';
            if (i) pref[i] += pref[i-1];
        }
        
        vector<int> zeros;
        for (int i = 0; i < n; ++i) {
            if (s[i] == '0') zeros.push_back(i);
            
            for (int k = 1; k <= size(zeros); ++k) {
                if (k*k >= i+2) break;
                
                int R = zeros[size(zeros) - k];
                int L = -1;
                if (k < size(zeros)) L = zeros[size(zeros) - k - 1];
                int lo = 0;
                if (L >= 0) lo = pref[L];
                
                int hi = pref[R];
                ans += max(0, min(hi, pref[i] - k*k) - lo + 1);
            }
            
            if (s[i] == '1') {
                if (size(zeros) == 0) ans += i+1;
                else ans += i - zeros.back();
            }
        }
        return ans;
    }
};