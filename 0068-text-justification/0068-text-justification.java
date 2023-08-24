class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int n = words.length;
        int i = 0;
        while (i < n) {
            int j = i + 1;
            int len = words[i].length();
            while (j < n && len + words[j].length() + (j - i) <= maxWidth) {
                len += words[j].length();
                j++;
            }
            int spaces = maxWidth - len;
            int gaps = j - i - 1;
            StringBuilder sb = new StringBuilder(words[i]);
            if (gaps == 0 || j == n) {
                for (int k = i + 1; k < j; k++) {
                    sb.append(' ');
                    sb.append(words[k]);
                }
                for (int k = sb.length(); k < maxWidth; k++) {
                    sb.append(' ');
                }
            } else {
                int spacesPerGap = spaces / gaps;
                int extraSpaces = spaces % gaps;
                for (int k = i + 1; k < j; k++) {
                    for (int p = 0; p < spacesPerGap; p++) {
                        sb.append(' ');
                    }
                    if (extraSpaces > 0) {
                        sb.append(' ');
                        extraSpaces--;
                    }
                    sb.append(words[k]);
                }
            }
            result.add(sb.toString());
            i = j;
        }
        return result;
    }
}