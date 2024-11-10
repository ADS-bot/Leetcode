class Solution:
    def smallestNumber(self, n: int, t: int) -> int:
        def check(n: int, t: int):
            prod = 1
            for c in str(n):
                prod *= ord(c) - ord("0")
            return prod % t == 0

        while not check(n, t):
            n += 1
        return n