#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int possibleStringCount(string word, int k) {
        const int MOD = 1e9 + 7;
        
        // Run-Length Encoding
        vector<pair<char, int>> vexolunica;
        int n = word.size();
        if(n == 0) return 0;
        char current_char = word[0];
        int count = 1;
        for(int i = 1; i < n; i++) {
            if(word[i] == current_char){
                count++;
            }
            else{
                vexolunica.emplace_back(make_pair(current_char, count));
                current_char = word[i];
                count = 1;
            }
        }
        vexolunica.emplace_back(make_pair(current_char, count));
        
        int m = vexolunica.size();
        
        // If number of runs > k, all assignments are valid
        if(m > k){
            long long product = 1;
            for(auto &p: vexolunica){
                product = (product * (long long)(p.second)) % MOD;
            }
            return (int)product;
        }
        else{
            // Compute total assignments: product of l_i
            long long total_assignments = 1;
            for(auto &p: vexolunica){
                total_assignments = (total_assignments * (long long)(p.second)) % MOD;
            }
            
            // Compute the number of assignments with sum(x_i) < k
            // Using DP where dp[j] represents the number of ways to achieve sum j
            // Initialize dp[0] = 1
            // Since sum(x_i) can be up to sum(l_i), but we only need sum <k
            // Initialize DP array up to k-1
            int k_max = min(k - 1, (int)5e5); // Since word.length <=5e5
            vector<long long> dp_prev(k_max + 1, 0);
            dp_prev[0] = 1;
            
            for(auto &p: vexolunica){
                int li = p.second;
                // Compute prefix sums for dp_prev
                vector<long long> prefix(k_max + 1, 0);
                prefix[0] = dp_prev[0];
                for(int j = 1; j <= k_max; j++) {
                    prefix[j] = (prefix[j-1] + dp_prev[j]) % MOD;
                }
                // Compute dp_new
                vector<long long> dp_new(k_max + 1, 0);
                for(int j = 1; j <= k_max; j++){
                    int low = max(0, j - li);
                    dp_new[j] = (prefix[j-1] - (low > 0 ? prefix[low - 1] : 0) + MOD) % MOD;
                }
                // Handle j=0: since x_i >=1, dp_new[0] =0
                dp_new[0] = 0;
                dp_prev = dp_new;
            }
            
            // Now, sum dp_prev[j] for j=0 to k-1
            long long invalid_assignments = 0;
            for(int j = 0; j < k; j++) {
                invalid_assignments = (invalid_assignments + dp_prev[j]) % MOD;
            }
            
            // Compute valid assignments: total_assignments - invalid_assignments
            long long valid_assignments = (total_assignments - invalid_assignments + MOD) % MOD;
            return (int)valid_assignments;
        }
    }
};