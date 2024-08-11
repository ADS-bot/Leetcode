class Solution {
    public int finalPositionOfSnake(int n, List<String> commands) {
        int ans = 0;
        for(String c : commands){
            if(c.startsWith("R")){
                ans++;
            }else if(c.startsWith("L")){
                ans--;
            }else if(c.startsWith("U")){
                ans -= n;
            }else{
                ans += n;
            }
        }
        return ans;
    }
}