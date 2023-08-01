class Solution {
    public List<List<Integer>> combine(int n, int k) {
     List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        combineHelper(n, k, 1, current, result);
        return result;
    }
    
    private void combineHelper(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
        // base case: if k is 0, we have chosen k numbers, so add the current combination to the result
        if (k == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // recursive case: choose the next number in the range [start, n] and add it to the current combination
        for (int i = start; i <= n; i++) {
            current.add(i);
            combineHelper(n, k - 1, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}