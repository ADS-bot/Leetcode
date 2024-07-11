class Solution {
 public:
  string getSmallestString(string s, int k) {
    string ans = s;

    for (char& c : ans) {
      if (k == 0)
        break;
      const int distToA = min(c - 'a', 'z' - c + 1);
      if (k >= distToA) {
        k -= distToA;
        c = 'a';
      } else {
        c -= k;
        k = 0;
      }
    }

    return ans;
  }
};