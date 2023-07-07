class Solution {
public:
    int minStartValue(vector<int>& A) {
        int sum = 0, mn = INT_MAX;
        for (int n : A) mn = min(mn, sum += n);
        return 1 - min(0, mn);
    }
};