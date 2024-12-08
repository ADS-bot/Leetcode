from typing import *
from itertools import *
from heapq import *


class Solution:
    def minOperations(self, n: int, m: int) -> int:
        w = len(str(n))
        k = 10 ** (w + 1)
        prime = [True] * (k + 1)
        prime[1] = False
        for i in range(2, k + 1):
            if prime[i]:
                for j in range(i + i, k + 1, i):
                    prime[j] = False
        dis = [-1] * (k + 1)
        if prime[n]:
            return -1
        dis[n] = n
        pq = [(n, n)]
        while len(pq):
            d, u = heappop(pq)
            if d != dis[u]:
                continue
            su = [ord(c) - ord("0") for c in str(u)]
            for i in range(w):
                if su[i] != 9:
                    v = u + 10 ** (w - i - 1)
                    if not prime[v] and (dis[v] == -1 or dis[v] > dis[u] + v):
                        dis[v] = dis[u] + v
                        heappush(pq, (dis[v], v))
                if su[i] > 1 or (su[i] == 1 and i):
                    v = u - 10 ** (w - i - 1)
                    if not prime[v] and (dis[v] == -1 or dis[v] > dis[u] + v):
                        dis[v] = dis[u] + v
                        heappush(pq, (dis[v], v))
        return dis[m]