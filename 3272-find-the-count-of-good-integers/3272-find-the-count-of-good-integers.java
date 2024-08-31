class Solution {
    
    Stack<Integer> stack;
    
    long result;
    
    long [] factorial;
    
    Set<String> key;
    
    public long countGoodIntegers(int n, int k) {
        int l = 0;
        int r = n - 1;
        
        int [] modArr = new int [n];
        
        int val = 1;
        
        for (int i = 0; i < n; ++i) {
            modArr[i] = val;
            val *= 10;
            val %= k;
        }
        
        result = 0;
        
        factorial = new long [11];
        factorial[0] = 1;
        
        for (int i = 1; i < factorial.length; ++i) {
            factorial[i] = i * factorial[i - 1];
        }
        
        key = new HashSet<>();
        stack = new Stack<>();
        
        solve(0 ,r, l, modArr, k);
        
        return result;
    }
    
    
    private void extract() {
        int [] count = new int [10];
        
        for (int num : stack) {
            count[num]++;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int num : count) {
            sb.append(num + ":");
        }
        
        String s = sb.toString();
        
        if (!key.add(s))
            return;
        
        // System.out.println(stack);
        
        long add = solve(count);
        
        if (count[0] > 0) {
            count[0]--;
            add -= solve(count);
        }
        
        result += add;
    }
    
    private long solve(int [] count) {
        int total = 0;
        
        for (int num : count) {
            total += num;
        }
        
        long res = factorial[total];
        
        for (int num : count) {
            res /= factorial[num];
        }
        
        return res;
    }
    
    private void solve(int cMod, int r, int l, int [] modArr, int k) {
        
        if (r < l){
            if (cMod == 0) {
                extract();
            }
            return;
        }
        
        // System.out.println(r + " " + l + " " + stack);
        
        int start = l == 0 ? 1 : 0;
        
        long result = 0;
        int nextMod;
        
        for (int i = start; i <= 9; ++i) {
            nextMod = i * modArr[r];
            nextMod %= k;
            
            stack.push(i);
            
            if (r != l) {
                nextMod += i * modArr[l];
                nextMod %= k;
                
                stack.push(i);
            }
            
            nextMod += cMod;
            nextMod %= k;
            
            
            
            solve(nextMod, r - 1, l + 1, modArr, k);
            
            stack.pop();
            
            if (r != l) {
                stack.pop();
            }
        }
    }
}
