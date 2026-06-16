class Solution:
    def processStr(self, s: str) -> str:
        r = ""
        for c in s:
            if c.isalpha():
                r += c
            elif c == '*':
                if r:
                    r = r[:-1]
            elif c == '#':
                r += r
            else:
                r = r[::-1]
        return r