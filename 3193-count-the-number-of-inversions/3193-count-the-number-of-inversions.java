class Solution {

    static int MOD = (int) 1e9 + 7;

    int dp[][];
    boolean vis[][];

    long solve(int n, HashMap<Integer, Integer> req, int index, int count) {

        if (index == 0) {
            if (count == 0) {
                return 1;
            }

            return 0;
        }

        if (req.containsKey(index)) {
            if (count != req.get(index)) {
                return 0;
            }
        }

        if (vis[index][count]) {
            return dp[index][count];
        }

        long ans = 0;

        for (int i = 0; i <= index; i++) {
            int contribution = i;

            if (contribution > count) {
                continue;
            }

            ans = ans + solve(n, req, index - 1, count - contribution);
            ans = ans % MOD;
        }

        vis[index][count] = true;
        dp[index][count] = (int) ans;

        return ans;
    }

    public int numberOfPermutations(int n, int[][] requirements) {

        HashMap<Integer, Integer> reqMap = new HashMap<>();

        int maxCount = 0;

        for (int i = 0; i < requirements.length; i++) {
            reqMap.put(requirements[i][0], requirements[i][1]);
            maxCount = Math.max(maxCount, requirements[i][1]);
        }

        if(reqMap.containsKey(0) && reqMap.get(0) > 0){
            return 0;
        }

        dp = new int[n + 1][maxCount + 1];
        vis = new boolean[n + 1][maxCount + 1];

        return (int) solve(n, reqMap, n - 1, reqMap.get(n - 1));
    }
}