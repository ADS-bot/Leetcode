class Solution {
public:
    bool checkTwoChessboards(string a, string b) {
        int v = a[0] - 'a' + a[1] - '1';
        int u = b[0] - 'a' + b[1] - '1';
        return ((u&1) == (v&1));
    }
};