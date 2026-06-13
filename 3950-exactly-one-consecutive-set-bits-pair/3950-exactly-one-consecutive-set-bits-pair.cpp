class Solution {
public:
    bool consecutiveSetBits(int n) {
        int x=n&(n>>1);
        int c=0;
        while(x)
        {
            c=c+1;
            x=x&(x-1);
        }
        return c==1;
    }
};