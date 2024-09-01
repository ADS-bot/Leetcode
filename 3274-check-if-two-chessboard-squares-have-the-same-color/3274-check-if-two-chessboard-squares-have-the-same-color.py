class Solution:
    def checkTwoChessboards(self, c: str, c2: str) -> bool:
        return (ord(c[0])-ord('a') + int(c[1]) - ord(c2[0])-ord('a') + int(c2[1])) %  2 == 0