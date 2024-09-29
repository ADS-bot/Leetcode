class Solution {
public:
    int countOfSubstrings(string word, int K) {
        int n = word.size();

        auto check = [&](char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        };

        auto calc = [&](int K) {
            long long ret = 0;
            int cnt[26] = {0}, good = 0, x = 0;
            for (int i = 0, j = 0; i < n; i++) {
                while (j < n && (good < 5 || x < K)) {
                    if (check(word[j])) {
                        int t = cnt[word[j] - 'a']++;
                        if (t == 0) good++;
                    } else {
                        x++;
                    }
                    j++;
                }
                if (good == 5 && x >= K) ret += n - j + 1;
                if (check(word[i])) {
                    int t = --cnt[word[i] - 'a'];
                    if (t == 0) good--;
                } else {
                    x--;
                }
            }
            return ret;
        };
        return calc(K) - calc(K + 1);
    }
};