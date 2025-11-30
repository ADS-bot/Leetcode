from typing import List
import bisect

class MergeSortTree:
    def __init__(self, arr: List[int], all_vals: List[int]):
        self.n = len(arr)
        self.all_vals = all_vals
        self.tree = [[] for _ in range(4 * self.n)]
        self.prefix = [[] for _ in range(4 * self.n)]
        self.build(1, 0, self.n - 1, arr)

    def build(self, node: int, start: int, end: int, arr: List[int]):
        if start == end:
            self.tree[node] = [arr[start]]
            self.prefix[node] = [0, arr[start]]
            return
        mid = (start + end) // 2
        self.build(2 * node, start, mid, arr)
        self.build(2 * node + 1, mid + 1, end, arr)
        left = self.tree[2 * node]
        right = self.tree[2 * node + 1]
        i = j = 0
        merged = []
        while i < len(left) and j < len(right):
            if left[i] <= right[j]:
                merged.append(left[i])
                i += 1
            else:
                merged.append(right[j])
                j += 1
        merged.extend(left[i:])
        merged.extend(right[j:])
        self.tree[node] = merged
        pre = [0]
        for x in merged:
            pre.append(pre[-1] + x)
        self.prefix[node] = pre

    def get_lists(self, l: int, r: int) -> List[tuple]:
        lists = []
        self._query(1, 0, self.n - 1, l, r, lists)
        return lists

    def _query(self, node: int, start: int, end: int, l: int, r: int, lists: List[tuple]):
        if r < start or end < l:
            return
        if l <= start and end <= r:
            lists.append((self.tree[node], self.prefix[node]))
            return
        mid = (start + end) // 2
        self._query(2 * node, start, mid, l, r, lists)
        self._query(2 * node + 1, mid + 1, end, l, r, lists)

    def get_sum_smallest(self, l: int, r: int, kk: int) -> int:
        if kk == 0:
            return 0
        lists = self.get_lists(l, r)
        lo = 0
        hi = len(self.all_vals) - 1
        while lo < hi:
            mid = (lo + hi) // 2
            v = self.all_vals[mid]
            cnt = sum(bisect.bisect_right(sl, v) for sl, _ in lists)
            if cnt >= kk:
                hi = mid
            else:
                lo = mid + 1
        v = self.all_vals[lo]
        count_less = 0
        sum_less = 0
        if lo > 0:
            vless = self.all_vals[lo - 1]
            count_less = sum(bisect.bisect_right(sl, vless) for sl, _ in lists)
            sum_less = sum(pl[bisect.bisect_right(sl, vless)] for sl, pl in lists)
        needed = kk - count_less
        return sum_less + needed * v

class Solution:
    def minOperations(self, nums: List[int], k: int, queries: List[List[int]]) -> List[int]:
        n = len(nums)
        q = [x // k for x in nums]
        mods = [x % k for x in nums]
        changes = [1 if mods[i] != mods[i + 1] else 0 for i in range(n - 1)]
        cum = [0] * n
        for i in range(1, n):
            cum[i] = cum[i - 1] + changes[i - 1]
        preq = [0] * (n + 1)
        for i in range(n):
            preq[i + 1] = preq[i] + q[i]
        all_vals = sorted(set(q))
        mst = MergeSortTree(q, all_vals)
        ans = []
        for l, r in queries:
            num_ch = cum[r] - cum[l]
            if num_ch > 0:
                ans.append(-1)
                continue
            m = r - l + 1
            total = preq[r + 1] - preq[l]
            s = m // 2
            sum_small_s = mst.get_sum_smallest(l, r, s)
            sum_small_cs = mst.get_sum_smallest(l, r, m - s)
            ops = total - sum_small_cs - sum_small_s
            ans.append(ops)
        return ans