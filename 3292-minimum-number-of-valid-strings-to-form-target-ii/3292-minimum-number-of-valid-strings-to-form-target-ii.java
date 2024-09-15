import java.util.*;

class Utils {
    public static int log2(long n) {
        return 63 - Long.numberOfLeadingZeros(n);
    }

    // nums = "aaabaaaba" odd d[1] return [1, 2, 1, 4, 1, 2, 2]
    // d[0] for even, d[1] for odd
    public static int[][] manacher(String s) {
        int n = s.length();
        int[] d1 = new int[n];
        for (int i = 0, l = 0, r = -1; i < n; ++i) {
            int k = (i > r) ? 1 : Math.min(d1[l + r - i], r - i + 1);
            while (0 <= i - k && i + k < n && s.charAt(i - k) == s.charAt(i + k)) k++;
            d1[i] = k;
            if (i + k - 1 > r) {
                l = i - k + 1;
                r = i + k - 1;
            }
        }

        int[] d0 = new int[n];
        for (int i = 0, l = 0, r = -1; i < n; i++) {
            int k = (i > r) ? 0 : Math.min(d0[l + r - i + 1], r - i + 1);
            while (0 <= i - k - 1 && i + k < n && s.charAt(i - k - 1) == s.charAt(i + k)) {
                k++;
            }
            d0[i] = k;
            if (i + k - 1 > r) {
                l = i - k;
                r = i + k - 1;
            }
        }
        return new int[][]{d0, d1};
    }

    // generate primes that is <= n
    public static int[] genPrimes(int n) {
        if (n <= 1) return new int[0];
        boolean[] isComp = new boolean[n + 1];
        int len = 0;
        for (int i = 2; i <= n; i++) {
            if (!isComp[i]) {
                len++;
                for (int j = i + i; j <= n; j += i) {
                    isComp[j] = true;
                }
            }
        }
        int[] ans = new int[len];
        for (int i = 2, j = 0; i <= n; i++) {
            if (!isComp[i]) {
                ans[j++] = i;
            }
        }
        return ans;
    }

    // int[0] = primeFactor, int[1] = count of that primeFactor
    // NOTE: factors[0] and factors[1] are empty list.
    public static List<int[]>[] genPrimeFactors(int n) {
        List<int[]>[] factors = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            factors[i] = new ArrayList<>();
        }
        boolean[] isComp = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!isComp[i]) {
                factors[i].add(new int[]{i, 1});
                for (int j = i + i; j <= n; j += i) {
                    isComp[j] = true;
                    int t = j, count = 0;
                    while (t % i == 0) {
                        count++;
                        t /= i;
                    }
                    factors[j].add(new int[]{i, count});
                }
            }
        }
        return factors;
    }

    // generate factors (not prime factors), factors[0] is empty list.
    public static List<Integer>[] genFactors(int n) {
        List<Integer>[] factors = new List[n + 1];
        for (int i = 0; i <= n; i++) factors[i] = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                factors[j].add(i);
            }
        }
        return factors;
    }

    public static int lowerBound(int[] a, int target) {
        return lowerBound(a, target, a.length);
    }

    public static int lowerBound(int[] a, int target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int upperBound(int[] a, int target) {
        return upperBound(a, target, a.length);
    }

    public static int upperBound(int[] a, int target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int lowerBound(long[] a, long target) {
        return lowerBound(a, target, a.length);
    }

    public static int lowerBound(long[] a, long target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int upperBound(long[] a, long target) {
        return upperBound(a, target, a.length);
    }

    public static int upperBound(long[] a, long target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int lowerBound(T[] a, T target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int upperBound(T[] a, T target, int n) {
        int low = 0;
        int high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid].compareTo(target) <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int lowerBound(List<T> a, T target) {
        int low = 0;
        int high = a.size();
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a.get(mid).compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static <T extends Comparable<T>> int upperBound(List<T> a, T target) {
        int low = 0;
        int high = a.size();
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a.get(mid).compareTo(target) <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static long or(int x, int y) {
        return ((long) x << 32) | ((long) y << 32 >>> 32);
    }

    // k = 0...nums.length - 1
    // After calling this function, nums[k] is the k-th number.
    public static int kthNumber(int[] nums, int k) {
        return kthNumberHelper(nums, k, 0, nums.length - 1);
    }

    // end is inclusive (0 to nums.length - 1)
    private static int kthNumberHelper(int[] nums, int K, int start, int end) {
        if (start == end) return nums[start];
        int i = partition(nums, start, end);
        return K <= i ? kthNumberHelper(nums, K, start, i) : kthNumberHelper(nums, K, i + 1, end);
    }

    // end is inclusive (0 to nums.length - 1)
    public static int partition(int[] nums, int start, int end) {
        int pos = start + ((end - start) >> 1);
        int pivot = nums[pos];

        int i = start, j = end;
        nums[pos] = nums[end];
        while (i < j) {
            while (i < j && nums[i] < pivot) i++;
            if (i < j) nums[j--] = nums[i];

            while (i < j && pivot < nums[j]) j--;
            if (i < j) nums[i++] = nums[j];
        }

        // pos is the final position for pivot.
        nums[i] = pivot;
        return i;
    }

    public static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    // partition nums into 3 parts [smaller than pivot, equal to pivot, larger than pivot]
    public static void partition3(int[] nums, int pivot) {
        int n = nums.length;
        int l = 0, r = n - 1;
        for (int i = 0; i <= r; i++) {
            if (nums[i] < pivot) {
                swap(nums, l++, i);
            } else if (nums[i] > pivot) {
                swap(nums, r--, i--);
            }
        }
    }

    // end is inclusive
    public static void reverse(int[] nums, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }

    // Return false if next permutation is not available. (nums is not changed for this case)
    public static boolean nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return false;
        int i = n - 1;
        while (i - 1 >= 0 && nums[i - 1] >= nums[i]) {
            i--;
        }
        if (i <= 0) return false;
        for (int j = n - 1; j >= i; j--) {
            if (nums[j] > nums[i - 1]) {
                int t = nums[j];
                nums[j] = nums[i - 1];
                nums[i - 1] = t;

                reverse(nums, i, n - 1);
                break;
            }
        }
        return true;
    }

    // Return false if previous permutation is not available. (nums is not changed for this case)
    public static boolean prevPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) return false;
        int i = n - 1;
        while (i - 1 >= 0 && nums[i - 1] <= nums[i]) {
            i--;
        }
        if (i <= 0) return false;
        for (int j = n - 1; j >= i; j--) {
            if (nums[j] < nums[i - 1]) {
                int t = nums[j];
                nums[j] = nums[i - 1];
                nums[i - 1] = t;

                reverse(nums, i, n - 1);
                break;
            }
        }
        return true;
    }

    // This function is deprecated, please hash(int l, int r) in class StableStringHash or StringHash
    // rolling hash of substrings of 's' with length == k
    // long[pos] = hash(s.substring(pos, pos + k))
    public static long[] rollingHash(String s, int k) {
        long[] ans = new long[s.length() - k + 1];
        int seed1 = 31;
        int seed2 = 131;
        int h1 = 0, h2 = 0, power1 = 1, power2 = 1;
        for (int i = 0; i < k; i++) {
            h1 = h1 * seed1 + s.charAt(i);
            h2 = h2 * seed2 + s.charAt(i);

            power1 *= seed1;
            power2 *= seed2;
        }

        ans[0] = ((long) h1 << 32) | ((long) h2 << 32 >>> 32);
        for (int i = k; i < s.length(); i++) {
            h1 = h1 * seed1 + s.charAt(i) - power1 * s.charAt(i - k);
            h2 = h2 * seed2 + s.charAt(i) - power2 * s.charAt(i - k);
            ans[i - k + 1] = ((long) h1 << 32) | ((long) h2 << 32 >>> 32);
        }

        return ans;
    }

    public static int[] kmpNext(String s) {
        int n = s.length();
        int[] next = new int[n];
        next[0] = 0;
        for (int i = 1; i < n; i++) {
            int k = next[i - 1];
            while (k > 0 && s.charAt(i) != s.charAt(k)) k = next[k - 1];
            if (s.charAt(i) == s.charAt(k)) {
                next[i] = k + 1;
            } else {
                next[i] = 0;
            }
        }
        return next;
    }

    // s is the original String
    // p is the pattern String
    public static boolean kmpMatch(String s, String p) {
        int[] next = kmpNext(p);
        int n = s.length();
        int m = p.length();
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && p.charAt(j) != s.charAt(i)) {
                j = next[j - 1];
            }
            if (p.charAt(j) == s.charAt(i)) j++;
            if (j == m) return true;
        }
        return false;
    }

    public static int[] kmpNext(int[] s) {
        int n = s.length;
        int[] next = new int[n];
        next[0] = 0;
        for (int i = 1; i < n; i++) {
            int k = next[i - 1];
            while (k > 0 && s[i] != s[k]) k = next[k - 1];
            if (s[i] == s[k]) {
                next[i] = k + 1;
            } else {
                next[i] = 0;
            }
        }
        return next;
    }

    // s is the original String
    // p is the pattern String
    public static boolean kmpMatch(int[] s, int[] p) {
        int[] next = kmpNext(p);
        int n = s.length;
        int m = p.length;
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && p[j] != s[i]) {
                j = next[j - 1];
            }
            if (p[j] == s[i]) j++;
            if (j == m) return true;
        }
        return false;
    }

    // a, b could be negative
    // remainder >= 0
    // return [a / b, a % b]
    public static int[] divMod(int a, int b) {
        int r = a % b;
        int c = a / b;
        if (r < 0) {
            r += Math.abs(b);
            c++;
        }
        return new int[]{c, r};
    }

    public static List<Integer> negativeBase(int n, int base) {
        List<Integer> digits = new ArrayList<>();
        if (n == 0) {
            digits.add(0);
            return digits;
        }
        while (n != 0) {
            // t[0] = n / base
            // t[1] = n % base;
            int[] t = divMod(n, base);
            digits.add(t[1]);
            n = t[0];
        }
        Collections.reverse(digits);
        return digits;
    }

    // (a ^ b) % MOD
    public static long powMod(long a, long b, long MOD) {
        long res = 1L;
        a %= MOD; // In case a * a is overflow
        while (b > 0) {
            if ((b & 1) != 0) res = (res * a) % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    public static long[][] genCombination(int n) {
        long[][] C = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i && j <= n; j++) {
                if (j == 0 || i == j) C[i][j] = 1;
                else C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]);
            }
        }
        return C;
    }

    public static long[][] genPermutation(int n) {
        long[][] P = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i && j <= n; j++) {
                if (i == 0 || j == 0) P[i][j] = 1;
                else P[i][j] = P[i][j - 1] * (i - j + 1);
            }
        }
        return P;
    }

    public static long gcd(long x, long y) {
        return x != 0 ? gcd(y % x, x) : y;
    }

    public static int[] preprocessLog2(int n) { // from log2(0) .... log2(n) inclusive
        int[] lg = new int[n + 1];
        for (int k = 0, i = 1; i <= n; lg[i++] = k - 1) {
            while ((1 << k) <= i) {
                k++;
            }
        }
        return lg;
    }

    public static long max(long[] nums) {
        long ret = Long.MIN_VALUE;
        for (long v : nums) ret = Math.max(ret, v);
        return ret;
    }

    public static int max(int[] nums) {
        int ret = Integer.MIN_VALUE;
        for (int v : nums) ret = Math.max(ret, v);
        return ret;
    }
}

