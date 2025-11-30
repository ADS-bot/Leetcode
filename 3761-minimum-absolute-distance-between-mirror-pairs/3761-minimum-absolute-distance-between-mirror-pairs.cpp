class Solution {
public:
    int minMirrorPairDistance(vector<int>& nums) {
        int n = nums.size();
        int minDist = INT_MAX;
        unordered_map<int, int> mp;
        
        for (int j = 0; j < n; ++j) {
            if (mp.find(nums[j]) != mp.end()) {
                minDist = min(minDist, j - mp[nums[j]]);
            }
            
            long long rev = 0;
            int temp = nums[j];
            while (temp > 0) {
                rev = rev * 10 + temp % 10;
                temp /= 10;
            }
            
            mp[(int)rev] = j;
        }
        
        return minDist == INT_MAX ? -1 : minDist;
    }
};