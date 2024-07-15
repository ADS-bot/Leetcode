class Solution {
 public:
  int minimumSubstringsInPartition(string s) {
    const int n = s.length();
    vector<int> dp(n, n);

    for (int i = 0; i < n; ++i) {
      vector<int> count(26);
      for (int j = i; j >= 0; --j) {
        ++count[s[j] - 'a'];
        if (isBalanced(count))
          dp[i] = j > 0 ? min(dp[i], 1 + dp[j - 1]) : 1;
      }
    }

    return dp.back();
  }

 private:
  static constexpr int kMax = 1001;
  bool isBalanced(const vector<int>& count) {
    int minfreq = kMax;
    int maxfreq = 0;
    for (const int freq : count)
      if (freq > 0) {
        minfreq = min(minfreq, freq);
        maxfreq = max(maxfreq, freq);
      }
    return minfreq == maxfreq;
  }
};