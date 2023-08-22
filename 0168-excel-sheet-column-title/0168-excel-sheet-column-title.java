class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            int remainder = columnNumber % 26;
            char c = (char) ('A' + remainder);
            sb.insert(0, c);
            columnNumber /= 26;
        }
        return sb.toString();
    }
}
