	class Solution {
		int[] ds(int s, int[][] g) {
			int[][] ps = parents(g, s);
			return ps[2];
		}


		public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {

			int n = edges1.length + 1;
			int[][] g1 = packU(n, edges1);
			int m = edges2.length + 1;
			int[][] g2 = packU(m, edges2);

			int[] ans = new int[n];
			for(int i = 0;i < n;i++){
				int[] ds = ds(i, g1);
				for(int j = 0;j < n;j++){
					if(ds[j] <= k){
						ans[i]++;
					}
				}
			}

			int max = 0;
			for(int i = 0;i < m;i++){
				int[] ds = ds(i, g2);
				int num = 0;
				for(int j = 0;j < m;j++){
					if(ds[j] <= k-1){
						num++;
					}
				}
				max = Math.max(max, num);
			}
			for(int i = 0;i < n;i++){
				ans[i] += max;
			}
			return ans;
		}

		public static int[][] parents(int[][] g, int root) {
			int n = g.length;
			int[] par = new int[n];
			Arrays.fill(par, -1);

			int[] depth = new int[n];
			depth[0] = 0;

			int[] q = new int[n];
			q[0] = root;
			for (int p = 0, r = 1; p < r; p++) {
				int cur = q[p];
				for (int nex : g[cur]) {
					if (par[cur] != nex) {
						q[r++] = nex;
						par[nex] = cur;
						depth[nex] = depth[cur] + 1;
					}
				}
			}
			return new int[][]{par, q, depth};
		}

		public static int[][] packU(int n, int[][] ft)
		{
			int[][] g = new int[n][];
			int[] p = new int[n];
			for(int[] u : ft){
				p[u[0]]++; p[u[1]]++;
			}
			for(int i = 0;i < n;i++)g[i] = new int[p[i]];
			for(int[] u : ft){
				g[u[0]][--p[u[0]]] = u[1];
				g[u[1]][--p[u[1]]] = u[0];
			}
			return g;
		}


	}