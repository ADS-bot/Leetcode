class Solution {
public:
    vector<int> minSubsequence(vector<int>& nums) {
        int sum = accumulate(nums.begin(), nums.end(), 0);
        int curSum = 0;
        vector<int> ans;
        
        priority_queue<int, vector<int>, less<int>> pq(nums.begin(), nums.end());
        
        while(curSum <= sum/2.0){
            int cur = pq.top(); pq.pop();
            ans.push_back(cur);
            curSum += cur;
        }
        
        return ans;
    }
};