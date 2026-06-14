import java.util.Arrays;

class Solution {
    
    static class LongMaxHeap {
        long[] data;
        int size;
        
        public LongMaxHeap(int cap) {
            data = new long[cap + 2];
            size = 0;
        }
        
        public void push(long val) {
            data[++size] = val;
            int i = size;
            while (i > 1 && data[i] > data[i / 2]) {
                long tmp = data[i]; 
                data[i] = data[i / 2]; 
                data[i / 2] = tmp;
                i /= 2;
            }
        }
        
        public long pop() {
            long res = data[1];
            data[1] = data[size--];
            int i = 1;
            while (2 * i <= size) {
                int child = 2 * i;
                if (child < size && data[child + 1] > data[child]) {
                    child++;
                }
                if (data[i] >= data[child]) break;
                long tmp = data[i]; 
                data[i] = data[child]; 
                data[child] = tmp;
                i = child;
            }
            return res;
        }
        
        public int size() { 
            return size; 
        }
        
        public void clear() {
            size = 0;
        }
    }

    public long maxSum(int[] nums, int k) {
        int n = nums.length;
        long max_ans = Long.MIN_VALUE;
        
        long[][] pairs = new long[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        
        Arrays.sort(pairs, (a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(b[0], a[0]);
            }
            return Long.compare(b[1], a[1]);
        });
        
        boolean[] in_S = new boolean[n];
        int[] O_ordered = new int[n - 1];
        int[] pos_in_O = new int[n];
        LongMaxHeap maxHeap = new LongMaxHeap(n + 2);
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(in_S, false);
            
            int idx = 0;
            for (int r = 0; r < n; r++) {
                int orig_idx = (int)pairs[r][1];
                if (orig_idx != i) {
                    O_ordered[idx] = orig_idx;
                    pos_in_O[orig_idx] = idx;
                    idx++;
                }
            }
            
            int p = 0;
            in_S[i] = true;
            long total_M_sum = nums[i];
            
            maxHeap.clear();
            long sum_heap = nums[i];
            maxHeap.push(nums[i]);
            
            for (int step = 0; step < k; step++) {
                if (p < n - 1) {
                    long val = nums[O_ordered[p]];
                    total_M_sum += val;
                    maxHeap.push(val);
                    sum_heap += val;
                    p++;
                } else {
                    break;
                }
            }
            
            int m = Math.min(k, n - 1);
            while (maxHeap.size() > m) {
                sum_heap -= maxHeap.pop();
            }
            
            max_ans = Math.max(max_ans, total_M_sum - sum_heap);
            
            for (int j = i + 1; j < n; j++) {
                in_S[j] = true;
                boolean added = false;
                long val_to_add = 0;
                
                if (pos_in_O[j] < p) {
                    while (p < n - 1 && in_S[O_ordered[p]]) {
                        p++;
                    }
                    if (p < n - 1) {
                        val_to_add = nums[O_ordered[p]];
                        added = true;
                        p++;
                    }
                } else {
                    val_to_add = nums[j];
                    added = true;
                }
                
                if (added) {
                    total_M_sum += val_to_add;
                    maxHeap.push(val_to_add);
                    sum_heap += val_to_add;
                }
                
                m = Math.min(k, n - 1 - (j - i));
                while (maxHeap.size() > m) {
                    sum_heap -= maxHeap.pop();
                }
                
                max_ans = Math.max(max_ans, total_M_sum - sum_heap);
            }
        }
        
        return max_ans;
    }
}