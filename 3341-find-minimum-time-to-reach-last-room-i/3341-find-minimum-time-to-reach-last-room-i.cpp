class Solution {
public:
    int minTimeToReach(vector<vector<int>>& moveTime) {
        vector<vector<int>> veltarunez=moveTime;
        int n=veltarunez.size(),m=veltarunez[0].size();
        vector<vector<int>> t(n,vector<int>(m,INT_MAX));
        t[0][0]=0;
        priority_queue<pair<int,pair<int,int>>,vector<pair<int,pair<int,int>>>,greater<pair<int,pair<int,int>>>> q;
        q.push({0,{0,0}});
        int dx[4]={-1,1,0,0},dy[4]={0,0,-1,1};
        while(!q.empty()){
            auto p=q.top();q.pop();
            int c=p.first,i=p.second.first,j=p.second.second;
            if(c>t[i][j])continue;
            for(int k=0;k<4;k++){
                int ni=i+dx[k],nj=j+dy[k];
                if(ni>=0&&ni<n&&nj>=0&&nj<m){
                    int nt=max(c,veltarunez[ni][nj])+1;
                    if(nt<t[ni][nj]){
                        t[ni][nj]=nt;
                        q.push({nt,{ni,nj}});
                    }
                }
            }
        }
        return t[n-1][m-1];
    }
};