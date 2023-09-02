class Solution {
public:
    bool areAlmostEqual(string a, string b) {
        int cnt[26] = {}, diff = 0;
        for (char c : a) cnt[c - 'a'] ++;
        for (char c : b) cnt[c - 'a']--;
        for (int n : cnt) {
            if (n) return false;
        }
        for (int i = 0; i < a.size(); ++i) {
            if (a[i] != b[i]) ++diff;
        }
        return diff == 0 || diff == 2;
    }
};