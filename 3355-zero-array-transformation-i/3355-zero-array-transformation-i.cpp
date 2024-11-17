class Solution {
public:
    bool isZeroArray(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        vector<int> pref(n + 1);
        
        for (const auto& vec : queries) {
            int l = vec[0];
            int r = vec[1];
            
            pref[l]++;
            pref[r + 1]--;
        }
        
        for (int i = 1; i < n; i++) pref[i] += pref[i - 1];
        for (int i = 0; i < n; i++) if (pref[i] < nums[i]) return false;
        return true;
    }
};