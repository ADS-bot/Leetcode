	class Solution {
		public boolean isPossibleToRearrange(String s, String t, int k) {
			int n = s.length();
			String[] a = new String[k];
			String[] b = new String[k];
			for(int i = 0;i < k;i++){
				a[i] = s.substring(i*(n/k), (i+1)*(n/k));
				b[i] = t.substring(i*(n/k), (i+1)*(n/k));
			}
			Arrays.sort(a);
			Arrays.sort(b);
			return Arrays.equals(a, b);
		}
	}