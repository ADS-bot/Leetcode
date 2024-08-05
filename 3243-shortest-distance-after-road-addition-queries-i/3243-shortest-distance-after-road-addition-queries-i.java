class Solution {

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        ArrayList<Integer>[] list = new ArrayList[n - 1];
        int[] d = new int[n], result = new int[queries.length];
        for (int i = 1; i < n; i++) {
            list[i - 1] = new ArrayList<>(List.of(i));
            d[i] = i;
        }
        for (int i = 0, k; i < queries.length; i++) {
            list[queries[i][0]].add(queries[i][1]);
            ArrayDeque<Integer> queue = new ArrayDeque<>(List.of(queries[i][0]));
            while (!queue.isEmpty()) {
                if ((k = queue.poll()) == n - 1) {
                    break;
                }
                for (int j : list[k]) {
                    if (d[k] + 1 < d[j]) {
                        d[j] = d[k] + 1;
                        queue.offer(j);
                    }
                }
            }
            result[i] = d[n - 1];
        }
        return result;
    }
}