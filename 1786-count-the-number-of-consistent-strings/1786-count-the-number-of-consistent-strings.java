class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        Set<Character> set = new HashSet<>();
        
        for (char ch : allowed.toCharArray()) {
            set.add(ch);
        }
        
        int res = 0;
        for (String w : words) {
            boolean match = true;
            
            for (char ch : w.toCharArray()) {
                if (!set.contains(ch)) {
                    match = false;
                    break;
                }
            }
            
            if(match){
                res++;
            }


            
        }
    
        return res;
    }
}