class Solution {
    public int lengthAfterTransformations(String s, int t) {
        int count[] = new int[26], sum = 0;
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (int i = 0, j = 25; i < t; i++, j = (j + 25) % 26) {
            count[(j + 1) % 26] = (count[(j + 1) % 26] + count[j]) % 1000000007;
        }
        for (int i : count) {
            sum = (sum + i) % 1000000007;
        }
        return sum;
    }
}