class Fancy {
    typedef unsigned long UL;
    UL mod = 1e9 + 7, len = 0, increment = 0, mul = 1, A[100001];
    UL modPow(UL base, UL exp) {
        UL ans = 1, p = base;
        for (; exp; exp >>= 1) {
            if (exp & 1) ans = (ans * p) % mod;
            p = (p * p) % mod;
        }
        return ans;
    }
public:
    Fancy() {}
    void append(int val) {
        A[len++] = (((val - increment + mod) % mod) * modPow(mul, mod - 2)) % mod;
    }
    void addAll(int inc) {
        increment = (increment + inc) % mod;
    }
    void multAll(int m) {
        mul = (mul * m) % mod;
        increment = (increment * m) % mod;
    }
    int getIndex(int i) {
        return i >= len ? -1 : ((A[i] * mul) % mod + increment) % mod;
    }
};