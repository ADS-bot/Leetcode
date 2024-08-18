class Solution {
public:
    vector<int> resultsArray(vector<int>& nums, int k){
        int n=nums.size();
        vector<int> bk(n,0);
        for(int i=1;i<n;i++){
            if(nums[i-1]+1==nums[i]){bk[i]=1;}
            bk[i]+=bk[i-1];
        }
        vector<int> res;
        for(int i=k-1;i<n;i++){
            int jud=bk[i]-bk[i-k+1];
            if(jud==(k-1)){res.push_back(nums[i]);}
            else{res.push_back(-1);}
        }
        return res;
    }
};