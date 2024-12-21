class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        int m = rectangles.length;
        Arrays.sort(rectangles,(o1,o2)->Integer.compare(o1[0],o2[0]));
        int max = rectangles[0][2];
        int cnt = 0;
        for(int i=1;i<m;i++) {
            if(rectangles[i][0]>=max) cnt++;
            max = Math.max(max,rectangles[i][2]);
        }
        if(cnt>=2) return true;
        Arrays.sort(rectangles,(o1,o2)->Integer.compare(o1[1],o2[1]));
        max = rectangles[0][3];
        cnt = 0;
        for(int i=1;i<m;i++) {
            if(rectangles[i][1]>=max) cnt++;
            max = Math.max(max,rectangles[i][3]);
        }
        return cnt>=2;
    }
}