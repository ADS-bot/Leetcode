
class Solution {
    private boolean isPossible(String s, int L, int numOps) {
        if (L == 1) {
            int flips1 = 0; 
            int flips2 = 0; 
            for (int i = 0; i < s.length(); ++i) {
                char expected1 = (i % 2 == 0) ? '0' : '1';
                char expected2 = (i % 2 == 0) ? '1' : '0';
                if (s.charAt(i) != expected1) flips1++;
                if (s.charAt(i) != expected2) flips2++;
            }
            return Math.min(flips1, flips2) <= numOps;
        } else {
            int sumFlips = 0;
            int n = s.length();
            int i = 0;
            while (i < n) {
                char current = s.charAt(i);
                int runLength = 1;
                while (i + 1 < n && s.charAt(i + 1) == current) {
                    i++;
                    runLength++;
                }
                if (runLength > L) {
                    int excess = runLength - L;
                    int flips = (excess + L) / (L + 1);
                    sumFlips += flips;
                }
                i++;
            }
            return sumFlips <= numOps;
        }
    }

    public int minLength(String s, int numOps) {
        int n = s.length();
        if (n == 0) return 0;

        int low = 1;
        int high = n;
        int result = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(s, mid, numOps)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}