class Solution {
public:
    char kthCharacter(long long K, vector<int>& operations) {
        if (K == 1) return 'a';

        int lim;
        long long len = 1;
        for (lim = 0; lim < operations.size(); lim++) {
            len *= 2;
            if (len >= K) break;
        }

        int bias = 0;
        for (int i = lim; i >= 0; i--) {
            if (K > len / 2) {
                bias += operations[i];
                K -= len / 2;
            }
            len /= 2;
        }
        return bias % 26 + 'a';
    }
};