// Tested by leetcode 1163
class SuffixArray {
    int[] s;
    int[] SA; // SA[i] => index of the i-th ranked suffix
    int[] rank; // rank[i] => the rank of i-th suffix, SA[rank[i]] == i
    int[] height; // height[i] = LCP(s[SA[i]], s[SA[i - 1]]).

    private int[][] d;
    private int n;
    private int[] lg;

    private int log2(int n) {
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    private static int[] stringToIntArray(String str) {
        int[] nums = new int[str.length()];
        for (int i = 0; i < str.length(); i++) nums[i] = str.charAt(i);
        return nums;
    }

    public SuffixArray(String str) {
        this(str, true);
    }

    public SuffixArray(String str, boolean toBuildRMQ) {
        this(stringToIntArray(str), toBuildRMQ);
    }

    public SuffixArray(int[] nums) {
        this(nums, true);
    }

    public SuffixArray(int[] nums, boolean toBuildRMQ) {
        this.s = nums;
        this.n = nums.length;
        this.SA = new int[n];
        this.rank = new int[n];
        suffixSort(s);
        if (toBuildRMQ) {
            this.height = new int[n];
            this.d = new int[log2(n) + 1][n];
            this.lg = new int[n + 1];
            buildHeight();
            buildRMQ();
        }
    }

    // height[i] = LCP(s[SA[i]], s[SA[i - 1]]).
    // ?? s[n] = 0 or s[n] = -INF.
    private void buildHeight() {
        int i, j, h;
        height[0] = 0;
        for (i = 0; i < n; i++)
            rank[SA[i]] = i;
        for (h = i = 0; i < n; i++)
            if (rank[i] > 0) {
                j = SA[rank[i] - 1];
                while (i + h < n && j + h < n && s[i + h] == s[j + h]) ++h;
                height[rank[i]] = h;
                if (h > 0) --h;
            }
    }

    // LCP(i, j) = LCP(s[SA[i]], s[SA[j]]) = min{height[k] | i + 1 <= k <= j}.
    private void buildRMQ() {
        int i, j, k;
        for (i = 0; i < n; i++)
            d[0][i] = height[i];
        for (j = 1; (1 << j) <= n; j++)
            for (i = 0; i + (1 << j) <= n; i++)
                d[j][i] = Math.min(d[j - 1][i], d[j - 1][i + (1 << (j - 1))]);
        for (lg[0] = k = 0, i = 1; i <= n; lg[i++] = k - 1)
            while ((1 << k) <= i) k++;
    }

    // LCP(i, j) = LCP(s[SA[i]], s[SA[j]]) = min{height[k] | i + 1 <= k <= j}.
    public int LCP(int i, int j) {
        if (i == j) return n - SA[i];
        if (i > j) {
            int t = i;
            i = j;
            j = t;
        }
        int k = lg[j - (++i) + 1];
        return Math.min(d[k][i], d[k][j - (1 << k) + 1]);
    }

    // LCPIndex(i, j) = LCP(s[i], s[j])
    public int LCPIndex(int i, int j) {
        return LCP(rank[i], rank[j]);
    }

    private void sortIndex(int[] nums, int start, int end) {
        if (start >= end) return;
        int pos = start + ((end - start) >> 1);
        int pivot = nums[pos];

        int i = start, j = end;
        nums[pos] = nums[end];
        while (i < j) {
            while (i < j && s[nums[i]] < s[pivot]) i++;
            if (i < j) nums[j--] = nums[i];

            while (i < j && s[pivot] < s[nums[j]]) j--;
            if (i < j) nums[i++] = nums[j];
        }
        nums[i] = pivot; // index i is the final position for pivot.
        sortIndex(nums, start, i - 1);
        sortIndex(nums, i + 1, end);
    }

    private void suffixSort(int[] s) {
        int[] count = new int[n], t;
        int[] nSA = new int[n];
        int[] nRank = new int[n];
//        Integer[] tempArray = new Integer[n];
//        for (int x = 0; x < n; x++) tempArray[x] = x;
//        Arrays.sort(tempArray, (a, b) -> Integer.compare(s[a], s[b]));
//        for (int x = 0; x < n; x++) SA[x] = tempArray[x];
        int i, k;
        int minV = s[0], maxV = s[0];
        for (i = 1; i < n; i++) {
            minV = Math.min(minV, s[i]);
            maxV = Math.max(maxV, s[i]);
        }
        if ((long) maxV - minV + 1 <= (long) n) { // radix sort if alphabet size is small
            for (i = 0; i < n; i++) count[s[i] - minV]++;
            for (i = minV + 1; i <= maxV; i++) count[i - minV] += count[i - minV - 1];
            for (i = n - 1; i >= 0; i--) SA[--count[s[i] - minV]] = i;
        } else {
            for (i = 0; i < n; i++) SA[i] = i;
            sortIndex(SA, 0, n - 1);
        }
        for (rank[SA[0]] = 0, i = 1; i < n; i++) {
            rank[SA[i]] = (s[SA[i]] != s[SA[i - 1]]) ? rank[SA[i - 1]] + 1 : rank[SA[i - 1]];
        }
        for (k = 1; k < n && rank[SA[n - 1]] < n - 1; k <<= 1) {
            for (i = 0; i < n; i++) count[rank[SA[i]]] = i + 1;
            for (i = n - 1; i >= 0; i--) if (SA[i] >= k) nSA[--count[rank[SA[i] - k]]] = SA[i] - k;
            for (i = n - k; i < n; i++) nSA[--count[rank[i]]] = i;
            t = SA;
            SA = nSA;
            nSA = t;
            for (nRank[SA[0]] = 0, i = 1; i < n; i++) {
                nRank[SA[i]] = (SA[i] + k >= n || SA[i - 1] + k >= n || rank[SA[i]] != rank[SA[i - 1]] || rank[SA[i] + k] != rank[SA[i - 1] + k]) ? nRank[SA[i - 1]] + 1 : nRank[SA[i - 1]];
            }
            t = rank;
            rank = nRank;
            nRank = t;
        }
    }
}

class Combination {
    long[] factorial;
    long[] inverseFactorial;
    long MOD;

