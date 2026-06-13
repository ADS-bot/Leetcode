class Solution {
    public long minEnergy(int n, int brightness, int[][] iv) {
        Arrays.sort(iv, (a, b) -> Integer.compare(a[0], b[0]));

        long cot = 0;

        int cs = iv[0][0];
        int ce = iv[0][1];

        for (int i = 1; i < iv.length; i++) {
            int start = iv[i][0];
            int end = iv[i][1];

            if (start <= ce + 1L) {
                ce = Math.max(ce, end);
            } else {
                cot += (long) ce - cs + 1;
                cs = start;
                ce = end;
            }
        }

        cot += (long) ce - cs + 1;

        long bulbsNeededPerTime = (brightness + 2L) / 3L;

        return cot * bulbsNeededPerTime;
    }
}