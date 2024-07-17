class Solution {
    const int M = 1e9+7;
public:
    int numberOfPermutations(int n, vector<vector<int>>& requirements) {
        vector<int> cnt(n, -1);
        sort(requirements.begin(), requirements.end());
        for(auto it: requirements){
            cnt[it[0]] = it[1];
        }

        vector<vector<int> > dp(n+1, vector<int>(405, 0));
        dp[0][0] = 1;
        if(cnt[0] != -1 && cnt[0] != 0) dp[0][0] = 0;

        for(int i = 1; i < n; i++){
            for(int j = 0; j <= 400; j++){
                for(int k = 0; k <= i; k++){
                    if(j+k <= 400) dp[i][j+k] = (dp[i][j+k]%M + dp[i-1][j]%M)%M;   
                }
                if(cnt[i] != -1 && cnt[i] != j) dp[i][j] = 0;
                dp[i][j] %= M;
            }
        }

        return dp[n-1][cnt[n-1]];
    }
};