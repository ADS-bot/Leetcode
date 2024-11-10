class Solution {
    int getmult(int x){
        int t=1;
        while (x>0){
            t=t*(x%10);x/=10;
        }
        return t;
    }
    public int smallestNumber(int n, int t) {
       int x=n;
       while (getmult(x)%t!=0){
           x++;
       }
        return x;
    }
}