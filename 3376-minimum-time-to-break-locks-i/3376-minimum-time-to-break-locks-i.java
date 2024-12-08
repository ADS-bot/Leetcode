public class Solution {
    private int minTime;
    private int[] strengthArray;
    private int K;
    private int n;

    
    public int findMinimumTime(List<Integer> strength, int K) {
        this.strengthArray = new int[strength.size()];
        for (int i = 0; i < strength.size(); i++) {
            this.strengthArray[i] = strength.get(i);
        }
        this.K = K;
        this.n = strength.size();
        this.minTime = Integer.MAX_VALUE;

        boolean[] used = new boolean[n];
        backtrack(0, 1, 0, used);
        return minTime;
    }

    
    private void backtrack(int index, int currentX, int currentTime, boolean[] used) {
     
        if (index == n) {
            minTime = Math.min(minTime, currentTime);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                int requiredStrength = strengthArray[i];
                int timeToBreak = (requiredStrength + currentX - 1) / currentX; 
                int newTime = currentTime + timeToBreak;

                if (newTime >= minTime) {
                    continue;
                }

                used[i] = true;
                backtrack(index + 1, currentX + K, newTime, used);
                used[i] = false; 
            }
        }
    }
}