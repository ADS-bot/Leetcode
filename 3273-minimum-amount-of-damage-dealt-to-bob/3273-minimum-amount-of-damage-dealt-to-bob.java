class Solution {
    public long minDamage(int power, int[] damage, int[] health) {
        int [][] mArr = new int [damage.length][];
        int val;
        for (int i = 0; i < health.length; ++i) {
            val = health[i] + power - 1;
            val /= power;   
            mArr[i] = new int [] {damage[i], val};
        }
        Arrays.sort(mArr, (a, b) -> a[1]*b[0] - a[0]*b[1]);
        long sum = 0;
        for (int num : damage) 
            sum += num;
        long result = 0;
        for (int [] cArr : mArr) {
            result += sum * cArr[1];
            sum -= cArr[0];
        }
        return result;
    }
}