    // O(maxSize) for preprocessing
    // MOD must be prime
    public Combination(int maxSize, long MOD) {
        factorial = new long[maxSize + 1];
        inverseFactorial = new long[maxSize + 1];
        factorial[0] = 1;
        inverseFactorial[0] = 1;
        this.MOD = MOD;
        for (int i = 1; i <= maxSize; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
            inverseFactorial[i] = inverse(factorial[i]);
        }
    }

    // x, y are long[1]
    // ax + by = gcd(a, b)
    private long extGcd(long a, long b, long[] x, long[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        } else {
            long r = extGcd(b, a % b, y, x);
            y[0] -= x[0] * (a / b);
            return r;
        }
    }

    // (a * x) % MOD = 1
    // x is the inverse element
    public long inverse(long a) {
        long[] x = new long[1], y = new long[1];
        extGcd(a, MOD, x, y);
        return (x[0] % MOD + MOD) % MOD;
    }

    // m >= n
    // O(1)
    public long P(int m, int n) {
        if (m == 0 || n == 0) return 1;
        else if (m == n) return factorial[m];
            //return factorial[m] * inverse(factorial[m - n]) % MOD;
        else return factorial[m] * inverseFactorial[m - n] % MOD;
    }

    // m >= n
    // O(1)
    public long C(int m, int n) {
        if (m == 0 || n == 0 || m == n) return 1;
            //return factorial[m] * inverse(factorial[m - n]) % MOD * inverse(factorial[n]) % MOD;
        else return factorial[m] * inverseFactorial[m - n] % MOD * inverseFactorial[n] % MOD;
    }
}

class CombinationNoMod {

    long[][] C;
    long[][] P;

    public CombinationNoMod(int maxSize) {
        C = new long[maxSize + 1][maxSize + 1];
        P = new long[maxSize + 1][maxSize + 1];
        int m = C.length;
        int n = C[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i && j < n; j++) {
                if (j == 0 || i == j) C[i][j] = 1;
                else C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i && j < n; j++) {
                if (i == 0 || j == 0) P[i][j] = 1;
                else P[i][j] = P[i][j - 1] * (i - j + 1);
            }
        }
    }

    // m >= n. O(1)
    public long P(int m, int n) {
        if (m == 0 || n == 0) return 1;
        return P[m][n];
    }

    // m >= n. O(1)
    public long C(int m, int n) {
        if (m == 0 || n == 0 || m == n) return 1;
        return C[m][n];
    }
}

class Fraction {
    long n, d; // n / d (d != 0)

    public Fraction(long n, long d) {
        if (d == 0) {
            this.n = Long.MAX_VALUE;
            this.d = 1;
            return;
        }
        long g = gcd(Math.abs(n), Math.abs(d));
        this.n = n / g;
        this.d = d / g;
        if (this.d < 0) {
            this.d = -this.d;
            this.n = -this.n;
        }
    }

    public void add(Fraction other) {
        long numerator = this.n * other.d + this.d * other.n;
        long denominator = this.d * other.d;
        long g = gcd(Math.abs(numerator), Math.abs(denominator));
        this.n = numerator / g;
        this.d = denominator / g;
        if (this.d < 0) {
            this.d = -this.d;
            this.n = -this.n;
        }
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Fraction) {
            Fraction anotherObject = (Fraction) anObject;
            return n == anotherObject.n && d == anotherObject.d;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) (n * 31 + d);
    }

    private long gcd(long x, long y) {
        return x != 0 ? gcd(y % x, x) : y;
    }
}

class UnionFind {
    int[] parent;
    int[] sz;

    // from 0 ... n - 1
    public UnionFind(int n) {
        parent = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    public void clear() {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int x) {
        //return parent[x] == x ? x : (parent[x] = find(parent[x]));
        if (parent[x] == x) return x;
        int px = x;
        while (px != parent[px]) px = parent[px];
        while (x != px) {
            int next = parent[x];
            parent[x] = px;
            x = next;
        }
        return px;
    }

    // px is the final parent
    public boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return false;
        parent[py] = px;
        sz[px] += sz[py];
        return true;
    }

    public int size(int x) {
        return sz[find(x)];
    }

    public UnionFind clone() {
        UnionFind cloned = new UnionFind(parent.length);
        for (int i = 0; i < parent.length; i++) {
            cloned.parent[i] = parent[i];
            cloned.sz[i] = sz[i];
        }
        return cloned;
    }
}

class Trie {
    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isWord = false;
        long count = 0L;
    }

    TrieNode root = new TrieNode();

    public void add(String word) {
        add(word, 1L);
    }

    public void remove(String word) {
        add(word, -1L);
    }

    private void add(String word, long count) {
        TrieNode cur = root;
        cur.count += count;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (cur.next[c] == null) cur.next[c] = new TrieNode();
            cur = cur.next[c];
            cur.count += count;
        }
        cur.isWord = true;
    }

    public boolean contains(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (cur.next[c] == null || cur.next[c].count <= 0L) return false;
            cur = cur.next[c];
            //if (cur.isWord) return true;
        }
        return cur.isWord;
    }
}

// Tested by Leetcode 1707 and Leetcode 1803
class TrieBinary {
    static class TrieNode {
        TrieNode[] next = new TrieNode[2];
        long count;
    }

    private TrieNode root;
    private int numOfBits;

    public TrieBinary(int numOfBits) { // normally 31 for int and 63 for long
        this.numOfBits = numOfBits;
        this.root = new TrieNode();
    }

    public void add(long v) {
        add(v, 1L);
    }

    public void remove(long v) { // Tested by Leetcode 1938
        add(v, -1L);
    }

    public void add(long v, long count) {
        TrieNode cur = root;
        root.count += count;
        for (int i = numOfBits - 1; i >= 0; i--) {
            int b = ((v >>> i) & 1) == 0 ? 0 : 1;
            if (cur.next[b] == null) cur.next[b] = new TrieNode();
            cur = cur.next[b];
            cur.count += count;
        }
    }

    // Tested by Leetcode 1707
    public long xorMax(long v) {
        TrieNode cur = root;
        long key = 0;
        for (int i = numOfBits - 1; i >= 0; i--) {
            int b = ((v >>> i) & 1) == 0 ? 0 : 1;
            if (cur.next[1 - b] != null && cur.next[1 - b].count > 0) {
                cur = cur.next[1 - b];
                key |= (1L - b) << i;
            } else {
                cur = cur.next[b];
                key |= (long) b << i;
            }
        }
        return key ^ v;
    }

    public boolean contains(long v) {
        TrieNode cur = root;
        for (int i = numOfBits - 1; i >= 0; i--) {
            int b = ((v >>> i) & 1) == 0 ? 0 : 1;
            if (cur.next[b] == null || cur.next[b].count <= 0) return false;
            cur = cur.next[b];
        }
        return true;
    }

