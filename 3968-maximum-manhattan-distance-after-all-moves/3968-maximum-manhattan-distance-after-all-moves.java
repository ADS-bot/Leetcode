class Solution {
    public int maxDistance(String moves) {
         int dx = 0, dy = 0, wild = 0;

        for (char ch : moves.toCharArray()) {
            switch (ch) {
                case 'U':
                    dy++;
                    break;
                     case 'D':
                    dy--;
                    break;
                case 'L':
                    dx--;
                    break;
                case 'R':
                    dx++;
                    break;
                case '_':
                    wild++;
                    break;
            }
        }

        return Math.abs(dx) + Math.abs(dy) + wild;
        
    }
}