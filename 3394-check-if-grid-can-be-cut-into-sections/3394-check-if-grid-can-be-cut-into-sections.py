from typing import List
class Solution:
    def checkValidCuts(self, n: int, rectangles: List[List[int]]) -> bool:
        xactions = []
        yactions = []
        for a, b, c, d in rectangles:
            xactions.append((a, 1))
            xactions.append((c, -1))
            yactions.append((b, 1))
            yactions.append((d, -1))
        xactions.sort()
        yactions.sort()
        tmp = 0
        cnt = 0
        for pos, value in xactions:
            tmp += value
            if tmp == 0:
                cnt += 1
        if cnt >= 3:
            return True
        tmp = 0
        cnt = 0
        for pos, value in yactions:
            tmp += value
            if tmp == 0:
                cnt += 1
        if cnt >= 3:
            return True
        return False