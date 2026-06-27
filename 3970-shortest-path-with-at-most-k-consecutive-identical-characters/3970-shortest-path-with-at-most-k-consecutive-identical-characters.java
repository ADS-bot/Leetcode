import java.util.*;

class Solution {

    static class Edge {
        int to, w;

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
    static class State {
        long dist;
        int node;
        int cnt;
        
        State(long dist, int node, int cnt) {
            this.dist = dist;
             this.node = node;
            this.cnt = cnt;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[1], e[2]));
        }

        long INF = Long.MAX_VALUE / 4;

        long[][] dist = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        dist[0][1] = 0;

        PriorityQueue<State> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

        pq.offer(new State(0, 0, 1));
        
        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.dist != dist[cur.node][cur.cnt]) {
                continue;
            }

            if (cur.node == n - 1) {
                return (int) cur.dist;
            }
              char curChar = labels.charAt(cur.node);

            for (Edge e : graph[cur.node]) {

                int next = e.to;
                char nextChar = labels.charAt(next);

                int nextCnt;

                if (nextChar == curChar) {
                    nextCnt = cur.cnt + 1;
                } else {
                    nextCnt = 1;
                }
                
 if (nextCnt > k) {
                    continue;
                }

                long nd = cur.dist + e.w;

                if (nd < dist[next][nextCnt]) {
                    dist[next][nextCnt] = nd;
                    pq.offer(new State(nd, next, nextCnt));
                }
            }
        }

        return -1;
    }
}