    // Tested by Leetcode 1803
    // count(x ^ v <= limit)
    public long xorLessOrEqualCount(long v, long limit) {
        if (limit < 0) return 0;
        TrieNode cur = root;
        long ans = 0;
        for (int i = numOfBits - 1; i >= 0; i--) {
            int bitLimit = ((limit >>> i) & 1) == 0 ? 0 : 1;
            int bitV = ((v >>> i) & 1) == 0 ? 0 : 1;
            if (bitLimit == 1) {
                ans += (cur.next[bitV] != null ? cur.next[bitV].count : 0);
            }
            cur = cur.next[bitV ^ bitLimit];
            if (cur == null) break;
            if (i == 0) ans += cur.count;
        }
        return ans;
    }
}

// Lazy execution example is in https://leetcode.com/submissions/detail/1034182539/
// Refer to AlgorithmCompilation.java

// [Template] Classic Segment Tree (Range Sum), used for Template
class ClassicSegmentTree { // For Range Sum
    class TreeNode {
        TreeNode left, right;
        long sum;
    }

    private final TreeNode root;
    private final int L, R;

    public ClassicSegmentTree(int[] input) {
        L = 0;
        R = input.length - 1;
        root = new TreeNode();
        build(root, L, R, input);
    }

    private void build(TreeNode root, int l, int r, int[] input) {
        if (l == r) {
            root.sum = input[l];
            return;
        }
        int m = (l + r) >> 1;
        root.left = new TreeNode();
        root.right = new TreeNode();
        build(root.left, l, m, input);
        build(root.right, m + 1, r, input);
        root.sum = root.left.sum + root.right.sum;
    }

    public void set(int index, int value) {
        set(root, L, R, index, value);
    }

    private void set(TreeNode root, int L, int R, int index, int value) {
        if (index < L || R < index) return;
        if (L == R) {
            root.sum = value;
            return;
        }
        int M = L + ((R - L) >> 1);
        if (index <= M) {
            set(root.left, L, M, index, value);
        } else {
            set(root.right, M + 1, R, index, value);
        }
        root.sum = root.left.sum + root.right.sum;
    }

    // Sum[queryL...queryR] inclusive
    public long query(int queryL, int queryR) {
        return query(root, L, R, queryL, queryR);
    }

    // [queryL, queryR] is range of query, [L, R] are range of TreeNode.
    private long query(TreeNode root, int L, int R, int queryL, int queryR) {
        if (root == null || queryL > R || queryR < L) return 0L;
        if (queryL <= L && R <= queryR) return root.sum;
        int M = L + ((R - L) >> 1);
        return query(root.left, L, M, queryL, queryR) + query(root.right, M + 1, R, queryL, queryR);
    }
}

// Tested by Leetcode 307
class DynamicSegmentTreeRangeSum {
    class TreeNode {
        TreeNode left, right;
        long sum;
    }

    private final TreeNode root;
    private final long L, R;

    public DynamicSegmentTreeRangeSum(long l, long r) {
        L = l;
        R = r;
        root = new TreeNode();
    }

    public long get(long index) {
        long l = L, r = R;
        TreeNode cur = root;
        while (l < r && cur != null) {
            long m = (l + r) >> 1;
            if (index <= m) {
                cur = cur.left;
                r = m;
            } else {
                cur = cur.right;
                l = m + 1;
            }
        }
        return sum(cur);
    }

    public void add(long index, long value) {
        set(index, get(index) + value);
    }

    private long sum(TreeNode node) {
        return node == null ? 0L : node.sum;
    }

    public void set(long index, long value) {
        set(root, L, R, index, value);
    }

    private void set(TreeNode root, long L, long R, long index, long value) {
        if (index < L || R < index) return;
        if (L == R) {
            root.sum = value;
            return;
        }
        long M = L + ((R - L) >> 1);
        if (index <= M) {
            if (root.left == null) root.left = new TreeNode();
            set(root.left, L, M, index, value);
        } else {
            if (root.right == null) root.right = new TreeNode();
            set(root.right, M + 1, R, index, value);
        }
        root.sum = sum(root.left) + sum(root.right);
    }

    // Sum[queryL...queryR] inclusive
    public long query(long queryL, long queryR) {
        return query(root, L, R, queryL, queryR);
    }

    // [queryL, queryR] is range of query, [L, R] are range of TreeNode.
    private long query(TreeNode root, long L, long R, long queryL, long queryR) {
        if (root == null || queryL > R || queryR < L) return 0L;
        if (queryL <= L && R <= queryR) return root.sum;
        long M = L + ((R - L) >> 1);
        return query(root.left, L, M, queryL, queryR) + query(root.right, M + 1, R, queryL, queryR);
    }
}

// Tested by Leetcode 239, 2926
class DynamicSegmentTreeRangeMax {
    class TreeNode {
        TreeNode left, right;
        long max = Long.MIN_VALUE;
    }

    private final TreeNode root;
    private final long L, R;

    public DynamicSegmentTreeRangeMax(long l, long r) {  // [l, r] inclusive
        L = l;
        R = r;
        root = new TreeNode();
    }

    public long get(long index) {
        long l = L, r = R;
        TreeNode cur = root;
        while (l < r && cur != null) {
            long m = (l + r) >> 1;
            if (index <= m) {
                cur = cur.left;
                r = m;
            } else {
                cur = cur.right;
                l = m + 1;
            }
        }
        return max(cur);
    }

    private long max(TreeNode node) {
        return node == null ? Long.MIN_VALUE : node.max;
    }

    public void set(long index, long value) {
        set(root, L, R, index, value);
    }

    private void set(TreeNode root, long L, long R, long index, long value) {
        if (index < L || R < index) return;
        if (L == R) {
            root.max = value;
            return;
        }

        long M = L + ((R - L) >> 1);
        if (index <= M) {
            if (root.left == null) root.left = new TreeNode();
            set(root.left, L, M, index, value);
        } else {
            if (root.right == null) root.right = new TreeNode();
            set(root.right, M + 1, R, index, value);
        }
        root.max = Math.max(max(root.left), max(root.right));
    }

    // Max[queryL...queryR] inclusive
    public long query(long queryL, long queryR) {
        return query(root, L, R, queryL, queryR);
    }

    // [queryL, queryR] is range of query, [L, R] are range of TreeNode.
    private long query(TreeNode root, long L, long R, long queryL, long queryR) {
        if (root == null || queryL > R || queryR < L) return Long.MIN_VALUE;
        if (queryL <= L && R <= queryR) return root.max;
        long M = L + ((R - L) >> 1);
        return Math.max(query(root.left, L, M, queryL, queryR), query(root.right, M + 1, R, queryL, queryR));
    }
}

class DynamicSegmentTreeRangeMin {
    class TreeNode {
        TreeNode left, right;
        long min = Long.MAX_VALUE;
    }

    private final TreeNode root;
    private final long L, R;

    public DynamicSegmentTreeRangeMin(long l, long r) {  // [l, r] inclusive
        L = l;
        R = r;
        root = new TreeNode();
    }

    public long get(long index) {
        long l = L, r = R;
        TreeNode cur = root;
        while (l < r && cur != null) {
            long m = (l + r) >> 1;
            if (index <= m) {
                cur = cur.left;
                r = m;
            } else {
                cur = cur.right;
                l = m + 1;
            }
        }
        return min(cur);
    }

    private long min(TreeNode node) {
        return node == null ? Long.MAX_VALUE : node.min;
    }

    public void set(long index, long value) {
        set(root, L, R, index, value);
    }

    private void set(TreeNode root, long L, long R, long index, long value) {
        if (index < L || R < index) return;
        if (L == R) {
            root.min = value;
            return;
        }

        long M = L + ((R - L) >> 1);
        if (index <= M) {
            if (root.left == null) root.left = new TreeNode();
            set(root.left, L, M, index, value);
        } else {
            if (root.right == null) root.right = new TreeNode();
            set(root.right, M + 1, R, index, value);
        }
        root.min = Math.min(min(root.left), min(root.right));
    }

    // Max[queryL...queryR] inclusive
    public long query(long queryL, long queryR) {
        return query(root, L, R, queryL, queryR);
    }

