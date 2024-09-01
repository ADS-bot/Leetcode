class Solution {
public:
    vector<int> maximumSubarrayXor(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size(), q = queries.size();

        vector<vector<int>> dp(n, vector<int>(n));
        for(int i = n - 1; ~i; i--)
        for(int j = i; j < n; j++){
            int x = nums[i], z = j - i;
            for(int l = z; l; l = (l - 1) & z){
                x ^= nums[i + l];
            }
            dp[i][j] = x;
            if(i < j){
                dp[i][j] = max({dp[i][j], dp[i + 1][j], dp[i][j - 1]});
            }
        }

        vector<int> ret;
        for(auto &qry : queries){
            int x = qry[0], y = qry[1];
            ret.push_back(dp[x][y]);
        }
        return ret;
    }
};
