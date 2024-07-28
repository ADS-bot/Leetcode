class Solution(object):
    def canReachCorner(self, X, Y, circles):
        """
        :type X: int
        :type Y: int
        :type circles: List[List[int]]
        :rtype: bool
        """
        n = len(circles)
        L, R, D, U = [], [], [], []
        markL = [False] * n
        markR = [False] * n
        markD = [False] * n
        markU = [False] * n
        mark = [False] * n
        for i in range(n):
            x, y, r = circles[i]
            cnt = 0
            if x - r <= 0:
                cnt += 1
                markL[i] = True
                L.append(i)
            if x + r >= X:
                cnt += 1
                markR[i] = True
                R.append(i)
            if y - r <= 0:
                cnt += 1
                markD[i] = True
                D.append(i)
            if y + r >= Y:
                cnt += 1
                markU[i] = True
                U.append(i)
        q = deque()
        for v in L:
            mark = [False] * n
            q.append(v)
            mark[v] = True
            while q:
                v = q.popleft()
                if markR[v] or markD[v]:
                    return False
                for i in range(n):
                    x1, y1, r1 = circles[v]
                    x2, y2, r2 = circles[i]
                    if not mark[i] and self.conflict(x1, y1, r1, x2, y2, r2):
                        q.append(i)
                        mark[i] = True
        for v in U:
            mark = [False] * n
            q.append(v)
            mark[v] = True
            while q:
                v = q.popleft()
                if markR[v] or markD[v]:
                    return False
                for i in range(n):
                    x1, y1, r1 = circles[v]
                    x2, y2, r2 = circles[i]
                    if not mark[i] and self.conflict(x1, y1, r1, x2, y2, r2):
                        q.append(i)
                        mark[i] = True
        return True
    def conflict(self, x1, y1, r1, x2, y2, r2):
        return (abs(x1 - x2) ** 2 + abs(y1 - y2) ** 2 <= (r1 + r2) ** 2)