class Solution {
public:
    bool canBeEqual(vector<int>& B, vector<int>& A) {
        unordered_map<int, int> cnt;
        for (int n : A) cnt[n]++;
        for (int n : B) {
            if (--cnt[n] < 0) return false;
        }
        return true;
    }
};