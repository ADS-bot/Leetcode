class Solution {
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int[][] A = new int[1][26], M = new int[26][26];
        for (char c : s.toCharArray()) {
            A[0][c - 'a']++;
        }
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 1; j <= nums.get(i); j++) {
                M[i][(i + j) % 26] = 1;
            }
        }
        int C[][] = modMult(A, modpow(M, t, 1000000007), 1000000007), count = 0;
        for (int[] i : C) {
            for (int j : i) {
                count = (count + j) % 1000000007;
            }
        }
        return count;
    }
   
    int[][] modMult(int[][] A, int[][] B, int m) {
        int[][] C = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    long prod = (long) A[i][k] * B[k][j];
                    C[i][j] = (int) ((C[i][j] + prod) % m);
                }
            }
        }
        return C;
    }
    int[][] modpow(int[][] A, int k, int m) {
        if (k == 0) {
            int[][] I = new int[A.length][A.length];
            for (int i = 0; i < A.length; i++) {
                I[i][i] = 1;
            }
            return I;
        } else if (k % 2 == 1) {
            return modMult(A, modpow(A, k - 1, m), m);
        } else {
            int[][] M = modpow(A, k / 2, m);
            return modMult(M, M, m);
        }
    }
}