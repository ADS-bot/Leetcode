class Solution {
public:
    int smallestNumber(int n) {
        return (1 << (__lg(n) + 1)) - 1;
    }
};