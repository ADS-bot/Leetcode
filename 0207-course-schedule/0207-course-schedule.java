class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Initialize adjacency list and in-degree array
        List<List<Integer>> adjList = new ArrayList<>(numCourses);
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        
        // Build adjacency list and in-degree array
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prereqFor = prereq[1];
            adjList.get(prereqFor).add(course);
            inDegree[course]++;
        }
        
        // Add all courses with in-degree of 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        
        // Process courses in the queue and decrement in-degrees of their neighbors
        int numProcessed = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            numProcessed++;
            for (int neighbor : adjList.get(curr)) {
                if (--inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        // If all courses were processed, return true; otherwise, return false
        return numProcessed == numCourses;
    }
}