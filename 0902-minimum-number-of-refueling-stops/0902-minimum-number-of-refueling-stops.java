class Solution {
  public int minRefuelStops(int target, int startFuel, int[][] stations) {
    int ans = 0;
    int i = 0; // stations's index
    int curr = startFuel;
    Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    while (curr < target) {
      // Add all reachable stops to maxHeap
      while (i < stations.length && curr >= stations[i][0])
        maxHeap.offer(stations[i++][1]);
      if (maxHeap.isEmpty()) // We can't refuel
        return -1;
      curr += maxHeap.poll(); // Pop out the largest gas
      ++ans;                  // Then refuel once
    }

    return ans;
  }
}
