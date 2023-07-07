class Solution:
    def stringMatching(self, words: List[str]) -> List[str]:
        res = set()
        for word in words:
            for cur in words:
                if cur in word and cur != word:
                    res.add(cur)
        return list(res)