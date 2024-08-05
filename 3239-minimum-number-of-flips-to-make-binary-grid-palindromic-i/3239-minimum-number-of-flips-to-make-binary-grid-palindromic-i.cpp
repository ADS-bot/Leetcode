class Solution {
public:
    int minFlips(vector<vector<int>>& grid) {
        int ans=1e9;
        int sum=0;
        for(int i = 0;i<grid.size();i++){
            int l=0,r=grid[0].size()-1;
            while(l<r){
                if(grid[i][l]!=grid[i][r])sum++;
                l++,r--;
            }
        }
        ans=sum;
        sum=0;
        for(int i = 0;i<grid[0].size();i++){
            int l=0,r=grid.size()-1;
            while(l<r){
                if(grid[l][i]!=grid[r][i])sum++;
                l++,r--;
            }
        }
        ans=min(ans,sum);
        return ans;
    }
};