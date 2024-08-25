from sortedcontainers import SortedList
class Solution:
    def getFinalState(self, nums: List[int], k: int, multiplier: int) -> List[int]:
        def power(p, x, e):
            base = x
            answer = 1
            while e:
                if e & 1:
                    answer = (answer * base) % p
                base = (base * base) % p
                e >>= 1
            return answer
        n = len(nums)
        mod = 10**9 + 7
        if multiplier == 1:
            return nums
        sl = SortedList([(x, i) for i, x in enumerate(nums)])
        while sl[0][0] <= 10**9:
            (x, i) = sl[0]
            sl.remove((x, i))
            sl.add((x * multiplier, i))
            k -= 1
            if k == 0:
                answer = [0] * n
                for (x, i) in sl:
                    answer[i] = x % mod
                return answer
        q, r = k // n, k % n
        p1, p2 = power(mod, multiplier, q + 1), power(mod, multiplier, q)
        answer = [0] * n
        for i in range(r):
            x, idx = sl[i]
            answer[idx] = (x * p1) % mod
        for i in range(r, n):
            x, idx = sl[i]
            answer[idx] = (x * p2) % mod
        return answer