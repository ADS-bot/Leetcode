class Solution {
public:
    const int L=7;
    int countPairs(vector<int>& nums) {
        int n=nums.size();
        unordered_map<string,int> mp,id;
        int ans=0;
        for(int i=1;i<=n;i++){
            int x=nums[i-1];
            string s;
            for(int j=0;j<L;j++){
                s+=(x%10)+'0';
                x/=10;
            }
            ans+=mp[s];
            mp[s]++;
            id[s]=i;
            for(int p=0;p<L;p++){
                for(int q=p+1;q<L;q++){
                    if(s[p]==s[q]) continue;
                    swap(s[p],s[q]);
                    if(id[s]<i){
                        id[s]=i;
                        mp[s]++;
                    }
                    for(int r=p;r<L;r++){
                        for(int t=r+1;t<L;t++){
                            if(s[r]==s[t]) continue;
                            swap(s[r],s[t]);
                            if(id[s]<i){
                                id[s]=i;
                                mp[s]++;
                            }
                            swap(s[r],s[t]);
                        }
                    }
                    swap(s[p],s[q]);
                }
            }
        }
        return ans;
    }
};