    // [queryL, queryR] is range of query, [L, R] are range of TreeNode.
    private long query(TreeNode root, long L, long R, long queryL, long queryR) {
        if (root == null || queryL > R || queryR < L) return Long.MAX_VALUE;
        if (queryL <= L && R <= queryR) return root.min;
        long M = L + ((R - L) >> 1);
        return Math.min(query(root.left, L, M, queryL, queryR), query(root.right, M + 1, R, queryL, queryR));
    }
}

class BinaryIndexTree {
    private long[] c;
    private int n;

    // NOTE: index is from 1 to n. (NOT 0 ... n - 1)
    public BinaryIndexTree(int n) {
        this.n = n;
        this.c = new long[n + 1];
    }

    // Add value to position k, k is from 1 to n.
    public void add(int k, long value) {
        while (k <= n) {
            c[k] += value;
            k += (k & (k ^ (k - 1)));
        }
    }

    // k is from 1 to n.
    public long getSum(int k) {
        long sum = 0;
        while (k > 0) {
            sum += c[k];
            k -= (k & (k ^ (k - 1)));
        }
        return sum;
    }
}

class RangeSum {
    BinaryIndexTree tree;
    int start;

    // [low, high] inclusive
    public RangeSum(int low, int high) {
        start = low;
        tree = new BinaryIndexTree(high - low + 1);
    }

    public void add(int index, long value) {
        tree.add(index - start + 1, value);
    }

    public long get(int index) {
        return tree.getSum(index - start + 1) - tree.getSum(index - start);
    }

    public void set(int index, long value) {
        tree.add(index - start + 1, value - get(index));
    }

    // [l, r] inclusive
    public long getSum(int l, int r) {
        return tree.getSum(r - start + 1) - tree.getSum(l - start);
    }
}

class RangeMax {
    int start;
    int n;
    long[] t;

    // [low, high] inclusive
    public RangeMax(int low, int high) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, Long.MIN_VALUE);
    }

    public RangeMax(int low, int high, long defaultValue) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, defaultValue);
    }

    long get(int index) {
        return t[index - start + n];
    }

    void set(int index, long value) {
        index -= start;
        for (t[index += n] = value; (index >>= 1) > 0; ) {
            t[index] = Math.max(t[index << 1], t[index << 1 | 1]);
        }
    }

    // [l, r] inclusive
    long getMax(int l, int r) {
        l -= start;
        r = (r + 1 - start); // make it as [l, r)
        long resl = Long.MIN_VALUE, resr = Long.MIN_VALUE;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) resl = Math.max(resl, t[l++]);
            if ((r & 1) != 0) resr = Math.max(t[--r], resr);
        }
        return Math.max(resl, resr);
    }
}

class RangeMin {
    int start;
    int n;
    long[] t;

    // [low, high] inclusive
    public RangeMin(int low, int high) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, Long.MAX_VALUE);
    }

    public RangeMin(int low, int high, long defaultValue) {
        start = low;
        n = high - low + 1;
        t = new long[n * 2 + 2];
        Arrays.fill(t, defaultValue);
    }

    long get(int index) {
        return t[index - start + n];
    }

    void set(int index, long value) {
        index -= start;
        for (t[index += n] = value; (index >>= 1) > 0; ) {
            t[index] = Math.min(t[index << 1], t[index << 1 | 1]);
        }
    }

    // [l, r] inclusive
    long getMin(int l, int r) {
        l -= start;
        r = (r + 1 - start); // make it as [l, r)
        long resl = Long.MAX_VALUE, resr = Long.MAX_VALUE;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) resl = Math.min(resl, t[l++]);
            if ((r & 1) != 0) resr = Math.min(t[--r], resr);
        }
        return Math.min(resl, resr);
    }
}

// Including topological sort.
class GraphIntegerFaster {
    public List<Integer>[] adj;

    public GraphIntegerFaster(int n) {
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
    }

    // directed edge x -> y
    public void link(int x, int y) {
        adj[x].add(y);
    }

    public List<Integer> getChildren(int x) {
        return adj[x];
    }

    private static final int TEMPORARY = 1;
    private static final int PERMANENT = 2;

    // x -> y means x should be processed before y
    // The input is a directed graph.
    // 1. return null, means there is a cycle inside the graph, or the input is
    // invalid.
    // 2. return List<Node> as the result.
    public List<Integer> topologicalSort() {
        //Map<E, Integer> vst = new HashMap<>();
        int n = adj.length;
        int[] vst = new int[n];
        List<Integer> result = new ArrayList<>();
        for (int x = 0; x < n; x++) {
            if (!topologicalSort(x, vst, result)) {
                return null;
            }
        }
        Collections.reverse(result);
        return result;
    }

    private boolean topologicalSort(int root, int[] vst, List<Integer> result) {
        if (vst[root] == PERMANENT) {
            return true;
        }
        if (vst[root] == TEMPORARY) { // found cycle
            return false;
        }

        vst[root] = TEMPORARY;
        for (int child : getChildren(root)) {
            if (!topologicalSort(child, vst, result)) {
                return false;
            }
        }
        result.add(root);
        vst[root] = PERMANENT;
        return true;
    }
}

class DijkstraLongFaster {
    public static class Edge {
        int to;
        long w;

        public Edge(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }

    public static final long INF = Long.MAX_VALUE / 4;
    public List<Edge>[] adj;

    public DijkstraLongFaster(int n) {
        this.adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
    }

    public void link(int x, int y, long w) {
        adj[x].add(new Edge(y, w));
    }

    public long[] shortestPath(int src) {
        int n = adj.length;
        long[] dist = new long[n];
        boolean[] vst = new boolean[n];
        Arrays.fill(dist, INF);

        PriorityQueue<Edge> q = new PriorityQueue<>((a, b) -> Long.compare(a.w, b.w));
        q.add(new Edge(src, 0));
        dist[src] = 0;

        while (!q.isEmpty()) {
            Edge cur = q.poll();
            if (vst[cur.to]) continue;
            int u = cur.to;
            long w = cur.w;
            vst[u] = true;

            for (Edge e : adj[u]) {
                if (!vst[e.to] && dist[e.to] > w + e.w) {
                    dist[e.to] = w + e.w;
                    q.add(new Edge(e.to, w + e.w));
                }
            }
        }
        return dist;
    }

    // NOTE: w of each edge must be the same.
    public long[] bfs(int src) {
        int n = adj.length;
        long[] dist = new long[n];
        boolean[] vst = new boolean[n];
        Arrays.fill(dist, INF);

        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        dist[src] = 0;
        vst[src] = true;

        while (!q.isEmpty()) {
            int u = q.poll();
            long w = dist[u];
            for (Edge e : adj[u]) {
                if (!vst[e.to]) {
                    dist[e.to] = w + e.w;
                    vst[e.to] = true;
                    q.add(e.to);
                }
            }
        }
        return dist;
    }
}

class TreapSet<E> {
    private class Node {
        E key;
        int priority, count, total;
        Node left, right, pnt;

        public Node(E key, int priority, Node pnt) {
            this.key = key;
            this.priority = priority;
            this.pnt = pnt;
            this.count = 1;
            this.total = 1;
        }
    }

    private static final Random RANDOM = new Random();
    private final Comparator<? super E> comparator;
    private Node root;

    public TreapSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public int size() {
        return root == null ? 0 : root.total;
    }

    private int leftRank(Node p) {
        return p.left != null ? p.left.total : 0;
    }

    private int rightRank(Node p) {
        return p.right != null ? p.right.total : 0;
    }

    private void rotateLeft(Node x) {
        Node y = x.right;
        if ((x.right = y.left) != null) y.left.pnt = x;
        y.pnt = x.pnt;
        if (x == root) root = y;
        else if (x == x.pnt.left) x.pnt.left = y;
        else x.pnt.right = y;
        y.left = x;
        x.pnt = y;
        x.total = leftRank(x) + rightRank(x) + x.count;
        y.total = leftRank(y) + rightRank(y) + y.count;
    }

