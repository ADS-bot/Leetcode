class Solution:
    def regionsBySlashes(self, grid: List[str]) -> int:
        n = len(grid)
        size = n * 3
        visited = [[False] * size for _ in range(size)]
        
        def dfs(x, y):
            if 0 <= x < size and 0 <= y < size and not visited[x][y]:
                visited[x][y] = True
                dfs(x + 1, y)
                dfs(x - 1, y)
                dfs(x, y + 1)
                dfs(x, y - 1)
        
        for i in range(n):
            for j in range(n):
                if grid[i][j] == '/':
                    visited[i * 3][j * 3 + 2] = True
                    visited[i * 3 + 1][j * 3 + 1] = True
                    visited[i * 3 + 2][j * 3] = True
                elif grid[i][j] == '\\':
                    visited[i * 3][j * 3] = True
                    visited[i * 3 + 1][j * 3 + 1] = True
                    visited[i * 3 + 2][j * 3 + 2] = True
        
        regions = 0
        for i in range(size):
            for j in range(size):
                if not visited[i][j]:
                    dfs(i, j)
                    regions += 1
        
        return regions
