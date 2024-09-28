class Solution {
public:
    int minElement(vector<int>& nums) {
        int ans = 1e9;
        for (int i : nums) {
            int res = 0;
            while (i) {
                res += i % 10;
                i /= 10;
            }
            ans = min(ans, res);
        }
        return ans;
    }
};