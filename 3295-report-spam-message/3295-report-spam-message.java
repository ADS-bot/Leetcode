class Solution {
    public boolean reportSpam(String[] message, String[] bannedWords) {
        Set<String> bw=new HashSet<>();
        for (int i=0;i<bannedWords.length;i++){
            bw.add(bannedWords[i]);
        }
        int cou=0;
        for (int i=0;i<message.length;i++){
            if (bw.contains(message[i])){
                cou++;
                if (cou>=2){
                    return true;
                }
            }
        }
        return false;
    }
}