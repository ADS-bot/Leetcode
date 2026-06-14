class Solution {
    public int getLength(int[] nums) {
        int n = nums.length;
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        int id = 0;
        int[] comp = new int[n];
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], id++);
            }
            comp[i] = map.get(nums[i]);
        }
        
        int maxLen = 0;
        int[] freq = new int[id];
        int[] countOfFreq = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(freq, 0);
            java.util.Arrays.fill(countOfFreq, 0);
            int maxFreq = 0;
            int distinctCount = 0;
            
            for (int j = i; j < n; j++) {
                int val = comp[j];
                int oldF = freq[val];
                int newF = oldF + 1;
                
                freq[val] = newF;
                
                if (oldF == 0) {
                    distinctCount++;
                } else {
                    countOfFreq[oldF]--;
                }
                countOfFreq[newF]++;
                
                if (newF > maxFreq) {
                    maxFreq = newF;
                }
                
                boolean isValid = false;
                if (distinctCount == 1) {
                    isValid = true;
                } else if (maxFreq % 2 == 0) {
                    int target = maxFreq / 2;
                    if (countOfFreq[target] > 0 && countOfFreq[maxFreq] + countOfFreq[target] == distinctCount) {
                        isValid = true;
                    }
                }
                
                if (isValid) {
                    int len = j - i + 1;
                    if (len > maxLen) {
                        maxLen = len;
                    }
                }
            }
        }
        return maxLen;
    }
}