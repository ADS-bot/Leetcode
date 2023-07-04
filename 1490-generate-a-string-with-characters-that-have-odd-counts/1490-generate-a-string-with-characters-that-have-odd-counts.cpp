class Solution {
public:
    string generateTheString(int n) {
        if(n % 2 == 1) return string(n, 'a');
        string ans = string(n-1, 'a');
        ans += 'b';
        return ans;
    }
};