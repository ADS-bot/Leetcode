class Solution {
public:
    bool canAliceWin(int n) {
        int who = 0, t = 10;
        while (t > 0) {
            if (n < t) break;
            n -= t;
            t--;
            who ^= 1;
        }
        return who == 1;
    }
};