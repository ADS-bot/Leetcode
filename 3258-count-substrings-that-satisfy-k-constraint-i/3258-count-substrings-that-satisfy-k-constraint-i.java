class Solution {
    public int countKConstraintSubstrings(String s, int k) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] count = new int[2];
            for (int j = i; j < s.length(); j++) {
                count[s.charAt(j) - '0']++;
                result += count[0] > k && count[1] > k ? 0 : 1;
            }
}
        return result;
    }
}