class Solution {
 public:
  // Similar to 3. Longest Substring Without Repeating Characters
  int numberOfSubstrings(string s) {
    int ans = 0;
    vector<int> lastSeen(3, -1);  // lastSeen[c] := index of last c appeared.

    for (int i = 0; i < s.length(); ++i) {
      lastSeen[s[i] - 'a'] = i;
      // s[0..i], s[1..i], s[min(lastSeen)..i] are satisfied strings.
      ans += 1 + *min_element(lastSeen.begin(), lastSeen.end());
    }

    return ans;
  }
};
