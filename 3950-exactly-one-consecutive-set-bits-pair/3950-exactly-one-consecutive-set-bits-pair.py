class Solution:
    def consecutiveSetBits(self, n: int) -> bool:
        return 1 ==(n & n >> 1).bit_count()