    private void rotateRight(Node x) {
        Node y = x.left;
        if ((x.left = y.right) != null) y.right.pnt = x;
        y.pnt = x.pnt;
        if (x == root) root = y;
        else if (x == x.pnt.right) x.pnt.right = y;
        else x.pnt.left = y;
        y.right = x;
        x.pnt = y;
        x.total = leftRank(x) + rightRank(x) + x.count;
        y.total = leftRank(y) + rightRank(y) + y.count;
    }

    public void add(E key) {
        if (root == null) {
            root = new Node(key, RANDOM.nextInt(), null);
            return;
        }
        Node x = root, p = null;
        while (x != null) {
            ++((p = x).total);
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                ++(x.count);
                return;
            }
        }

        x = new Node(key, RANDOM.nextInt(), p);
        if (comparator.compare(key, p.key) < 0) p.left = x;
        else p.right = x;
        while ((p = x.pnt) != null && p.priority < x.priority) {
            if (p.left == x) rotateRight(p);
            else rotateLeft(p);
        }
    }

    public void remove(E key) {
        Node x = root, p = null;
        while (x != null) {
            --(x.total);
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                if (--(x.count) > 0) return;
                break;
            }
        }
        if (x == null) return;
        while (x.left != null || x.right != null) {
            if (x.left == null || (x.right != null && x.right.priority > x.left.priority)) {
                rotateLeft(x);
            } else {
                rotateRight(x);
            }
        }
        if ((p = x.pnt) != null) {
            if (p.left == x) p.left = null;
            else p.right = null;
        } else {
            root = null;
        }
        // delete x;
    }

    // number of elements smaller than key
    public int lowerCount(E key) {
        int sum = 0;
        Node x = root;
        while (x != null) {
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) {
                sum += leftRank(x) + x.count;
                x = x.right;
            } else {
                sum += leftRank(x);
                break;
            }
        }
        return sum;
    }

    // number of elements larger than key
    public int higherCount(E key) {
        //return size() - lowerCount(key) - count(key);
        int sum = 0;
        Node x = root;
        while (x != null) {
            int cmp = comparator.compare(key, x.key);
            if (cmp > 0) x = x.right;
            else if (cmp < 0) {
                sum += rightRank(x) + x.count;
                x = x.left;
            } else {
                sum += rightRank(x);
                break;
            }
        }
        return sum;
    }

    public int index(E key) {
        return lowerCount(key);
    }

    public E get(int index) // index = 0 ... size - 1
    {
        Node p = root;
        ++index;
        while (true) {
            int t = leftRank(p);
            if (index <= t) p = p.left;
            else {
                if ((index -= t + p.count) <= 0) break;
                p = p.right;
            }
        }
        return p.key;
    }

    public int count(E key) {
        Node x = root;
        while (x != null) {
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) {
                x = x.right;
            } else {
                return x.count;
            }
        }
        return 0;
    }

    public E first() {
        if (root == null) return null;
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    public E last() {
        if (root == null) return null;
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    public boolean contains(E key) {
        return count(key) > 0;
    }

    public E ceiling(E key) {
        int id = lowerCount(key);
        return id >= size() ? null : get(id);
    }

    public E floor(E key) {
        int id = size() - higherCount(key) - 1;
        return id < 0 ? null : get(id);
    }

    public E lower(E key) {
        int id = lowerCount(key) - 1;
        return id < 0 ? null : get(id);
    }

    public E higher(E key) {
        int id = size() - higherCount(key);
        return id >= size() ? null : get(id);
    }

    // return the first index whose value >= target
    // if this value doesn't exist, return index = size()
    public int lowerBound(E target) {
        return lowerCount(target);
    }

    // return the first index whose value > target
    // if this value doesn't exist, return index = size()
    public int upperBound(E target) {
        return size() - higherCount(target);
    }

    public List<E> keys() {
        List<E> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(Node x, List<E> list) {
        if (x == null) return;
        inorder(x.left, list);
        list.add(x.key);
        inorder(x.right, list);
    }
}

class ArraySum {
    long[] sum;
    int n;

    // nums index starts from 0 to nums.length - 1;
    public ArraySum(int[] nums) {
        n = nums.length;
        sum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public ArraySum(long[] nums) {
        n = nums.length;
        sum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    // [l, r], index starts from 0
    public long getSum(int l, int r) {
        // error handling
        if (l > r || !(l >= 0 && l < n && r >= 0 && r < n)) return 0L;
        return sum[r + 1] - sum[l];
    }
}

class TwoDArraySum {
    int[][] sum;
    int m, n;

    // nums index starts from 0
    public TwoDArraySum(final int[][] nums) {
        m = nums.length;
        n = nums[0].length;
        sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + nums[i - 1][j - 1];
            }
        }
    }

    // [(x1, y1), (x2, y2)], index starts from 0
    // x1 <= x2 and y1 <= y2
    public int getSum(int x1, int y1, int x2, int y2) {
        if (!(x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && x2 >= 0 && x2 < m && y2 >= 0 && y2 < n) || x1 > x2 || y1 > y2)
            return 0;
        return sum[x2 + 1][y2 + 1] - sum[x2 + 1][y1] - sum[x1][y2 + 1] + sum[x1][y1];
    }
}

class ArrayUtils {
    static void fill(int[] array, int defaultValue) {
        Arrays.fill(array, defaultValue);
    }

    static void fill(int[][] array, int defaultValue) {
        for (int[] a1 : array) {
            Arrays.fill(a1, defaultValue);
        }
    }

    static void fill(int[][][] array, int defaultValue) {
        for (int[][] a2 : array) {
            for (int[] a1 : a2) {
                Arrays.fill(a1, defaultValue);
            }
        }
    }

    static void fill(int[][][][] array, int defaultValue) {
        for (int[][][] a3 : array) {
            for (int[][] a2 : a3) {
                for (int[] a1 : a2) {
                    Arrays.fill(a1, defaultValue);
                }
            }
        }
    }

    static void fill(int[][][][][] array, int defaultValue) {
        for (int[][][][] a4 : array) {
            for (int[][][] a3 : a4) {
                for (int[][] a2 : a3) {
                    for (int[] a1 : a2) {
                        Arrays.fill(a1, defaultValue);
                    }
                }
            }
        }
    }

    static void fill(int[][][][][][] array, int defaultValue) {
        for (int[][][][][] a5 : array) {
            for (int[][][][] a4 : a5) {
                for (int[][][] a3 : a4) {
                    for (int[][] a2 : a3) {
                        for (int[] a1 : a2) {
                            Arrays.fill(a1, defaultValue);
                        }
                    }
                }
            }
        }
    }

    static void fill(long[] array, long defaultValue) {
        Arrays.fill(array, defaultValue);
    }

    static void fill(long[][] array, long defaultValue) {
        for (long[] a1 : array) {
            Arrays.fill(a1, defaultValue);
        }
    }

    static void fill(long[][][] array, long defaultValue) {
        for (long[][] a2 : array) {
            for (long[] a1 : a2) {
                Arrays.fill(a1, defaultValue);
            }
        }
    }

    static void fill(long[][][][] array, long defaultValue) {
        for (long[][][] a3 : array) {
            for (long[][] a2 : a3) {
                for (long[] a1 : a2) {
                    Arrays.fill(a1, defaultValue);
                }
            }
        }
    }

    static void fill(long[][][][][] array, long defaultValue) {
        for (long[][][][] a4 : array) {
            for (long[][][] a3 : a4) {
                for (long[][] a2 : a3) {
                    for (long[] a1 : a2) {
                        Arrays.fill(a1, defaultValue);
                    }
                }
            }
        }
    }

    static void fill(long[][][][][][] array, long defaultValue) {
        for (long[][][][][] a5 : array) {
            for (long[][][][] a4 : a5) {
                for (long[][][] a3 : a4) {
                    for (long[][] a2 : a3) {
                        for (long[] a1 : a2) {
                            Arrays.fill(a1, defaultValue);
                        }
                    }
                }
            }
        }
    }
}

class CharacterUtils {
    static boolean[] vowel = new boolean[128];

    static {
        vowel['a'] = vowel['e'] = vowel['i'] = vowel['o'] = vowel['u'] = true;
        vowel['A'] = vowel['E'] = vowel['I'] = vowel['O'] = vowel['U'] = true;
    }

    static boolean isVowel(char ch) {
        return vowel[ch];
    }

    static boolean isLetter(char ch) {
        return ((ch - 'a') >= 0 && (ch - 'a') < 26) ||
                ((ch - 'A') >= 0 && (ch - 'A') < 26);
    }

    static boolean isLowerCase(char ch) {
        return (ch - 'a') >= 0 && (ch - 'a') < 26;
    }

    static boolean isUpperCase(char ch) {
        return (ch - 'A') >= 0 && (ch - 'A') < 26;
    }

    static boolean isDigit(char ch) {
        return (ch - '0') >= 0 && (ch - '0') <= 9;
    }
}

class DoubleUtils {
    private static final double EPS = 1e-8;

    public static int compare(double a, double b) {
        if (a - b > EPS) return 1;
        else if (a - b < -EPS) return -1;
        else return 0;
    }

    public static boolean isInteger(double a) {
        return compare(floor(a), a) == 0;
    }

    public static double floor(double a) {
        return Math.floor(a + EPS);
    }

    public static double ceil(double a) {
        return Math.ceil(a - EPS);
    }
}

class IntegerUtils {
    static long[] power10 = new long[19]; // 10^i

    static {
        power10[0] = 1L;
        for (int i = 1; i < power10.length; i++) {
            power10[i] = power10[i - 1] * 10;
        }
    }

    // swap index i and j of integer (long) num. i == 0 means the lowest position.
    static long swapDigit(long num, int i, int j) {
        if (i == j) return num;
        long di = num / power10[i] % 10L;
        long dj = num / power10[j] % 10L;
        return num - di * power10[i] - dj * power10[j] + dj * power10[i] + di * power10[j];
    }
}

// Better to use this when length of string >= 2^11
class StringHash {
    static class SingleStringHash {
        long[] h;
        long[] pow;
        final long SEED;
        final long MOD;

        public SingleStringHash(String s, long SEED, long MOD) { // example: SEED = 131L;
            this.SEED = SEED;
            this.MOD = MOD;
            int n = s.length();
            h = new long[n + 1];
            pow = new long[n + 1];
            pow[0] = 1;
            for (int i = 1; i <= n; i++) pow[i] = (pow[i - 1] * SEED) % MOD;
            //h[i] = hash[s[0...i - 1]]
            h[0] = 0;
            for (int i = 1; i <= n; i++) {
                h[i] = (h[i - 1] * SEED + s.charAt(i - 1)) % MOD;
            }
        }

        // hash[s[l....r]]
        public long hash(int l, int r) {
            long ret = (h[r + 1] - h[l] * pow[r - l + 1]) % MOD;
            return ret >= 0 ? ret : ret + MOD;
        }
    }

    private SingleStringHash hash1, hash2;
    static final long SEED1 = 31L, SEED2 = 131L;
    static final long MOD = 1_000_000_007L;

    public StringHash(String s) {
        hash1 = new SingleStringHash(s, SEED1, MOD);
        hash2 = new SingleStringHash(s, SEED2, MOD);
    }

    public long hash(int l, int r) {
        return (hash1.hash(l, r) << 32) | (hash2.hash(l, r) << 32 >>> 32);
    }

    public static long hashCode(String s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.length(); i++) {
            h1 = (h1 * SEED1 + s.charAt(i)) % MOD;
            h2 = (h2 * SEED2 + s.charAt(i)) % MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }

    public static long hashCode(char[] s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.length; i++) {
            h1 = (h1 * SEED1 + s[i]) % MOD;
            h2 = (h2 * SEED2 + s[i]) % MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }
}

class IntegerArrayHash {
    static class SingleIntegerArrayHash {
        long[] h;
        long[] pow;
        final long SEED;
        final long MOD;

        public SingleIntegerArrayHash(int[] s, long SEED, long MOD) { // example: SEED = 131L;
            this.SEED = SEED;
            this.MOD = MOD;
            int n = s.length;
            h = new long[n + 1];
            pow = new long[n + 1];
            pow[0] = 1;
            for (int i = 1; i <= n; i++) pow[i] = (pow[i - 1] * SEED) % MOD;
            //h[i] = hash[s[0...i - 1]]
            h[0] = 0;
            for (int i = 1; i <= n; i++) {
                h[i] = (h[i - 1] * SEED + s[i - 1]) % MOD;
                if (h[i] < 0) h[i] += MOD;
            }
        }

        // hash[s[l....r]]
        public long hash(int l, int r) {
            long ret = (h[r + 1] - h[l] * pow[r - l + 1]) % MOD;
            return ret >= 0 ? ret : ret + MOD;
        }
    }

    private SingleIntegerArrayHash hash1, hash2;
    static final long SEED1 = 31L, SEED2 = 131L;
    static final long MOD = 1_000_000_007L; // or 1_000_000_123L

    public IntegerArrayHash(int[] s) {
        hash1 = new SingleIntegerArrayHash(s, SEED1, MOD);
        hash2 = new SingleIntegerArrayHash(s, SEED2, MOD);
    }

    public long hash(int l, int r) {
        return (hash1.hash(l, r) << 32) | (hash2.hash(l, r) << 32 >>> 32);
    }

    public static long hashCode(int[] s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.length; i++) {
            h1 = (h1 * SEED1 + s[i]) % MOD;
            if (h1 < 0) h1 += MOD;
            h2 = (h2 * SEED2 + s[i]) % MOD;
            if (h2 < 0) h2 += MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }

    public static long hashCode(List<Integer> s) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < s.size(); i++) {
            h1 = (h1 * SEED1 + s.get(i)) % MOD;
            if (h1 < 0) h1 += MOD;
            h2 = (h2 * SEED2 + s.get(i)) % MOD;
            if (h2 < 0) h2 += MOD;
        }
        return (h1 << 32) | (h2 << 32 >>> 32);
    }
}

