class Solution {
public:
    int numberOfSubstrings(string s, int k) {
        int n = s.size(), t = 0;
        for (int i = 0; i < n; ++i) {
            int f[26] = {};
            for (int j = i; j < n; ++j) {
                f[s[j]-'a']++;
                for (int c = 0; c < 26; ++c) {
                    if (f[c] >= k) {
                        t++;
                        break;
                    }
                }
            }
        }
        return t;
    }
};