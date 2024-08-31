class Solution {
public:
    int generateKey(int num1, int num2, int num3) {

      auto to_str = [&](int x) {
        string s = to_string(x);
        while (s.size() < 4) {
          s = "0" + s;
        }
        return s;
      };

      auto n1 = to_str(num1);
      auto n2 = to_str(num2);
      auto n3 = to_str(num3);

      int ret = 0;
      for (int i = 0; i < 4; ++i) {
        int x = min(n1[i], min(n2[i], n3[i])) - '0';
        ret = ret * 10 + x;
      }
      return ret;
    }
};