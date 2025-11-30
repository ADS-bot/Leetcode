class Solution {

    static class G {
        int sz;
        int[] idx;
        long[] v;
        long[] pv;
        int[][] seg;
        long[][] sp;
        long[] u;

        G(List<Integer> ps, List<Long> vs) {
            sz = ps.size();
            idx = new int[sz];
            v = new long[sz];
            pv = new long[sz + 1];

            int i = 0;
            while (i < sz) {
                idx[i] = ps.get(i);
                v[i] = vs.get(i);
                pv[i + 1] = pv[i] + v[i];
                i++;
            }

            long[] tmp = new long[sz];
            i = 0;
            while (i < sz) {
                tmp[i] = v[i];
                i++;
            }
            Arrays.sort(tmp);

            ArrayList<Long> uu = new ArrayList<>();
            i = 0;
            while (i < sz) {
                if (i == 0 || tmp[i] != tmp[i - 1]) uu.add(tmp[i]);
                i++;
            }

            u = new long[uu.size()];
            i = 0;
            while (i < uu.size()) {
                u[i] = uu.get(i);
                i++;
            }

            seg = new int[4 * Math.max(1, sz)][];
            sp = new long[4 * Math.max(1, sz)][];

            if (sz > 0) b(1, 0, sz - 1);
        }

        private void b(int node, int l, int r) {
            if (l == r) {
                seg[node] = new int[]{(int) v[l]};
                sp[node] = new long[]{v[l]};
                return;
            }
            int mid = (l + r) >>> 1;
            b(node << 1, l, mid);
            b(node << 1 | 1, mid + 1, r);

            int[] a = seg[node << 1];
            int[] b = seg[node << 1 | 1];
            int na = a.length, nb = b.length;

            int[] mrg = new int[na + nb];
            long[] mp = new long[na + nb];

            int i = 0, j = 0, k = 0;
            long rs = 0;

            while (i < na && j < nb) {
                if (a[i] <= b[j]) {
                    mrg[k] = a[i++];
                } else {
                    mrg[k] = b[j++];
                }
                rs += mrg[k];
                mp[k] = rs;
                k++;
            }

            while (i < na) {
                mrg[k] = a[i++];
                rs += mrg[k];
                mp[k] = rs;
                k++;
            }

            while (j < nb) {
                mrg[k] = b[j++];
                rs += mrg[k];
                mp[k] = rs;
                k++;
            }

            seg[node] = mrg;
            sp[node] = mp;
        }

        int cntLE(int node, int l, int r, int ql, int qr, long val) {
            if (ql > r || qr < l) return 0;
            if (ql <= l && r <= qr) {
                return ubA(seg[node], val);
            }
            int mid = (l + r) >>> 1;
            return cntLE(node << 1, l, mid, ql, qr, val)
                    + cntLE(node << 1 | 1, mid + 1, r, ql, qr, val);
        }

        long sumLE(int node, int l, int r, int ql, int qr, long val) {
            if (ql > r || qr < l) return 0L;
            if (ql <= l && r <= qr) {
                int[] arr = seg[node];
                long[] pref = sp[node];
                int id = ubA(arr, val);
                return id == 0 ? 0L : pref[id - 1];
            }
            int mid = (l + r) >>> 1;
            return sumLE(node << 1, l, mid, ql, qr, val)
                    + sumLE(node << 1 | 1, mid + 1, r, ql, qr, val);
        }

        private int ubA(int[] arr, long val) {
            int l = 0, r = arr.length;
            while (l < r) {
                int m = (l + r) >>> 1;
                if (arr[m] <= val) l = m + 1;
                else r = m;
            }
            return l;
        }
    }

    private static HashMap<Long, G> mk(int[] a, int k) {
        HashMap<Long, ArrayList<Integer>> pm = new HashMap<>();
        HashMap<Long, ArrayList<Long>> vm = new HashMap<>();

        int nn = a.length;
        for (int i = 0; i < nn; i++) {
            long rem = a[i] % (long) k;
            pm.computeIfAbsent(rem, x -> new ArrayList<>()).add(i);

            long bi = (a[i] - rem) / (long) k;
            vm.computeIfAbsent(rem, x -> new ArrayList<>()).add(bi);
        }

        HashMap<Long, G> gm = new HashMap<>();
        for (Map.Entry<Long, ArrayList<Integer>> e : pm.entrySet()) {
            long r = e.getKey();
            gm.put(r, new G(e.getValue(), vm.get(r)));
        }
        return gm;
    }

    private static int lbArr(int[] arr, int val) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (arr[m] < val) l = m + 1;
            else r = m;
        }
        return l;
    }

    private static int ubArr(int[] arr, int val) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (arr[m] <= val) l = m + 1;
            else r = m;
        }
        return l;
    }

    private static long proc(G g, int l, int r) {
        int li = lbArr(g.idx, l);
        int ri = ubArr(g.idx, r) - 1;
        int cnt = (ri >= li) ? (ri - li + 1) : 0;

        if (cnt <= 1) return cnt == 0 ? -2L : 0L;

        int kth = (cnt + 1) / 2;
        int low = 0, high = g.u.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1;
            long tv = g.u[mid];
            int c = g.cntLE(1, 0, g.sz - 1, li, ri, tv);
            if (c >= kth) high = mid;
            else low = mid + 1;
        }

        long med = g.u[low];
        long sumL = g.sumLE(1, 0, g.sz - 1, li, ri, med);
        long tot = g.pv[ri + 1] - g.pv[li];

        long cntL = g.cntLE(1, 0, g.sz - 1, li, ri, med);
        long cntR = cnt - cntL;

        long sumR = tot - sumL;

        return med * cntL - sumL + sumR - med * cntR;
    }

    public long[] minOperations(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        HashMap<Long, G> gm = mk(nums, k);

        int q = queries.length;
        long[] res = new long[q];

        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            long rem = nums[l] % (long) k;
            G g = gm.get(rem);

            if (g == null) {
                res[i] = -1;
                continue;
            }

            long out = proc(g, l, r);

            if (out == -2L) {
                res[i] = -1;
            } else {
                int li = lbArr(g.idx, l);
                int ri = ubArr(g.idx, r) - 1;
                int cnt = (ri >= li) ? (ri - li + 1) : 0;

                if (cnt != (r - l + 1)) res[i] = -1;
                else res[i] = out;
            }
        }
        return res;
    }
}