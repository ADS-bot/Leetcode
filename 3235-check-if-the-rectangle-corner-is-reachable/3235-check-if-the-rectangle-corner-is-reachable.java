class Solution {

    public boolean canReachCorner(int X, int Y, int[][] circles) {
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < circles.length; i++) {
            if (circles[i][0] - circles[i][2] <= 0 || circles[i][1]  + circles[i][2] >= Y) {
                deque.offer(circles[i]);
                circles[i] = null;
            }
        }
        for (int[] circle; !deque.isEmpty();) {
            if ((circle = deque.poll())[0] + circle[2] >= X || circle[1] - circle[2] <= 0) {
                return false;
            }
            for (int i = 0; i < circles.length; i++) {
                if (circles[i] != null && Math.abs(circle[0] - circles[i][0]) * Math.abs(circle[0] - circles[i][0]) + Math.abs(circle[1] - circles[i][1]) * Math.abs(circle[1] - circles[i][1]) <= (circle[2] + circles[i][2]) * (circle[2] + circles[i][2])) {
                    deque.offer(circles[i]);
                    circles[i] = null;
                }
            }
        }
        return true;
    }
}