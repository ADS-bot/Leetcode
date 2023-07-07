class Solution:
    def reformat(self, s: str) -> str:
        strs = [c for c in s if not c.isdigit()]
        digits = [c for c in s if c.isdigit()]
        res = ""
        while strs and digits:
            res += strs.pop() + digits.pop()
        if len(strs) > 1 or len(digits) > 1:
            return ""
        elif len(strs) == 1:
            res += strs.pop()
        elif len(digits) == 1:
            res = digits.pop() + res
        return res