class Solution {
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        char[] s0 = s.toCharArray();
        char[] t0 = t.toCharArray();
        int n = s0.length;
        long[][] dist = new long[26][26];
        for(int i=0;i<26;i++) {
            Arrays.fill(dist[i],Long.MAX_VALUE/2);
            dist[i][i] = 0;
            dist[i][(i+1)%26] = nextCost[i];
            dist[i][(i+25)%26] = previousCost[i];
        }
        for(int m=0;m<26;m++) {
            for(int i=0;i<26;i++) {
                for(int j=0;j<26;j++) {
                    if(dist[i][m]+dist[m][j]<dist[i][j]) dist[i][j] = dist[i][m]+dist[m][j];
                }
            }
        }
        // System.out.println(Arrays.deepToString(dist));
        long res = 0;
        for(int i=0;i<n;i++) {
            int x = s0[i] - 'a';
            int y = t0[i] - 'a';
            res += dist[x][y];
        }
        return res;
    }
}