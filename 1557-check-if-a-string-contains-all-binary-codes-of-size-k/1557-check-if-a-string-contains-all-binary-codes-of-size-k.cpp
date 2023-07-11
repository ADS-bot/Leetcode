class Solution {
 public:
  bool hasAllCodes(string s, int k) {
    const int n = 1 << k;
    if (s.length() < n)
      return false;

    // used[i] := true if i is a substring of s.
    vector<bool> used(n);

    int window = k == 1 ? 0 : stoi(s.substr(0, k - 1), nullptr, 2);
    for (int i = k - 1; i < s.length(); ++i) {
      // Include s[i].
      window = (window << 1) + (s[i] - '0');
      // Discard s[i - k].
      window &= n - 1;
      used[window] = true;
    }

    return all_of(used.begin(), used.end(), [](bool u) { return u; });
  }
};
