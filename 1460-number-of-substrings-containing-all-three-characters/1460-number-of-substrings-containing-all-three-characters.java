class Solution {
  // Similar to 3. Longest SubString Without Repeating Characters
  public int numberOfSubstrings(String s) {
    int ans = 0;
    int[] lastSeen = new int[3]; // lastSeen[c] := index of last c appeared.
    Arrays.fill(lastSeen, -1);

    for (int i = 0; i < s.length(); ++i) {
      lastSeen[s.charAt(i) - 'a'] = i;
      // s[0..i], s[1..i], s[Math.min(lastSeen)..i] are satisfied strings.
      ans += 1 + Arrays.stream(lastSeen).min().getAsInt();
    }

    return ans;
  }
}
