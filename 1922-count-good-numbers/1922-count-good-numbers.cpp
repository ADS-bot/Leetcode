class Solution {
public:
    int countGoodNumbers(long long n) {
        int mod = (1e9 + 7);
        long long ocnt = (n >> 1), om = 1;
        long long ecnt = ocnt + (n & 1), em = 1;
        for (long long o = 4, e = 5; ecnt || ocnt; o *= o, e *= e) {
            o %= mod, e %= mod;
            if (ocnt & 1) om *= o, om %= mod;
            if (ecnt & 1) em *= e, em %= mod;
            ocnt >>= 1, ecnt >>= 1;
        }
        return om * em % mod;
    }
};