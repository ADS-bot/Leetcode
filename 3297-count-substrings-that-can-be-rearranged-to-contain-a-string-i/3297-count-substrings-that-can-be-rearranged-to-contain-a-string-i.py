class Solution:
    def validSubstringCount(self, word1: str, word2: str) -> int:
        from collections import defaultdict

        counts_word2 = defaultdict(int)
        required_letters = set(word2)
        for c in word2:
            counts_word2[c] += 1

        N = len(word1)
        counts = defaultdict(int)
        total_substrings = 0
        left = 0
        right = 0

        while left < N:
            while right < N and any(counts[char] < counts_word2[char] for char in counts_word2):
                counts[word1[right]] +=1
                right +=1
            if any(counts[char] < counts_word2[char] for char in counts_word2):
                break
            total_substrings += N - right +1
            counts[word1[left]] -=1
            left +=1

        return total_substrings        