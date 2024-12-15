class Solution {
public:
    int buttonWithLongestTime(vector<vector<int>>& events) {
        map<int, int> ans;
        int last = 0;
        for (auto &t : events) {
            int id = t[0];
            int tt = t[1];
            ans[id] = max(ans[id], tt - last);
            last = tt;
        }
        int mx = 0, idx = 0;
        for (auto &t : ans) {
            if (t.second > mx) {
                mx = t.second;
                idx = t.first;
            }
        }
        return idx;
    }
};