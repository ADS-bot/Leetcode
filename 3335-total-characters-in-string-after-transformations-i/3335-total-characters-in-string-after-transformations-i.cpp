typedef long long ll;
const int MOD = 1e9+7;

typedef vector<vector<ll>> matrix;

matrix multiply(const matrix &A, const matrix &B) {
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

matrix power_matrix(matrix A, int power){
    matrix result(26, vector<ll>(26, 0));
    for(int i=0;i<26;i++) result[i][i] = 1;
    while(power > 0){
        if(power & 1){
            result = multiply(result, A);
        }
        A = multiply(A, A);
        power >>=1;
    }
    return result;
}

class Solution {
public:
    int lengthAfterTransformations(string s, int t) {
        matrix M(26, vector<ll>(26, 0));
        for(int c=0;c<26;c++){
            if(c != 25){
                M[c][c+1] = 1;
            }
            else{
                M[c][0] = 1;
                M[c][1] = 1;
            }
        }
        matrix Mt = power_matrix(M, t);
        vector<ll> counts(26, 0);
        for(char ch:s) counts[ch - 'a']++;
        vector<ll> final_counts(26, 0);
        for(int i=0;i<26;i++) {
            if(counts[i]==0) continue;
            for(int j=0;j<26;j++) {
                final_counts[j] = (final_counts[j] + counts[i] * Mt[i][j]) % MOD;
            }
        }
        ll total = 0;
        for(auto cnt: final_counts) total = (total + cnt) % MOD;
        return total;
    }
};