class Solution {

    private static List<Integer>[] graph = new ArrayList[100010];

    static {
        for (int i = 0;i < 100010;i ++) {
            graph[i] = new ArrayList<>();
        }
    }

    private int[] dists;
    private int[] ans;

    public int[] timeTaken(int[][] edges) {
        int m = edges.length , n = m + 1;
        dists = new int[n];
        ans = new int[n];
        for (int i = 0;i < n;i ++) {
            graph[i].clear();
        }
        for (int[] edge : edges) {
            int u = edge[0] , v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        build(0, - 1);
        solve(0, -1, 0);
        return ans;
    }

    private void build(int cur, int parent) {
        for (int next : graph[cur]) {
            if (next != parent) {
                build(next , cur);
                dists[cur] = Math.max(dists[cur] , dists[next] + (next % 2 == 0 ? 2 : 1));
            }
        }
    }

    private void solve(int cur, int parent, int otherDist) {
        ans[cur] = Math.max(dists[cur], otherDist);
        List<Integer> nextList = new ArrayList<>();
        for (int next : graph[cur]) {
            if (next != parent) {
                nextList.add(next);
            }
        }
        int[] prefix = new int[nextList.size()];
        int[] suffix = new int[nextList.size()];
        for (int i = 0;i < nextList.size();i ++) {
            prefix[i] = dists[nextList.get(i)] + (nextList.get(i) % 2 == 0 ? 2 : 1);
            if (i > 0) {
                prefix[i] = Math.max(prefix[i] , prefix[i - 1]);
            }
        }
        for (int i = nextList.size() - 1;i >= 0;i --) {
            suffix[i] = dists[nextList.get(i)] + (nextList.get(i) % 2 == 0 ? 2 : 1);
            if (i + 1 < nextList.size()) {
                suffix[i] = Math.max(suffix[i] , suffix[i + 1]);
            }
        }
        for (int i = 0;i < nextList.size();i ++) {
            int next = nextList.get(i);
            int result = otherDist;
            if (i - 1 >= 0) {
                result = Math.max(prefix[i - 1] , result);
            }
            if (i + 1 < nextList.size()) {
                result = Math.max(suffix[i + 1] , result);
            }
            result += (cur % 2 == 0 ? 2 : 1);
            solve(next, cur, result);
        }
    }

}