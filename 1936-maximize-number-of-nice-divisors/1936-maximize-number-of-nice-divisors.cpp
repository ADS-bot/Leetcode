class Solution {
 public:
  int maxNiceDivisors(int primeFactors) {
    if (primeFactors <= 3)
      return primeFactors;
    if (primeFactors % 3 == 0)
      return myPow(3, primeFactors / 3) % kMod;
    if (primeFactors % 3 == 1)
      return 4L * myPow(3, (primeFactors - 4) / 3) % kMod;
    return 2L * myPow(3, (primeFactors - 2) / 3) % kMod;
  }

 private:
  static constexpr int kMod = 1'000'000'007;

  int myPow(long x, int n) {
    if (n == 0)
      return 1;
    if (n & 1)
      return x * myPow(x, n - 1) % kMod;
    return myPow(x * x % kMod, n / 2);
  }
};
