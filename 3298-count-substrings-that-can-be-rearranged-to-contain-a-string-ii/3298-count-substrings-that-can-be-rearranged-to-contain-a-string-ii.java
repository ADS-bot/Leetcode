class Solution {
    boolean match (int[] cou1,int[] cou2){
        for (int i=0;i<26;i++){
            if (cou1[i]<cou2[i]){
                return false;
            }
        }
        return true;
    }
    public long validSubstringCount(String word1, String word2) {
        int[] cou=new int[26];
        int[] cou2=new int[26];
        for (int i=0;i<word2.length();i++){
            cou2[word2.charAt(i)-97]++;
        }
        int n=word1.length();
        int st=0;long ret=0L;
        for (int i=0;i<n;i++){
            cou[word1.charAt(i)-97]++;
            if (match(cou,cou2)){
                while (match(cou,cou2)){
                    cou[word1.charAt(st)-97]--;
                    st++;
                }
                st--;cou[word1.charAt(st)-97]++;
                ret=ret+st+1;
            }
        }
        return ret;   
    }
}