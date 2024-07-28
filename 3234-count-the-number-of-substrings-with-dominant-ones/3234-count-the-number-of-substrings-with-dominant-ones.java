class Solution {

    public int numberOfSubstrings(String s) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                deque.offer(i);
            }
        }
        deque.offer(s.length());
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            while (!deque.isEmpty() && deque.peek() < i) {
                deque.poll();
            }
            int k = i - 1, x = 0, y = 0;
            for (int j : deque) {
                count += j - k - 1 < x * x - y ? 0 : j - k - Math.max(1, x * x - y);
                if (++x * x <= (y += j - k - 1) & (k = j) < s.length()) {
                    count++;
                } else if (x * x > y + s.length() - j - 1) {
                    break;
                }
            }
        }
        return count;
    }
}