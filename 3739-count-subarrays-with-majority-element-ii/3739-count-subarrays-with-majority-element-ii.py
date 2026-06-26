class Solution:
    def countMajoritySubarrays(self, nums: List[int], target: int) -> int:
        t = [1 if x == target else -1 for x in nums]
        pre = [0]
        for x in t: pre.append(pre[-1] + x)
        vals = sorted(set(pre))
        idx = {v: i+1 for i, v in enumerate(vals)}
        bit = [0] * (len(vals) + 1)
        def add(i):
            while i < len(bit):
                bit[i] += 1
                i += i & -i
        def query(i):
            s = 0
            while i:
                s += bit[i]
                i -= i & -i
            return s
        ans = 0
        for v in pre:
            k = idx[v]
            ans += query(k - 1)
            add(k)
        return ans