class Solution(object):
    def nonSpecialCount(self, l, r):
        """
        :type l: int
        :type r: int
        :rtype: int
        """
        prime = [True] * (int(math.sqrt(r)) + 5)
        v = []
        cnt = 0
        self.SieveOfEratosthenes(len(prime) - 1, prime, v)
        for x in v:
            val = x * x
            if l <= val <= r:
                cnt += 1
        return r - l + 1 - cnt
    def SieveOfEratosthenes(self, n, prime, v):
        prime[0] = prime[1] = False
        for p in range(2, int(math.sqrt(n)) + 1):
            if prime[p]:
                for i in range(p * p, n + 1, p):
                    prime[i] = False
        for p in range(2, n + 1):
            if prime[p]:
                v.append(p)