class Solution {
public:
  long long maxScore(vector<int>& a, vector<int>& b) {
    vector<long long> f(5, -1e18);
    f[0] = 0;
    for (auto e: b) {
      for (int i = 4; i >= 1; i --)
        f[i] = max(f[i], f[i - 1] + 1ll * a[i - 1] * e);
    }
    return f.back();
  }
};