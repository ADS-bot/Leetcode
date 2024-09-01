class Solution {
		public int maxScore(List<List<Integer>> grid) {
			int n = grid.size();
			int m = grid.get(0).size();

			List<Edge> es = new ArrayList<>();
			int src = 10 + 101, sink = src + 1;
			for(int i = 0;i < n;i++){
				es.add(new Edge(src, i, 1, 0));
				for(int j = 0;j < m;j++){
					es.add(new Edge(i, 10+grid.get(i).get(j), 1, 0));
				}
			}
			for(int j = 1;j <= 100;j++){
				es.add(new Edge(10+j, sink, 1, 100-j));
			}
			Edge[][] g = compileWD(10+101+2, es);
			long C = solveMinCostFlow(g, src, sink, 10);
			long f = 0;
			for(Edge e : g[src]){
				f += e.flow;
			}
			return (int)(100*f - C);
		}

		public static class Edge
		{
			public int from, to;
			public int capacity;
			public int cost;
			public int flow;
			public Edge complement;
			// public int iniflow;

			public Edge(int from, int to, int capacity, int cost) {
				this.from = from;
				this.to = to;
				this.capacity = capacity;
				this.cost = cost;
			}
		}

		public static Edge[][] compileWD(int n, List<Edge> edges)
		{
			Edge[][] g = new Edge[n][];
			// cloning
			for(int i = edges.size()-1;i >= 0;i--){
				Edge origin = edges.get(i);
				Edge clone = new Edge(origin.to, origin.from, origin.capacity, -origin.cost);
				clone.flow = origin.capacity;
				clone.complement = origin;
				origin.complement = clone;
				edges.add(clone);
			}

			int[] p = new int[n];
			for(Edge e : edges)p[e.from]++;
			for(int i = 0;i < n;i++)g[i] = new Edge[p[i]];
			for(Edge e : edges)g[e.from][--p[e.from]] = e;
			return g;
		}

		// NOT VERIFIED
		public static Edge[][] compileWU(int n, List<Edge> edges)
		{
			Edge[][] g = new Edge[n][];
			// cloning
			for(int i = edges.size()-1;i >= 0;i--){
				Edge origin = edges.get(i);
				Edge back = new Edge(origin.to, origin.from, origin.capacity, origin.cost);
				edges.add(back);
			}
			for(int i = edges.size()-1;i >= 0;i--){
				Edge origin = edges.get(i);
				Edge clone = new Edge(origin.to, origin.from, origin.capacity, -origin.cost);
				clone.flow = origin.capacity;
				clone.complement = origin;
				origin.complement = clone;
				edges.add(clone);
			}

			int[] p = new int[n];
			for(Edge e : edges)p[e.from]++;
			for(int i = 0;i < n;i++)g[i] = new Edge[p[i]];
			for(Edge e : edges)g[e.from][--p[e.from]] = e;
			return g;
		}

		public static class MinHeap {
			public int[] a;
			public int[] map;
			public int[] imap;
			public int n;
			public int pos;
			public static int INF = Integer.MAX_VALUE;

			public MinHeap(int m)
			{
				n = m+2;
				a = new int[n];
				map = new int[n];
				imap = new int[n];
				Arrays.fill(a, INF);
				Arrays.fill(map, -1);
				Arrays.fill(imap, -1);
				pos = 1;
			}

			public int add(int ind, int x)
			{
				int ret = imap[ind];
				if(imap[ind] < 0){
					a[pos] = x; map[pos] = ind; imap[ind] = pos;
					pos++;
					up(pos-1);
				}
				return ret != -1 ? a[ret] : x;
			}

			public int update(int ind, int x)
			{
				int ret = imap[ind];
				if(imap[ind] < 0){
					a[pos] = x; map[pos] = ind; imap[ind] = pos;
					pos++;
					up(pos-1);
				}else{
					int o = a[ret];
					a[ret] = x;
					up(ret);
					down(ret);
					//			if(a[ret] > o){
					//				up(ret);
					//			}else{
					//				down(ret);
					//			}
				}
				return x;
			}

			public int remove(int ind)
			{
				if(pos == 1)return INF;
				if(imap[ind] == -1)return INF;

				pos--;
				int rem = imap[ind];
				int ret = a[rem];
				map[rem] = map[pos];
				imap[map[pos]] = rem;
				imap[ind] = -1;
				a[rem] = a[pos];
				a[pos] = INF;
				map[pos] = -1;

				up(rem);
				down(rem);
				return ret;
			}

			public int min() { return a[1]; }
			public int argmin() { return map[1]; }
			public int size() {	return pos-1; }
			public int get(int ind){ return a[imap[ind]]; }

			private void up(int cur)
			{
				for(int c = cur, p = c>>>1;p >= 1 && a[p] > a[c];c>>>=1, p>>>=1){
					int d = a[p]; a[p] = a[c]; a[c] = d;
					int e = imap[map[p]]; imap[map[p]] = imap[map[c]]; imap[map[c]] = e;
					e = map[p]; map[p] = map[c]; map[c] = e;
				}
			}

			private void down(int cur)
			{
				for(int c = cur;2*c < pos;){
					int b = a[2*c] < a[2*c+1] ? 2*c : 2*c+1;
					if(a[b] < a[c]){
						int d = a[c]; a[c] = a[b]; a[b] = d;
						int e = imap[map[c]]; imap[map[c]] = imap[map[b]]; imap[map[b]] = e;
						e = map[c]; map[c] = map[b]; map[b] = e;
						c = b;
					}else{
						break;
					}
				}
			}
		}



		public static long solveMinCostFlow(Edge[][] g, int source, int sink, long all)
		{
			int n = g.length;
			long mincost = 0;
			int[] potential = new int[n];

			final int[] d = new int[n];
			MinHeap q = new MinHeap(n);
			while(all > 0){
				// shortest path src->sink
				Edge[] inedge = new Edge[n];
				Arrays.fill(d, Integer.MAX_VALUE / 2);
				d[source] = 0;
				q.add(source, 0);
				while(q.size() > 0){
					int cur = q.argmin();
					q.remove(cur);
					for(Edge ne : g[cur]){
						if(ne.capacity - ne.flow > 0){
							int nd = d[cur] + ne.cost + potential[cur] - potential[ne.to];
							if(d[ne.to] > nd){
								inedge[ne.to] = ne;
								d[ne.to] = nd;
								q.update(ne.to, nd);
							}
						}
					}
				}

				if(inedge[sink] == null)break;

				long minflow = all;
				long sumcost = 0;
				for(Edge e = inedge[sink];e != null;e = inedge[e.from]){
					if(e.capacity - e.flow < minflow)minflow = e.capacity - e.flow;
					sumcost += e.cost;
				}
				mincost += minflow * sumcost;
				for(Edge e = inedge[sink];e != null;e = inedge[e.from]){
					e.flow += minflow;
					e.complement.flow -= minflow;
				}

				all -= minflow;
				for(int i = 0;i < n;i++){
					potential[i] += d[i];
				}
			}
			return mincost;
		}

	}
