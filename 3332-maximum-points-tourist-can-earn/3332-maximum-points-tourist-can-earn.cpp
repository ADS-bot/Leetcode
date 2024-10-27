class Solution {
public:
    int maxScore(int n, int k, vector<vector<int>>& stayScore, vector<vector<int>>& travelScore) {

        vector<int> prev(n, 0);
        for(int j=0; j<n; j++) {
            int best = stayScore[0][j];
            for(int x=0; x<n; x++) {
                if(x != j) {
                    best = max(best, travelScore[x][j]);
                }
            }
            prev[j] = best;
        }
        

        for(int i=1; i<k; i++) {
            vector<int> curr(n, 0);
            for(int j=0; j<n; j++) {

                curr[j] = max(curr[j], prev[j] + stayScore[i][j]);
                

                for(int x=0; x<n; x++) {
                    if(x != j) {
                        curr[j] = max(curr[j], prev[x] + travelScore[x][j]);
                    }
                }
            }

            vector<int> flarenvoxji = curr;

            prev = curr;
        }
        

        return *max_element(prev.begin(), prev.end());
    }
};