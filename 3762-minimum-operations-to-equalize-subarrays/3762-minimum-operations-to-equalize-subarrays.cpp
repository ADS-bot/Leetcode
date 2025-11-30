struct PST {
    int M;
    vector<int> L, R, cnt;
    vector<long long> sum;
    int nodes;
    PST(int maxNodes) {
        L.assign(maxNodes, 0);
        R.assign(maxNodes, 0);
        cnt.assign(maxNodes, 0);
        sum.assign(maxNodes, 0);
        nodes = 0;
    }
    int new_node_from(int prev) {
        ++nodes;
        if ((int)L.size() <= nodes) {
            L.push_back(0);
            R.push_back(0);
            cnt.push_back(0);
            sum.push_back(0);
        }
        L[nodes] = L[prev];
        R[nodes] = R[prev];
        cnt[nodes] = cnt[prev];
        sum[nodes] = sum[prev];
        return nodes;
    }
    int update(int prevRoot, int l, int r, int pos, long long val) {
        int cur = new_node_from(prevRoot);
        cnt[cur] = cnt[prevRoot] + 1;
        sum[cur] = sum[prevRoot] + val;
        if (l == r) return cur;
        int mid = (l + r) >> 1;
        if (pos <= mid) {
            int leftChild = update(L[prevRoot], l, mid, pos, val);
            L[cur] = leftChild;
            R[cur] = R[prevRoot];
        } else {
            int rightChild = update(R[prevRoot], mid + 1, r, pos, val);
            R[cur] = rightChild;
            L[cur] = L[prevRoot];
        }
        return cur;
    }
    int kth(int rootR, int rootL, int l, int r, int k) {
        if (l == r) return l;
        int leftCount = cnt[L[rootR]] - cnt[L[rootL]];
        int mid = (l + r) >> 1;
        if (k <= leftCount) return kth(L[rootR], L[rootL], l, mid, k);
        else return kth(R[rootR], R[rootL], mid + 1, r, k - leftCount);
    }
    int countLE(int rootR, int rootL, int l, int r, int posIdx) {
        if (posIdx < l) return 0;
        if (r <= posIdx) return cnt[rootR] - cnt[rootL];
        int mid = (l + r) >> 1;
        int res = 0;
        res += countLE(L[rootR], L[rootL], l, mid, posIdx);
        res += countLE(R[rootR], R[rootL], mid + 1, r, posIdx);
        return res;
    }
    long long sumLE(int rootR, int rootL, int l, int r, int posIdx) {
        if (posIdx < l) return 0;
        if (r <= posIdx) return sum[rootR] - sum[rootL];
        int mid = (l + r) >> 1;
        long long res = 0;
        res += sumLE(L[rootR], L[rootL], l, mid, posIdx);
        res += sumLE(R[rootR], R[rootL], mid + 1, r, posIdx);
        return res;
    }
};

class Solution {
public:
    vector<long long> minOperations(vector<int>& nums, int k, vector<vector<int>>& queries) {
        int n = (int)nums.size();
        vector<int> res(n);
        for (int i = 0; i < n; ++i) res[i] = nums[i] % k;
        int LOG = 1;
        while ((1 << LOG) <= n) ++LOG;
        vector<vector<int>> stMin(LOG, vector<int>(n)), stMax(LOG, vector<int>(n));
        for (int i = 0; i < n; ++i) {
            stMin[0][i] = res[i];
            stMax[0][i] = res[i];
        }
        for (int j = 1; j < LOG; ++j) {
            int len = 1 << j;
            int half = len >> 1;
            for (int i = 0; i + len - 1 < n; ++i) {
                stMin[j][i] = min(stMin[j - 1][i], stMin[j - 1][i + half]);
                stMax[j][i] = max(stMax[j - 1][i], stMax[j - 1][i + half]);
            }
        }
        vector<int> lg2(n + 1);
        for (int i = 2; i <= n; ++i) lg2[i] = lg2[i >> 1] + 1;
        auto rangeMin = [&](int L, int R) {
            int j = lg2[R - L + 1];
            return min(stMin[j][L], stMin[j][R - (1 << j) + 1]);
        };
        auto rangeMax = [&](int L, int R) {
            int j = lg2[R - L + 1];
            return max(stMax[j][L], stMax[j][R - (1 << j) + 1]);
        };
        vector<long long> q(n);
        for (int i = 0; i < n; ++i) q[i] = nums[i] / (long long)k;
        vector<long long> vals = q;
        sort(vals.begin(), vals.end());
        vals.erase(unique(vals.begin(), vals.end()), vals.end());
        int M = (int)vals.size();
        vector<int> pos(n);
        for (int i = 0; i < n; ++i) {
            pos[i] = int(lower_bound(vals.begin(), vals.end(), q[i]) - vals.begin()) + 1;
        }
        int approxNodes = (n * 22) + 10;
        PST pst(approxNodes);
        vector<int> root(n + 1, 0);
        for (int i = 0; i < n; ++i) {
            root[i + 1] = pst.update(root[i], 1, M, pos[i], q[i]);
        }
        vector<long long> ans;
        ans.reserve(queries.size());
        for (auto& qr : queries) {
            int l = qr[0], r = qr[1];
            if (rangeMin(l, r) != rangeMax(l, r)) {
                ans.push_back(-1);
                continue;
            }
            int len = r - l + 1;
            int kthPos = (len + 1) / 2;
            int rootR = root[r + 1], rootL = root[l];
            int idx = pst.kth(rootR, rootL, 1, M, kthPos);
            long long medVal = vals[idx - 1];
            int countLE = pst.countLE(rootR, rootL, 1, M, idx);
            long long sumLE = pst.sumLE(rootR, rootL, 1, M, idx);
            int countLess = pst.countLE(rootR, rootL, 1, M, idx - 1);
            long long sumLess = pst.sumLE(rootR, rootL, 1, M, idx - 1);
            long long totalSum = pst.sumLE(rootR, rootL, 1, M, M);
            int totalCount = len;
            int countGreater = totalCount - countLE;
            long long sumGreater = totalSum - sumLE;
            long long ops = 0;
            ops += medVal * (long long)countLess - sumLess;
            ops += sumGreater - medVal * (long long)countGreater;
            ans.push_back(ops);
        }
        return ans;
    }
};