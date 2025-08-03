class Solution {
    public int maxBalancedShipments(int[] weight) {
        int n = weight.length;
        if (n == 0) {
            return 0;
        }

        int[] pge = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && weight[stack.peek()] <= weight[i]) {
                stack.pop();
            }
            pge[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int[] dp = new int[n + 1];
        int[] max_dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];

            int currentWeightIndex = i - 1;
            int k = pge[currentWeightIndex];

            if (k != -1) {
                dp[i] = Math.max(dp[i], max_dp[k] + 1);
            }
            
            max_dp[i] = Math.max(max_dp[i - 1], dp[i]);
        }

        return dp[n];
    }
}