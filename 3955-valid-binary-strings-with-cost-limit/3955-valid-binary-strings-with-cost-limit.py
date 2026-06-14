class Solution:
    def generateValidStrings(self, n: int, k: int) -> list[str]:
        ans = []
        
        def dfs(i, cost, current_str):
            if cost > k:
                return
            if i == n:
                ans.append(current_str)
                return
                
            dfs(i + 1, cost, current_str + "0")
            
            if not current_str or current_str[-1] == '0':
                dfs(i + 1, cost + i, current_str + "1")
                
        dfs(0, 0, "")
        return ans