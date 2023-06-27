class Solution {
public:
    bool isSolvable(vector<string>& words, string result) {
        unordered_set<char> charSet;
        int charCnt[91] = {};
        bool nonLeadingZero[91] = {};
        for (string word : words) {
            int n = word.size();
            for (int i = 0; i < n; ++i) {
                if (i == 0 && n > 1) nonLeadingZero[word[i]] = true;
                charSet.insert(word[i]);
                charCnt[word[i]] += pow(10, n - 1 - i);
            }
        }
        int n = result.size();
        for (int i = 0; i < n; ++i) {
            if (i == 0 && n > 1) nonLeadingZero[result[i]] = true;
            charSet.insert(result[i]);
            charCnt[result[i]] -= pow(10, n - 1 - i);
        }
        bool used[10] = {};
        char charList[charSet.size()];
        int i = 0;
        for (char c : charSet) {
            charList[i++] = c;
        }
        return dfs(charList, charCnt, 0, 0, nonLeadingZero, used, i);
    }
    bool dfs(char* charList, int* charCnt, int cur, int sum, bool* nonLeadingZero, bool* used, int numChars) {
        if (cur == numChars) return sum == 0;
        for (int i = 0; i <= 9; ++i) {
            char c = charList[cur];
            if (used[i] || (i == 0 && nonLeadingZero[charList[cur]])) continue;
            used[i] = true;
            if (dfs(charList, charCnt, cur + 1, sum + charCnt[charList[cur]] * i, nonLeadingZero, used, numChars)) return true;
            used[i] = false;
        }
        return false;
    }
};
