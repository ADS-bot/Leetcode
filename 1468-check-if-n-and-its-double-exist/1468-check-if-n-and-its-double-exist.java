class Solution {
    public boolean checkIfExist(int[] arr) {
        if (arr == null || arr.length < 2) {
            return false;
        }
        
        Arrays.sort(arr);
        
        for (int i = 0; i < arr.length; ++i) {
            int currentValue = arr[i];
            int idx = Arrays.binarySearch(arr, 2 * currentValue);
            if (idx >= 0 && idx != i) {
                return true;
            }
        }
        
        return false;
    }
}