class Solution:
    def stringSequence(self, target: str) -> List[str]:
        current = ''
        result = []
        
        for char in target:
            if not current:
                current += 'a'
                result.append(current)
            
            while current[-1] != char:
                current = current[:-1] + chr((ord(current[-1]) - ord('a') + 1) % 26 + ord('a'))
                result.append(current)
            
            if len(current) < len(target):
                current += 'a'
                result.append(current)
        
        return result