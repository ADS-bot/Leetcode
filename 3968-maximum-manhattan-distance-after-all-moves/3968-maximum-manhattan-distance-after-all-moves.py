class Solution:
    def maxDistance(self, moves: str) -> int:
        x = y = free = 0

        for c in moves:
            if c == 'U':
                y += 1
            elif c == 'D':
                y -= 1
            elif c == 'R':
                x += 1
            elif c == 'L':
                x -= 1
            else:
                free += 1

        return abs(x) + abs(y) + free