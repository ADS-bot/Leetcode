class Solution {
    public int countOfSubstrings(String word, int k) {
        int dp[] = new int[word.length() + 1], result = 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            if (i == word.length() - 1 || "aeiou".indexOf(word.charAt(i + 1)) < 0) {
                dp[i] = "aeiou".indexOf(word.charAt(i)) < 0 ? 0 : 1;
            } else {
                dp[i] = dp[i + 1] + 1;
            }
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < word.length(); i++) {
            for (; j < word.length() && (map.size() < 5 || k > 0); j++) {
                if ("aeiou".indexOf(word.charAt(j)) < 0) {
                    k--;
                } else {
                    map.put(word.charAt(j), map.getOrDefault(word.charAt(j), 0) + 1);
                }
            }
            if (map.size() == 5 && k == 0) {
                result += Math.max(1, dp[j - 1]);
            }
            if ("aeiou".indexOf(word.charAt(i)) < 0) {
                k++;
            } else {
                map.put(word.charAt(i), map.get(word.charAt(i)) - 1);
                if (map.get(word.charAt(i)) == 0) {
                    map.remove(word.charAt(i));
                }
            }
        }
        return result;
    }
}