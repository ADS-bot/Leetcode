from collections import deque

class TrieNode():
    def __init__(self):
        self.suffixLink = None
        self.output = defaultdict(lambda:10e5)
        self.children = {}
        self.id = -1

class AhoCorasick:
    def __init__(self, words, costs):
        self.initWords(words, costs)
    
    def initWords(self, words, costs):
        self.root = TrieNode()
        
        for i, word in enumerate(words):
            root = self.root
            for c in word:
                if c not in root.children:
                    root.children[c] = TrieNode()
                root = root.children[c]
            if root.id == -1 or costs[i] < costs[root.id]:
                root.id = i
            root.output[len(word)] = min(root.output[len(word)], costs[i])
        
        self.buildAutomata()
    
    def buildAutomata(self):
        q = deque([])
        
        for c, node in self.root.children.items(): 
            q.append(node)
            node.suffixLink = self.root
        
        while q:
            cur = q.popleft()

            for c, node in cur.children.items():
                ptr = cur.suffixLink
                while ptr and c not in ptr.children:
                    ptr = ptr.suffixLink
                
                if ptr and c in ptr.children:
                    node.suffixLink = ptr.children[c]
                else:
                    node.suffixLink = self.root
                
                if node.suffixLink.id >= 0:
                    node.id = node.suffixLink.id
                
                if node.suffixLink is not self.root:
                    for length, cost in node.suffixLink.output.items():
                        node.output[length] = min(node.output[length], cost)
                
                q.append(node)

    def suffixesAfterAppending(self, node, letter):
        while node != self.root and letter not in node.children:
            node = node.suffixLink
        
        if letter in node.children:
            node = node.children[letter]
        else:
            node = self.root
        
        return node
    
class Solution:
    def minimumCost(self, target: str, words: List[str], costs: List[int]) -> int:
        trie = AhoCorasick(words, costs)
        n = len(target)
        dp = [math.inf] * (n+1)
        dp[0] = 0
        cur = trie.root
    
        for i in range(1, n+1):
            cur = trie.suffixesAfterAppending(cur, target[i-1])

            for length, cost in cur.output.items():
                dp[i] = min(dp[i], dp[i-length]+cost)
           
        return -1 if dp[n] == math.inf else dp[n]