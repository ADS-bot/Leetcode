class Solution:
    def minDamage(self, power: int, damage: List[int], health: List[int]) -> int:
        hs = []
        ret = 0
        for d, h in zip(damage, health):
            hits = int(math.ceil(h / power))
            heappush(hs, (- d / hits, hits, d))
        dam = sum(damage)
        while hs:
            _, hits, d = heappop(hs)
            ret += dam * hits
            dam -= d
        return ret