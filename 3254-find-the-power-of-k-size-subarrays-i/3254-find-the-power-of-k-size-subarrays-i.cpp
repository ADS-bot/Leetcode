class Solution {
public:
    vector<int> resultsArray(vector<int>& nums, int k){
        int n=nums.size();
        vector<int> res;
        for(int i=k-1;i<n;i++){
            vector<int> ca;
            for(int j=0;j<k;j++){
                ca.push_back(nums[i-(k-1)+j]);
            }
            int w=ca.back();
            for(int i=1;i<ca.size();i++){
                if(ca[i-1]+1!=ca[i]){w=-1; break;}
            }
            res.push_back(w);
        }
        return res;
    }
};