class RMQ { // NOTE!!! query return index, not value
    // tested by LC 1438,239
    final long[] a; // input data array copy
    int[][] d;

    int[] log2;
    boolean useMax;

    private static long[] intArrayToLongArray(int[] nums) {
        long[] ret = new long[nums.length];
        for (int i = 0; i < nums.length; i++) ret[i] = nums[i];
        return ret;
    }

    // useMax == true means RMQMax
    public RMQ(int[] input, boolean useMax) {
        this(intArrayToLongArray(input), useMax);
    }

    public RMQ(long[] input, boolean useMax) {
        int n = input.length;
        this.a = input;
        this.log2 = Utils.preprocessLog2(n);
        this.useMax = useMax;
        this.d = new int[log2[n] + 1][n];
        for (int i = 0; i < n; i++) d[0][i] = i;
        for (int j = 1; (1 << j) <= n; j++)
            for (int i = 0; i + (1 << j) <= n; i++)
                if (useMax) {
                    d[j][i] = a[d[j - 1][i]] >= a[d[j - 1][i + (1 << (j - 1))]] ? d[j - 1][i] : d[j - 1][i + (1 << (j - 1))];
                } else {
                    d[j][i] = a[d[j - 1][i]] <= a[d[j - 1][i + (1 << (j - 1))]] ? d[j - 1][i] : d[j - 1][i + (1 << (j - 1))];
                }
    }

    // i <= j, and index starts from 0.
    public int query(int i, int j) { // NOTE!!! return index, not value
        int k = log2[j - i + 1];
        if (useMax) return a[d[k][i]] >= a[d[k][j - (1 << k) + 1]] ? d[k][i] : d[k][j - (1 << k) + 1];
        else return a[d[k][i]] <= a[d[k][j - (1 << k) + 1]] ? d[k][i] : d[k][j - (1 << k) + 1];
    }
}

class RMQValue { // NOTE!!! query return value, not index
    final long[] a; // input data array copy
    long[][] d;

    int[] log2;
    boolean useMax;

    private static long[] intArrayToLongArray(int[] nums) {
        long[] ret = new long[nums.length];
        for (int i = 0; i < nums.length; i++) ret[i] = nums[i];
        return ret;
    }

    // useMax == true means RMQMax
    public RMQValue(int[] input, boolean useMax) {
        this(intArrayToLongArray(input), useMax);
    }

