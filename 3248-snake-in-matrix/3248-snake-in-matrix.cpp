class Solution {
public:
    int finalPositionOfSnake(int n, vector<string>& commands) {
        int i=0, j=0;
        for (string s : commands) {
            if (s == "LEFT") {
                j--;
            } else if (s == "RIGHT") {
                j++;
            } else if (s == "DOWN") {
                i++;
            } else if (s == "UP") {
                i--;
            }
        }
        return i*n+j;
    }
};