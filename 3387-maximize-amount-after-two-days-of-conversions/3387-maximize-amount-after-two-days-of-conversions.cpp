class Solution {
public:
    map<string, double> calc(string s0, vector<vector<string>>& pairs, vector<double>& rates) {
        map<string, vector<pair<string, double>>> e;
        int n = pairs.size();
        for (int i = 0; i < n; i++) {
            string s1 = pairs[i][0], s2 = pairs[i][1];
            e[s1].emplace_back(s2, rates[i]);
            e[s2].emplace_back(s1, 1 / rates[i]);
        }
        queue<string> q;
        map<string, double> ret;
        q.push(s0);
        ret[s0] = 1.0;
        while(!q.empty()) {
            string now = q.front();
            q.pop();
            for (auto &t : e[now]) {
                string nxt = t.first;
                double r = t.second;
                if (ret.contains(nxt)) continue;
                ret[nxt] = ret[now] * r;
                q.push(nxt);
            }
        }
        return ret;
    }
    double maxAmount(string s0, vector<vector<string>>& pairs1, vector<double>& rates1, vector<vector<string>>& pairs2, vector<double>& rates2) {
        for (auto &t : rates2) {
            t = 1 / t;
        }
        double ans = 1.0;
        auto m1 = calc(s0, pairs1, rates1);
        auto m2 = calc(s0, pairs2, rates2);
        for (auto &t : m1) {
            if (m2.contains(t.first)) {
                ans = max(ans, m1[t.first] * m2[t.first]);
            }
        }
        return ans;
    }
};