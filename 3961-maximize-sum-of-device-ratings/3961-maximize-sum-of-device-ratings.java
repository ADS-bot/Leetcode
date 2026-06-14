class Solution {
    public long maxRatings(int[][] units) {
        int m = units.length;
        int n = units[0].length;
        
        if (n == 1) {
            long sum = 0;
            for (int i = 0; i < m; i++) {
                sum += units[i][0];
            }
            return sum;
        }
        
        long sumA2 = 0;
        int M = Integer.MAX_VALUE;
        int minA2 = Integer.MAX_VALUE;
        
        for (int i = 0; i < m; i++) {
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            
            for (int j = 0; j < n; j++) {
                int val = units[i][j];
                if (val < min1) {
                    min2 = min1;
                    min1 = val;
                } else if (val < min2) {
                    min2 = val;
                }
            }
            
            if (min1 < M) {
                M = min1;
            }
            
            sumA2 += min2;
            
            if (min2 < minA2) {
                minA2 = min2;
            }
        }
        
        return (long) M + sumA2 - minA2;
    }
}