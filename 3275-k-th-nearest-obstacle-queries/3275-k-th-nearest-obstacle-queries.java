class Solution {
    public int[] resultsArray(int[][] queries, int k) {
        int n = queries.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            pq.add(Math.abs(queries[i][0]) + Math.abs(queries[i][1]));
            while (pq.size() > k)
                pq.poll();
            if (pq.size() == k)
                ans[i] = pq.peek();
        }

        return ans;
    }
}
