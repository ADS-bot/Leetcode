class Solution {
    public boolean[] findAnswer(int[] parent, String s) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i < parent.length; i++) {
            map.computeIfAbsent(parent[i], t -> new ArrayList<>()).add(i);
        }
        StringBuilder sb = new StringBuilder();
        int[][] range = new int[parent.length][2];
        findAnswer(0, sb, range, s, map);
        Manacher manacher = new Manacher("" + sb);
        boolean[] result = new boolean[parent.length];
        for (int i = 0; i < parent.length; i++) {
            result[i] = manacher.p[range[i][0] + range[i][1] + 1] >= range[i][1] - range[i][0];
        }
        return result;
    }
    private void findAnswer(int k, StringBuilder sb, int[][] range, String s, HashMap<Integer, ArrayList<Integer>> map) {
        range[k][0] = sb.length();
        for (int i : map.getOrDefault(k, new ArrayList<>())) {
            findAnswer(i, sb, range, s, map);
        }
        sb.append(s.charAt(k));
        range[k][1] = sb.length();
    }
}

class Manacher {
    public int[] p;
    private String s;
    private char[] t;
    public Manacher(String s) {
        this.s = s;
        preprocess();
        p = new int[t.length];
        int center = 0, right = 0;
        for (int i = 1; i < t.length-1; i++) {
            int mirror = 2*center - i;
            if (right > i)
                p[i] = Math.min(right - i, p[mirror]);
            while (t[i + (1 + p[i])] == t[i - (1 + p[i])])
                p[i]++;
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }
    }
    private void preprocess() {
        t = new char[s.length()*2 + 3];
        t[0] = '$';
        t[s.length()*2 + 2] = '@';
        for (int i = 0; i < s.length(); i++) {
            t[2*i + 1] = '#';
            t[2*i + 2] = s.charAt(i);
        }
        t[s.length()*2 + 1] = '#';
    }
}