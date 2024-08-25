from sortedcontainers import SortedList

class Solution:
    def getFinalState(self, nums: List[int], k: int, multiplier: int) -> List[int]:
        n = len(nums)        
        if multiplier == 1:
            return nums
        sl = SortedList([(x, i) for i, x in enumerate(nums)])
        while True:
            (x, i) = sl[0]
            sl.remove((x, i))
            sl.add((x * multiplier, i))
            k -= 1
            if k == 0:
                answer = [0] * n
                for (x, i) in sl:
                    answer[i] = x
                return answer