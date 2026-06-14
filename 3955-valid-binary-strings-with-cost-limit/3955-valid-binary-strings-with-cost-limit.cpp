class Solution {
public:
    vector<string> generateValidStrings(int n, int k) {
        vector<string> ans;
        string cur;

        function<void(int, int, bool)> dfs = [&](int i, int cost, bool prevOne) {
            if (i == n) {
                ans.push_back(cur);
                return;
            }

    
            cur.push_back('0');
            dfs(i + 1, cost, false);
            cur.pop_back();


            if (!prevOne && cost + i <= k) {
                cur.push_back('1');
                dfs(i + 1, cost + i, true);
                cur.pop_back();
            }
        };

        dfs(0, 0, false);
        return ans;
    }
};