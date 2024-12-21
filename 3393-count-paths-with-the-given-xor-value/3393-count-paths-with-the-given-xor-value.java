class Solution {
    long mod=1000000007;
    public int countPathsWithXorValue(int[][] grid, int k) {
        int n=grid.length;
        int m=grid[0].length;
        long[][][] ways=new long[n][m][16];
        ways[0][0][grid[0][0]]=1;
        int t=grid[0][0];
        for (int i=1;i<n;i++){
            t=t^grid[i][0];
            ways[i][0][t]=1;
        }
        t=grid[0][0];
        for (int i=1;i<m;i++){
            t=t^grid[0][i];ways[0][i][t]=1;
        }
        for (int i=1;i<n;i++){
            for (int j=1;j<m;j++){
                for ( t=0;t<16;t++){
                    int t1=t^grid[i][j];
                    ways[i][j][t]=(ways[i-1][j][t1]+ways[i][j-1][t1])%mod;
                }
            }
        }
        return (int)ways[n-1][m-1][k];
    }
}