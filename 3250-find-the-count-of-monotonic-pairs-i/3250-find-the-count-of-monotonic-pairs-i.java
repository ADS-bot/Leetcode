class Solution {
		public int countOfPairs(int[] a) {
			final int mod = 1000000007;
			int n = a.length;
			long[] dp = new long[1001];
			for(int i = 0;i <= a[0];i++){
				dp[i]++;
			}
			for(int i = 1;i < n;i++){
				int p = Math.max(0, a[i] - a[i-1]);
				for(int j = 1;j <= 1000;j++){
					dp[j] += dp[j-1];
					dp[j] %= mod;
				}
				for(int j = 1000;j >= p;j--){
					dp[j] = dp[j-p];
				}
				for(int j = p-1;j >= 0;j--){
					dp[j] = 0;
				}
				for(int j = a[i]+1;j <= 1000;j++){
					dp[j] = 0;
				}
			}
			long ans = 0;
			for(long v :  dp)ans += v;
			return (int)(ans % mod);
		}
	}