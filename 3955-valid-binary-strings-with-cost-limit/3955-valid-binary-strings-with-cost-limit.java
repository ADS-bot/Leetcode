class Solution {
    public List<String> generateValidStrings(int n, int k) {
        List<String> res=new ArrayList<>();
        buildString(n,k,0,"",0,res);
        return res;
    }
    private void buildString(int n,int k,int index,String currentStr,int currentCost,List<String> res){
        if(currentCost>k){
            return;
        }
        if(index==n){
            res.add(currentStr);
            return;
        }
        buildString(n,k,index+1,currentStr+"0",currentCost,res);
        if (currentStr.isEmpty() || currentStr.charAt(currentStr.length() - 1) != '1') {
            buildString(n, k, index + 1, currentStr + "1", currentCost + index, res);
        }
    }
}