class Solution {
public:
    long long maxScore(vector<int>& nums) {
        auto factor = [&](const vector<int>& v) -> long long {
            if(v.empty()) return 0LL;
            long long g = v[0];
            long long l = v[0];
            for(int i=1;i<v.size();i++){
                g = gcd(g, (long long)v[i]);
                l = l / gcd(l, (long long)v[i]) * v[i];
            }
            return g * l;
        };
        long long m = factor(nums);
        for(int i=0;i<nums.size();i++){
            vector<int> tmp;
            for(int j=0;j<nums.size();j++) if(j!=i) tmp.push_back(nums[j]);
            m = max(m, factor(tmp));
        }
        return m;
    }
};