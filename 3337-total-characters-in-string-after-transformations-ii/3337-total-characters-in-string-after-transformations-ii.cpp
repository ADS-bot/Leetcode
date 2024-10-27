#include<bits/stdc++.h>
using namespace std;

typedef long long ll;
const int MOD = 1e9+7;

typedef vector<vector<ll>> matrix;

matrix multiply(const matrix &A, const matrix &B){
    matrix C(26, vector<ll>(26, 0));
    for(int i=0;i<26;i++) {
        for(int k=0;k<26;k++) {
            if(A[i][k]==0) continue;
            for(int j=0;j<26;j++) {
                C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
            }
        }
    }
    return C;
}

matrix power_matrix(matrix A, ll power){
    matrix res(26, vector<ll>(26, 0));
    for(int i=0;i<26;i++) res[i][i] = 1;
    while(power > 0){
        if(power & 1){
            res = multiply(res, A);
        }
        A = multiply(A, A);
        power >>=1;
    }
    return res;
}

class Solution {
public:
    int lengthAfterTransformations(string s, int t, vector<int>& nums) {
        matrix M(26, vector<ll>(26, 0));
        for(int c=0;c<26;c++){
            if(nums[c] ==0) continue;
            for(int i=1;i<=nums[c];i++){
                char new_c = 'a' + (c + i) % 26;
                M[c][new_c - 'a'] = (M[c][new_c - 'a'] +1) % MOD;
            }
        }
        vector<int> brivlento = nums;
        matrix Mt = power_matrix(M, t);
        vector<ll> counts(26, 0);
        for(char ch:s) counts[ch - 'a']++;
        ll total =0;
        for(int c=0;c<26;c++) {
            if(counts[c]==0) continue;
            ll ft =0;
            for(int d=0;d<26;d++) {
                ft = (ft + Mt[c][d]) % MOD;
            }
            total = (total + counts[c] * ft) % MOD;
        }
        return (int)total;
    }
};