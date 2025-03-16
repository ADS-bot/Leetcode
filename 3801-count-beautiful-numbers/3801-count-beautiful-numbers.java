import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

import static java.lang.Math.*;


public class Solution {
    static PrintWriter out;
    static int inf = (int) 1e9;
    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) {
        FastReader in = new FastReader();
        out = new PrintWriter(System.out);
        long t = in.nextInt();
        long test = 1;
        while (test <= t) {
            test++;
        }
        out.close();
    }
     private static int gcd(int a, int b) {
        return b == 0 ? a : (gcd(b, a % b));
    }

    static boolean[] sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }
        return prime;
    }

    static void sort(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int i : a) l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++) a[i] = l.get(i);
    }

    static long modPow(long a, long b, long m) {
        long res = 1;
        a %= m;
        while (b > 0) {
            if ((b & 1) != 0) {
                res = res * a;
                res %= m;
            }
            b >>= 1;
            a *= a;
            a %= m;
        }
        return res;
    }

    private static class Pair implements Comparable<Pair> {
        int ff, ss;

        Pair(int x, int y) {
            this.ff = x;
            this.ss = y;
        }

        public int compareTo(Pair o) {
            return this.ff == o.ff ? this.ss - o.ss : this.ff - o.ff;
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static int[][] sumreq = new int[82][4];
    static boolean[] validsum = new boolean[82];

    static {
        for (int s = 1; s < 82; s++) {
            int tmp = s;
            int r2 = 0, r3 = 0, r5 = 0, r7 = 0;
            int t = tmp;
            while (t % 2 == 0) {
                r2++;
                t /= 2;
            }
            while (t % 3 == 0) {
                r3++;
                t /= 3;
            }
            while (t % 5 == 0) {
                r5++;
                t /= 5;
            }
            while (t % 7 == 0) {
                r7++;
                t /= 7;
            }
            validsum[s] = (t == 1);
            sumreq[s][0] = r2;
            sumreq[s][1] = r3;
            sumreq[s][2] = r5;
            sumreq[s][3] = r7;
        }
    }

    static int[][] digitfac = new int[10][5];

    static {
        digitfac[0] = new int[]{0, 0, 0, 0, 0};
        digitfac[1] = new int[]{0, 0, 0, 0, 1};
        digitfac[2] = new int[]{1, 0, 0, 0, 2};
        digitfac[3] = new int[]{0, 1, 0, 0, 3};
        digitfac[4] = new int[]{2, 0, 0, 0, 4};
        digitfac[5] = new int[]{0, 0, 1, 0, 5};
        digitfac[6] = new int[]{1, 1, 0, 0, 6};
        digitfac[7] = new int[]{0, 0, 0, 1, 7};
        digitfac[8] = new int[]{3, 0, 0, 0, 8};
        digitfac[9] = new int[]{0, 2, 0, 0, 9};
    }

    String sdig;
    int nd;

    Map<Integer, Long> memo_nz = new HashMap<>();
    Map<Integer, Long> memo_b = new HashMap<>();

    public int beautifulNumbers(int l, int r) {
        long ans = cntB(r) - cntB(l - 1);
        return (int) ans;
    }

    public long cntB(int N) {
        if (N < 1) return 0;
        long a = cntNZdp(Integer.toString(N));
        long b = cntBdp(Integer.toString(N));
        return (long) N - a + b;
    }

    public long cntNZdp(String s) {
        sdig = s;
        nd = sdig.length();
        memo_nz.clear();
        return dpnz(0, 1, 0);
    }

    private long dpnz(int pos, int tight, int st) {
        if (pos == nd) {
            return (st == 1) ? 1 : 0;
        }
        int key = (pos << 3) | (tight << 2) | st;
        if (memo_nz.containsKey(key))
            return memo_nz.get(key);
        long res = 0;
        int lim = (tight == 1) ? sdig.charAt(pos) - '0' : 9;
        for (int d = 0; d <= lim; d++) {
            int nt = (tight == 1 && d == lim) ? 1 : 0;
            if (st == 0) {
                if (d == 0)
                    res += dpnz(pos + 1, nt, 0);
                else
                    res += dpnz(pos + 1, nt, 1);
            } else {
                if (d == 0) continue;
                res += dpnz(pos + 1, nt, 1);
            }
        }
        memo_nz.put(key, res);
        return res;
    }

    public long cntBdp(String nstr) {
        sdig = nstr;
        nd = sdig.length();
        memo_b.clear();
        return dpb(0, 1, 0, 0, 0, 0, 0, 0);
    }

    private long dpb(int pos, int tight, int st, int sum, int a, int b, int c, int d) {
        if (pos == nd) {
            if (st == 0) return 0;
            if (sum < 1) return 0;
            if (!validsum[sum]) return 0;
            int r2 = sumreq[sum][0];
            int r3 = sumreq[sum][1];
            int r5 = sumreq[sum][2];
            int r7 = sumreq[sum][3];
            return (a >= r2 && b >= r3 && c >= r5 && d >= r7) ? 1 : 0;
        }
        int key = pack(pos, tight, st, sum, a, b, c, d);
        if (memo_b.containsKey(key))
            return memo_b.get(key);
        long res = 0;
        int lim = (tight == 1) ? sdig.charAt(pos) - '0' : 9;
        for (int dgt = 0; dgt <= lim; dgt++) {
            int nt = (tight == 1 && dgt == lim) ? 1 : 0;
            if (st == 0) {
                if (dgt == 0)
                    res += dpb(pos + 1, nt, 0, sum, a, b, c, d);
                else {
                    res = helper(pos, sum, a, b, c, d, res, dgt, nt);
                }
            } else {
                if (dgt == 0) continue;
                res = helper(pos, sum, a, b, c, d, res, dgt, nt);
            }
        }
        memo_b.put(key, res);
        return res;
    }

    private long helper(int pos, int sum, int a, int b, int c, int d, long res, int dgt, int nt) {
        int ns = sum + dgt;
        int na = a + digitfac[dgt][0];
        int nb = b + digitfac[dgt][1];
        int nc = c + digitfac[dgt][2];
        int ndp = d + digitfac[dgt][3];
        res += dpb(pos + 1, nt, 1, ns, na, nb, nc, ndp);
        return res;
    }

    private int pack(int pos, int tight, int st, int sum, int a, int b, int c, int d) {
        int key = pos;
        key |= (tight << 5);
        key |= (st << 6);
        key |= (sum << 7);
        key |= (a << 14);
        key |= (b << 19);
        key |= (c << 24);
        key |= (d << 28);
        return key;
    }
}