class Solution {
public:
    int maxGoodNumber(vector<int>& a) {
        sort(begin(a), end(a));
        int ans = 0;
        do {
            int cur = 0;
            for (int x : a) {
                bool flag = false;
                for (int i = 10; i >= 0; --i) {
                    if (x & (1 << i)) {
                        flag = true;
                    }
                    if (flag) cur = cur*2 + ((x >> i) & 1);
                }
            }
            ans = max(ans, cur);
        } while (next_permutation(begin(a), end(a)));
        return ans;
    }
};