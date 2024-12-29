class Solution {
public:
    string answerString(string word, int numFriends) {
        if (numFriends == 1) return word;
        int n = word.size();
        int len = n - numFriends + 1;
        vector<string> v;
        for (int i = 0; i < n; i ++) {
            string ss = "";
            for (int j = i; j < n && j - i + 1 <= len; j ++) ss += word[j];
            v.push_back(ss);
        }
        sort(v.begin(), v.end());
        return *(--v.end());
    }
};