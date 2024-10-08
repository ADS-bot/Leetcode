class Solution {
		public String largestPalindrome(int n, int k) {
			long[] a = new long[100005];
			a[0] = 1;
			for(int i = 1;i < 100005;i++){
				a[i] = a[i-1] * 10 % k;
			}
			boolean[][] dp = new boolean[k][(n-1)/2+1];
			dp[0][0] = true;
			for(int i = 0;i < (n-1)/2;i++){
				int t = (int)(i == 0 && n/2*2 != n ? a[n/2] : ((a[(n-1)/2-i] + a[n/2+i]) % k));
				for(int u = 0;u < k;u++) {
					if(dp[u][i]){
						for(int j = 0;j <= 9;j++) {
							dp[(u + t * j) % k][i + 1] = true;
						}
					}
				}
			}
			int kk = 0;
			char[] ret = new char[n];
			for(int i = (n-1)/2;i >= 0;i--){
				int t = (int)(i == 0 && n/2*2 != n ? a[n/2] : ((a[(n-1)/2-i] + a[n/2+i]) % k));
				for(int j = 9;j >= 0;j--) {
					int u = (kk-t*j) % k;
					if(u < 0)u += k;
					if (dp[u][i]){
						ret[n/2+i] = ret[(n-1)/2-i] = (char)('0'+j);
						kk = u;
						break;
					}
				}
			}
			return new String(ret);
		}
	}