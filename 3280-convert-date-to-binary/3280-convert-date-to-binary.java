class Solution {
    public String convertDateToBinary(String date) {
        String[] arr = date.split("-");
        String res = "";
        for (String s : arr) {
            if (res.length() > 0) res += '-';
            res += Integer.toBinaryString(Integer.parseInt(s));
        }
        return res;
    }
}