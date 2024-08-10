class UnionFind:
    def __init__(self, size):
        self.parent = list(range(size))
        self.rank = [1] * size
    
    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]
    
    def union(self, x, y):
        rootX = self.find(x)
        rootY = self.find(y)
        
        if rootX != rootY:
            if self.rank[rootX] > self.rank[rootY]:
                self.parent[rootY] = rootX
            elif self.rank[rootX] < self.rank[rootY]:
                self.parent[rootX] = rootY
            else:
                self.parent[rootY] = rootX
                self.rank[rootX] += 1

class Solution:
    def regionsBySlashes(self, grid: List[str]) -> int:
        n = len(grid)
        uf = UnionFind(4 * n * n)  # 4 triangles per 1x1 square
        
        for i in range(n):
            for j in range(n):
                index = 4 * (i * n + j)
                
                if grid[i][j] == '/':
                    # Connect triangles 0-3, 1-2
                    uf.union(index + 0, index + 3)
                    uf.union(index + 1, index + 2)
                elif grid[i][j] == '\\':
                    # Connect triangles 0-1, 2-3
                    uf.union(index + 0, index + 1)
                    uf.union(index + 2, index + 3)
                else:
                    # Connect all triangles in the square
                    uf.union(index + 0, index + 1)
                    uf.union(index + 1, index + 2)
                    uf.union(index + 2, index + 3)
                
                # Union with right and bottom neighbors
                if j + 1 < n:  # Connect right neighbor
                    uf.union(index + 1, 4 * (i * n + (j + 1)) + 3)
                if i + 1 < n:  # Connect bottom neighbor
                    uf.union(index + 2, 4 * ((i + 1) * n + j) + 0)
        
        # Count number of connected components
        region_count = sum(1 for i in range(4 * n * n) if uf.find(i) == i)
        
        return region_count
