class Solution {
  public int minOperations(String s1, String s2, int x) {
    List<Integer> diffIndices = getDiffIndices(s1, s2);
    if (diffIndices.isEmpty())
      return 0;
    // It's impossible to make two strings equal if there are odd number of
    // differences.
    if (diffIndices.size() % 2 == 1)
      return -1;

    double dp = 0;
    double dpNext = x / 2.0;
    double dpNextNext = 0;

    for (int i = diffIndices.size() - 2; i >= 0; --i) {
      dp = Math.min(dpNext + x / 2.0, //
                    dpNextNext + diffIndices.get(i + 1) - diffIndices.get(i));
      dpNextNext = dpNext;
      dpNext = dp;
    }

    return (int) dp;
  }

  private List<Integer> getDiffIndices(final String s1, final String s2) {
    List<Integer> diffIndices = new ArrayList<>();
    for (int i = 0; i < s1.length(); ++i)
      if (s1.charAt(i) != s2.charAt(i))
        diffIndices.add(i);
    return diffIndices;
  }
}