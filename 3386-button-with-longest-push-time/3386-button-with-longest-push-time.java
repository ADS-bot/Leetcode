class Solution {
    public int buttonWithLongestTime(int[][] events) {
        int max = -1;
        int arg = -1;
        int b = 0;
        for(int[] e : events){
            int t = e[1] - b;
            if(t > max || t == max && e[0] < arg){
                max = t;
                arg = e[0];
            }
            b = e[1];
        }
        return arg;
    }
}