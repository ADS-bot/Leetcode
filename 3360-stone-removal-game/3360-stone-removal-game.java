class Solution {
    public boolean canAliceWin(int n) {
                int count=0;
        int amount=10;
        while (n>=amount){
            n-=amount;
            amount--;
            count++;
        }
        return count%2==1;
    }
}