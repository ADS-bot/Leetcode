class Solution {
    public long maxEnergyBoost(int[] aa, int[] bb) {
        long a = 0, b = 0;
        int n = aa.length;
        for(int i = 0;i < n;i++){
            long na = Math.max(a+aa[i], b);
            long nb = Math.max(b+bb[i], a);
            a = na;
            b = nb;
        }
        return Math.max(a, b);
    }
}