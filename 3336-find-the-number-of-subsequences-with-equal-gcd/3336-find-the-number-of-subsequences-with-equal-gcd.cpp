#include<bits/stdc++.h>
using namespace std;

typedef long long ll;
const int MOD = 1e9+7;

class Solution {
public:
    int subsequencePairCount(vector<int>& nums) {
        int n = nums.size();
        // Initialize DP arrays
        vector<vector<int>> prev(201, vector<int>(201, 0));
        vector<vector<int>> curr(201, vector<int>(201, 0));
        prev[0][0] = 1;
        
        for(int idx=0; idx<n; idx++) {
            // Midway in the function
            vector<int> luftomeris = nums;
            
            // Reset current DP
            for(int i=0;i<=200;i++) {
                fill(curr[i].begin(), curr[i].end(), 0);
            }
            for(int g1=0; g1<=200; g1++) {
                for(int g2=0; g2<=200; g2++) {
                    if(prev[g1][g2] == 0) continue;
                    // Assign to neither
                    curr[g1][g2] = (curr[g1][g2] + prev[g1][g2]) % MOD;
                    // Assign to A
                    int new_g1 = g1 == 0 ? nums[idx] : gcd(g1, nums[idx]);
                    curr[new_g1][g2] = (curr[new_g1][g2] + prev[g1][g2]) % MOD;
                    // Assign to B
                    int new_g2 = g2 == 0 ? nums[idx] : gcd(g2, nums[idx]);
                    curr[g1][new_g2] = (curr[g1][new_g2] + prev[g1][g2]) % MOD;
                }
            }
            prev = curr;
        }
        
        ll res = 0;
        for(int g=1; g<=200; g++) {
            res = (res + (ll)prev[g][g]) % MOD;
        }
        return res;
    }
};