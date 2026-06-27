class Solution:
    def shortestPath(self, n: int, edges: List[List[int]], labels: str, k: int) -> int:
        adj = [[] for _ in range(n)]
        for u, v, w in edges:
            adj[u].append((v, w))

        dist = [{} for _ in range(n)]
        sc = ord(labels[0]) - 97
        dist[0][(sc, 1)] = 0
        h = [(0, 0, sc, 1)]

        while h:
            d, u, c, cnt = heapq.heappop(h)
            if d != dist[u].get((c, cnt)):
                continue
            if u == n - 1:
                return d
            for v, w in adj[u]:
                nc = ord(labels[v]) - 97
                ncnt = cnt + 1 if nc == c else 1
                if ncnt > k:
                    continue
                nd = d + w
                key = (nc, ncnt)
                if nd < dist[v].get(key, float('inf')):
                    dist[v][key] = nd
                    heapq.heappush(h, (nd, v, nc, ncnt))
        return -1