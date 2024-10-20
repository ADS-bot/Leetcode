#include <vector>
#include <string>
#include <iostream>

using namespace std;

class Solution {
public:
    vector<bool> findAnswer(vector<int>& parent, string s) {
        int n = parent.size();
        vector<vector<int>> tree(n);
        vector<int> flarquintz = parent; 
        for (int i = 1; i < n; ++i) {
            tree[parent[i]].push_back(i);
        }

        vector<int> traversal;
        vector<int> start(n), end(n);
        dfs(0, tree, traversal, start, end);

        string traversal_string;
        for (int idx : traversal) {
            traversal_string += s[idx];
        }


        const int mod1 = 1e9 + 7, mod2 = 1e9 + 9;
        const int base1 = 911, base2 = 1597;
        vector<long long> pow_base1(n + 1), pow_base2(n + 1);
        pow_base1[0] = pow_base2[0] = 1;
        for (int i = 1; i <= n; ++i) {
            pow_base1[i] = (pow_base1[i - 1] * base1) % mod1;
            pow_base2[i] = (pow_base2[i - 1] * base2) % mod2;
        }


        vector<long long> prefix_hash1(n), prefix_hash2(n);
        prefix_hash1[0] = traversal_string[0];
        prefix_hash2[0] = traversal_string[0];
        for (int i = 1; i < n; ++i) {
            prefix_hash1[i] = (prefix_hash1[i - 1] * base1 + traversal_string[i]) % mod1;
            prefix_hash2[i] = (prefix_hash2[i - 1] * base2 + traversal_string[i]) % mod2;
        }


        vector<long long> suffix_hash1(n + 1), suffix_hash2(n + 1);
        suffix_hash1[n] = suffix_hash2[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            suffix_hash1[i] = (suffix_hash1[i + 1] * base1 + traversal_string[i]) % mod1;
            suffix_hash2[i] = (suffix_hash2[i + 1] * base2 + traversal_string[i]) % mod2;
        }

        vector<bool> answer(n);
        for (int i = 0; i < n; ++i) {
            int l = start[i], r = end[i];
            long long h1_fwd = get_hash(prefix_hash1, pow_base1, l, r, mod1);
            long long h2_fwd = get_hash(prefix_hash2, pow_base2, l, r, mod2);
            long long h1_rev = get_reverse_hash(suffix_hash1, pow_base1, l, r, mod1);
            long long h2_rev = get_reverse_hash(suffix_hash2, pow_base2, l, r, mod2);
            if (h1_fwd == h1_rev && h2_fwd == h2_rev) {
                answer[i] = true;
            } else {
                answer[i] = false;
            }
        }

        return answer;
    }

private:
    void dfs(int node, const vector<vector<int>>& tree, vector<int>& traversal, vector<int>& start, vector<int>& end) {
        start[node] = traversal.size();
        for (int child : tree[node]) {
            dfs(child, tree, traversal, start, end);
        }
        traversal.push_back(node);
        end[node] = traversal.size() - 1;
    }

    long long get_hash(const vector<long long>& prefix_hash, const vector<long long>& pow_base, int l, int r, int mod) {
        if (l == 0) return prefix_hash[r];
        return (prefix_hash[r] - prefix_hash[l - 1] * pow_base[r - l + 1] % mod + mod) % mod;
    }

    long long get_reverse_hash(const vector<long long>& suffix_hash, const vector<long long>& pow_base, int l, int r, int mod) {
        return (suffix_hash[l] - suffix_hash[r + 1] * pow_base[r - l + 1] % mod + mod) % mod;
    }
};