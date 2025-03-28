	class Solution {
		public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
			int n = edges1.length + 1;
			int[][] g1 = packU(n, edges1);
			int m = edges2.length + 1;
			int[][] g2 = packU(m, edges2);

			int[][] p2 = parents(g2, 0);
			int[] eo2 = new int[2];
			for(int i = 0;i < m;i++){
				eo2[p2[2][i] % 2]++;
			}
			int max = Math.max(eo2[0], eo2[1]);

			int[][] p1 = parents(g1, 0);
			int[] eo1 = new int[2];
			for(int i = 0;i < n;i++){
				eo1[p1[2][i] % 2]++;
			}

			int[] ans = new int[n];
			for(int i = 0;i < n;i++){
				ans[i] = eo1[p1[2][i]%2] + max;
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