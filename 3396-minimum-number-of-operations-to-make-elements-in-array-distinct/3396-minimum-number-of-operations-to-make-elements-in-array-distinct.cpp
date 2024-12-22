class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        reverse(nums.begin(),nums.end());
        int cnt[105];
        memset(cnt,0,sizeof(cnt));
        for(auto it:nums)cnt[it]++;
        for(int i = 0;;i++){
            int ok=1;
            for(int j = 1;j<=100;j++){
                if(cnt[j]>1)ok=0;
            }
            if(ok)return i;
            for(int j =0;j<3;j++){
                cnt[nums.back()]--;
                nums.pop_back();
                if(nums.empty())break;
            }
        }
        return 0;
    }
};