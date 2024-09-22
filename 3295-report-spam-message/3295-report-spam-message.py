class Solution:
    def reportSpam(self, message: List[str], bannedWords: List[str]) -> bool:
        banned_set = set(bannedWords)
        count = sum(1 for word in message if word in banned_set)
        return count >= 2     