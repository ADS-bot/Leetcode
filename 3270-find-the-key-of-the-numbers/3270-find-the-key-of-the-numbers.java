class Solution {
    public int generateKey(int num1, int num2, int num3) {
        String s1 = augment(num1);
        String s2 = augment(num2);
        String s3 = augment(num3);
        
        String [] arr = new String [] {s1, s2, s3};
        
        String res = "";
        
        for (int i = 0; i < 4; ++i) {
            int result = 10;
            int val;
            for (String s : arr) {
                val = s.charAt(i) - '0';
                result = Math.min(val, result);
            }
            
            res += result;
        }
        
        return Integer.parseInt(res);
    }
    
    private String augment(int num1) {
        String s1 = num1 + "";
        
        while (s1.length() < 4) {
            s1 = "0" + s1;
        }
        
        return s1;
    }
}
