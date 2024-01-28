class Solution {
 public:
  int maximumGroups(vector<int>& grades) {
    return sqrt(grades.size() * 2 + 0.25) - 0.5;
  }
};