class Solution {
    public long maximumValueSum(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        long[] bs = new long[n*m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                long ta = board[i][j];
                ta=2000000000-ta;
                ta*=(n*m);
                ta+=i*m+j;
                bs[i*m+j]=ta;
            }
        }
        Arrays.sort(bs);
        long ans = -3000000000L;
        for (int i = 0; i < n*m; ++i) {
            int y1 = (int)(bs[i]%(n*m)/m);
            int x1 = (int)(bs[i]%(n*m)%m);
            long s1 = board[y1][x1];
            if (s1*3<=ans) {
                break;
            }
            for (int j = i+1; j < n*m; ++j) {
                int y2 = (int)(bs[j]%(n*m)/m);
                int x2 = (int)(bs[j]%(n*m)%m);
                long s2 = board[y2][x2];
                if (s1+s2*2<=ans) {
                    break;
                }
                if (y1==y2||x1==x2) {
                    continue;
                }
                for (int k = j+1; k < n*m; ++k) {
                    int y3 = (int)(bs[k]%(n*m)/m);
                    int x3 = (int)(bs[k]%(n*m)%m);
                    long s3 = board[y3][x3];
                    if (s1+s2+s3<=ans) {
                        break;
                    }
                    if (y1==y3||y2==y3||x1==x3||x2==x3) {
                        continue;
                    }
                    ans = s1+s2+s3;
                }
            }
        }
        return ans;
    }
}