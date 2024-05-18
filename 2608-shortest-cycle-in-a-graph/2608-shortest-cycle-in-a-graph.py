from collections import deque, defaultdict

class Solution:
    def findShortestCycle(self, n: int, edges: List[List[int]]) -> int:
        graph = defaultdict(list)
        
        # Build the graph
        for u, v in edges:
            graph[u].append(v)
            graph[v].append(u)
        
        def bfs(start):
            dist = [-1] * n
            queue = deque([(start, -1)])  # (current_vertex, parent_vertex)
            dist[start] = 0
            min_cycle_length = float('inf')
            
            while queue:
                current, parent = queue.popleft()
                current_dist = dist[current]
                
                for neighbor in graph[current]:
                    if neighbor == parent:
                        continue
                    if dist[neighbor] == -1:
                        dist[neighbor] = current_dist + 1
                        queue.append((neighbor, current))
                    else:
                        # A cycle is detected
                        cycle_length = current_dist + dist[neighbor] + 1
                        min_cycle_length = min(min_cycle_length, cycle_length)
            
            return min_cycle_length
        
        shortest_cycle = float('inf')
        
        # Perform BFS from each vertex to find the shortest cycle
        for i in range(n):
            shortest_cycle = min(shortest_cycle, bfs(i))
        
        # If we did not find any cycle, return -1
        return shortest_cycle if shortest_cycle != float('inf') else -1

# Example usage:
# sol = Solution()
# print(sol.findShortestCycle(7, [[0, 1], [1, 2], [2, 0], [3, 4], [4, 5], [5, 6], [6, 3]]))  # Output: 3
# print(sol.findShortestCycle(4, [[0, 1], [0, 2]]))  # Output: -1
