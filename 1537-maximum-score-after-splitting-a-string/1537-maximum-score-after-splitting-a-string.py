class Solution:
    def maxScore(self, s: str) -> int:
        maxi = 0
        zero = 0
        for j in range(len(s) - 1):
            one = 0
            if (s[j] == '0'):
                zero += 1
            print("j= ", j)
            for i in range(j + 1, len(s)):
                if s[i] == '1':
                    one += 1
            if (one + zero > maxi):
                maxi = one + zero

        return maxi