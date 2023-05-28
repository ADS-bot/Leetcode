class Solution(object):
    def allCellsDistOrder(self, R, C, r0, c0):
        """
        :type R: int
        :type C: int
        :type r0: int
        :type c0: int
        :rtype: List[List[int]]
        """
        dis = []
        for r in range(R):
            for c in range(C):
                dis.append((abs(r0 - r) + abs(c0 - c), [r, c]))
        dis.sort()
        return [x for d, x in dis]