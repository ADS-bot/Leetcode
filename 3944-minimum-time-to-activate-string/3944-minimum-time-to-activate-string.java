class Solution {
    public int minTime(String s, int[] order, int k) {
        int n = s.length();
        long totalSubstrings = (long)n * (n + 1) / 2;

        if (k > totalSubstrings) {
            return -1;
        }

        TreeSet<int[]> segments = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
        if (n > 0) {
            segments.add(new int[]{0, n - 1});
        }
        
        long invalidSubsCount = totalSubstrings;

        for (int t = 0; t < n; t++) {
            int idx = order[t];
            
            int[] dummy = new int[]{idx, 0};
            int[] segment = segments.floor(dummy);

            if (segment != null && idx <= segment[1]) {
                segments.remove(segment);
                
                int start = segment[0];
                int end = segment[1];
                long len = (long)end - start + 1;
                
                invalidSubsCount -= len * (len + 1) / 2;
                
                if (start <= idx - 1) {
                    int[] leftSegment = new int[]{start, idx - 1};
                    long leftLen = (long)leftSegment[1] - leftSegment[0] + 1;
                    segments.add(leftSegment);
                    invalidSubsCount += leftLen * (leftLen + 1) / 2;
                }
                
                if (idx + 1 <= end) {
                    int[] rightSegment = new int[]{idx + 1, end};
                    long rightLen = (long)rightSegment[1] - rightSegment[0] + 1;
                    segments.add(rightSegment);
                    invalidSubsCount += rightLen * (rightLen + 1) / 2;
                }
            }
            
            long validSubsCount = totalSubstrings - invalidSubsCount;
            if (validSubsCount >= k) {
                return t;
            }
        }
        
        return -1;
    }
}