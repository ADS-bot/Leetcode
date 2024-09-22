class Solution:
    def validSubstringCount(self, word1: str, word2: str) -> int:
        N = len(word1)
        need = [0]*26
        for c in word2:
            need[ord(c)-ord('a')] +=1
        have = [0]*26
        letters_needed = [i for i in range(26) if need[i]>0]
        left = 0
        total = 0
        for right in range(N):
            c = ord(word1[right])-ord('a')
            have[c] +=1
            while all(have[i]>=need[i] for i in letters_needed):
                have[ord(word1[left])-ord('a')] -=1
                left +=1
            total += left
        return total        