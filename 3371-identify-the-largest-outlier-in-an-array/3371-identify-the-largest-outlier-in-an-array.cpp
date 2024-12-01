class Solution {
public:
    int getLargestOutlier(vector<int>& nums) {
        multiset<int> ms;
        long long sm = 0;
        for (int x : nums) ms.insert(x), sm += x;

        long long ans = -1e9;
        for (int x : nums) {
            ms.erase(ms.find(x));
            long long t = sm - x * 2;
            if (ms.find(t) != ms.end()) ans = max(ans, t);
            ms.insert(x);
        }
        return ans;
    }
};