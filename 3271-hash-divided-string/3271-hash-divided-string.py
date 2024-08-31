class Solution:
    def stringHash(self, s: str, k: int) -> str:
        return ''.join(chr(sum(ord(s[j]) - ord('a') for j in range(i, i + k)) % 26 + ord('a')) for i in range(0, len(s), k))