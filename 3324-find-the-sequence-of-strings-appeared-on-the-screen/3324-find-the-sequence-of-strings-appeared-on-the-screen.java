class Solution {
    public List<String> stringSequence(String target) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            for (char c = 'a'; c <= target.charAt(i); c++) {
                list.add(target.substring(0, i) + c);
            }
        }
        return list;
    }
}