class Solution {
    public int maxDistinct(String s) {
        boolean[] seen=new boolean[26];
        int c=0;
        for(int i=0;i<s.length();i++){
            int x=s.charAt(i)-'a';
            if(!seen[x]){seen[x]=true;c++;}
        }
        return c;
    }
}