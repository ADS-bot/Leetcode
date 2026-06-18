class Solution:
    def angleClock(self, hour: int, minutes: int) -> float:
        ha = (hour % 12) * 30 + minutes * 0.5
        ma = minutes * 6
        dd = abs(ha - ma)
        return min(dd, 360 - dd)