class Solution {

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] result = new int[queries.length];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0, k = n - 1; i < queries.length; i++) {
            Map.Entry<Integer, Integer> entry = map.floorEntry(queries[i][0]);
            if (entry == null || entry.getValue() < queries[i][1]) {
                for (; (entry = map.ceilingEntry(queries[i][0])) != null && entry.getValue() <= queries[i][1]; map.remove(entry.getKey())) {
                    k += entry.getValue() - entry.getKey() - 1;
                }
                map.put(queries[i][0], queries[i][1]);
                k -= queries[i][1] - queries[i][0] - 1;
            }
            result[i] = k;
        }
        return result;
    }
}