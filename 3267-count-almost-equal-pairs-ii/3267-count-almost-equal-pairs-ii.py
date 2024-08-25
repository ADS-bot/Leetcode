class Solution:
    def countPairs(self, nums: List[int]) -> int:
        dic = defaultdict(int)
        ans = 0
        nums.sort(reverse = True)
        for x in nums:
            ans += dic[x]
            st = set()
            st.add(x)
            n = len(str(x))
            for i in range(n):
                for j in range(i, n):
                    for k in range(n):
                        for l in range(k, n):
                            s = list(str(x))
                            s[i], s[j] = s[j], s[i]
                            s[k], s[l] = s[l], s[k]
                            st.add(int("".join(s)))
            for u in st:
                dic[u] += 1
        return ans