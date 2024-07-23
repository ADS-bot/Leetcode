class Solution {
 public:
  bool doesAliceWin(string s) {
    return ranges::any_of(s, [=](char c) { return isVowel(c); });
  }
 private:
  bool isVowel(char c) {
    static constexpr string_view kVowels = "aeiou";
    return kVowels.find(c) != string_view::npos;
  }
};