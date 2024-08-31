class Solution {
    public String stringHash(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int val;
        
        for (int i = 0; i < s.length() / k; ++i) {
            val = 0;
            
            for (int j = 0; j < k; ++j) {
                val += s.charAt(i * k + j) - 'a';    
            }
            
            val %= 26;
            sb.append((char)(val + 'a'));
        }
        
        return sb.toString();
    }
}