    public RMQValue(long[] input, boolean useMax) {
        int n = input.length;
        this.a = input;
        this.log2 = Utils.preprocessLog2(n);
        this.useMax = useMax;
        this.d = new long[log2[n] + 1][n];
        for (int i = 0; i < n; i++) d[0][i] = a[i];
        for (int j = 1; (1 << j) <= n; j++)
            for (int i = 0; i + (1 << j) <= n; i++)
                if (useMax) {
                    d[j][i] = Math.max(d[j - 1][i], d[j - 1][i + (1 << (j - 1))]);
                } else {
                    d[j][i] = Math.min(d[j - 1][i], d[j - 1][i + (1 << (j - 1))]);
                }
    }

    // i <= j, and index starts from 0.
    public long query(int i, int j) {
        int k = log2[j - i + 1];
        if (useMax) return Math.max(d[k][i], d[k][j - (1 << k) + 1]);
        else return Math.min(d[k][i], d[k][j - (1 << k) + 1]);
    }
}

// Tested by 2827
// Similar: 1012, 2376, 902, 2719, 2801, 357, 1215, 1397
class DigitDP {
    static final boolean ENABLE_DEBUG_MODE = false; // TODO: set it to true if you want to print more debug info
    static final long UNSET = -1;
    // dp[isStart][hasLimit][pos] | [odd][even][r]
    // (isStart, hasLimit, pos) is generic, (odd, even, r) is use case specific
    long[][][][][][] dp;
    int K;

    public long digitDP(long num, int k) {
        String numString = String.valueOf(num); // TODO
        this.dp = new long[2][2][numString.length()][numString.length() + 1][numString.length() + 1][k];
        this.K = k;

        if (ENABLE_DEBUG_MODE) System.out.println("num = " + numString);
        ArrayUtils.fill(dp, UNSET);
        long ans = dfs(new StringBuilder(), numString, 1, 1, 0, 0, 0, 0);
        if (ENABLE_DEBUG_MODE) System.out.println("---------------------\n");
        return ans;
    }

    long dfs(StringBuilder curResult, String s, int isStart, int hasLimit, int pos, int odd, int even, int r) {
        if (pos == s.length()) {
            if (isStart == 1) return 0; // NOTE: special check for num == 0. TODO
            if (r == 0 && odd == even) { // TODO
                if (ENABLE_DEBUG_MODE) System.out.println("result = " + curResult);
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[isStart][hasLimit][pos][odd][even][r] != UNSET) return dp[isStart][hasLimit][pos][odd][even][r];

        int start = 0;
        int end = (hasLimit == 1) ? s.charAt(pos) - '0' : 9; // TODO: check if it is binary string or not

        long ans = 0;
        for (int digit = start; digit <= end; digit++) {
            if (isStart == 1 && digit == 0) { // NOTE: handle leading zeros
                // [0, 999..9]
                // [0， 10^(len(s) - pos - 1) - 1]
                ans += dfs(new StringBuilder(), s, 1, 0, pos + 1, 0, 0, 0);
                continue;
            }
            int newHasLimit = (hasLimit == 1 && digit == s.charAt(pos) - '0') ? 1 : 0;

            int newOdd = odd + (digit % 2); // TODO
            int newEven = even + (digit % 2 == 0 ? 1 : 0); // TODO
            int newR = (isStart == 1 ? digit % this.K : (r * 10 + digit) % this.K); // TODO;

            if (ENABLE_DEBUG_MODE) curResult.append(digit);
            ans += dfs(curResult, s, 0, newHasLimit, pos + 1, newOdd, newEven, newR);
            if (ENABLE_DEBUG_MODE) curResult.deleteCharAt(curResult.length() - 1);

        }
        return dp[isStart][hasLimit][pos][odd][even][r] = ans;
    }
}

class Printer {
    static boolean ENABLE_LOCAL_PRINT = false;

    static void println(String x) {
        if (!ENABLE_LOCAL_PRINT) return; // do nothing
        System.out.println(x);
    }

    static void printf(String format, Object... args) {
        if (!ENABLE_LOCAL_PRINT) return; // do nothing
        System.out.printf(format, args);
    }
}


public class Solution {
    private static final int INF = 1_000_000_001;
    private static final long MOD = 1_000_000_007L;

    private static final int UNSET = -131;
    // 4 neighbors
    //    private static final int[] DX = {-1, 0, 1, 0}; // up, right, down, left
    //    private static final int[] DY = {0, 1, 0, -1};

    // 8 neighbors
    //    private static final int[] DX = {-1, -1, 0, 1, 1,  1, 0,  -1};
    //    private static final int[] DY = {0,   1, 1, 1, 0, -1, -1, -1};

    // knight
    //    private static final int[] DX = {-2, -2, -1, 1, 2, 2, 1, -1};
    //    private static final int[] DY = {-1, 1, 2, 2, 1, -1, -2, -2};

    private static final double EPS = 1e-8;
    private static final long SEED = 31L;

    static class ACAutomation {
        private static class TrieNode {
            TrieNode[] next = new TrieNode[26];
            // suffix node that is the longest suffix of current node.
            TrieNode suffix = null;
            // source node that contains a word.
            TrieNode src = null;
            String word = null;
            int depth = 0;
        }

        private TrieNode root = new TrieNode();
        private TrieNode curSearchNode = root;

        // DO NOT insert empty word ("").
        public void insert(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (cur.next[c] == null) {
                    cur.next[c] = new TrieNode();
                }
                cur = cur.next[c];
                cur.depth = i + 1;
            }
            cur.src = cur;
            cur.word = word;
        }

        private TrieNode getSuffixChild(TrieNode x, int c) {
            while (true) {
                if (x.next[c] != null) return x.next[c];
                if (x == root) break;
                x = x.suffix;
            }
            return root;
        }

        // After inserting all the words into trie, call build() function
        // to build the AC Automation.
        public void build() {
            Deque<TrieNode> q = new ArrayDeque<>();
            q.add(root);

            while (!q.isEmpty()) {
                TrieNode cur = q.pollFirst();
                for (int i = 0; i < 26; i++) {
                    if (cur.next[i] == null) continue;
                    TrieNode node = cur.next[i];
                    if (cur == root) {
                        node.suffix = root;
                    } else {
                        node.suffix = getSuffixChild(cur.suffix, i);

                        if (node.src == null) {
                            node.src = node.suffix.src;
                        }
                    }
                    q.add(node);
                }
            }
        }

        // Return the longest word that matches the current suffix
        public String search(char ch) {
            curSearchNode = getSuffixChild(curSearchNode, ch - 'a');
            if (curSearchNode.src == null) return null;
            else return curSearchNode.src.word;
        }

        // Return the longest word that matches the current suffix
        public int searchAndGetLength(char ch) {
            curSearchNode = getSuffixChild(curSearchNode, ch - 'a');
            return curSearchNode.depth;
        }

        // Return all words in the dict that are contained in the current suffix.
        // If there is no word, return an empty List (not null).
        public List<String> searchAll(char ch) {
            curSearchNode = getSuffixChild(curSearchNode, ch - 'a');
            TrieNode cur = curSearchNode.src;
            List<String> ans = new ArrayList<>();
            while (cur != null) {
                ans.add(cur.word);
                cur = cur.suffix.src;
            }
            return ans;
        }

        // Example usage code from LC 1032
        static class StreamChecker {

            ACAutomation ac = new ACAutomation();

            public StreamChecker(String[] words) {
                for (int i = 0; i < words.length; i++) {
                    ac.insert(words[i]);
                }
                ac.build();
            }

            public boolean query(char letter) {
                String word = ac.search(letter);
                return word != null;
            }
        }
    }

    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        ACAutomation ac = new ACAutomation();
        for (int i = 0; i < words.length; i++) {
            ac.insert(words[i]);
        }
        ac.build();

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int len = ac.searchAndGetLength(target.charAt(i - 1));
            if (len == 0) return -1;
            dp[i] = dp[i - len] + 1;
        }
        return dp[n];
    }


    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Printer.ENABLE_LOCAL_PRINT = true;
        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */





        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */
        /* ------------------------------------------------------- */
        System.out.println((System.currentTimeMillis() - startTime) + "ms");
    }
}