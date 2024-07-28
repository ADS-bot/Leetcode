class Solution(object):
    def numberOfSubstrings(self, s):
        """
        :type s: str
        :rtype: int
        """
        n = len(s)
        v = deque()
        for i in range(n):
            if s[i] == '0':
                v.append(i)
        ans = 0
        for i in range(n):
            while v and v[0] < i:
                v.popleft()
            prev = i - 1
            cnt1 = 0
            cnt0 = 0
            for x in v:
                if x - prev - 1 >= max(0, cnt0 * cnt0 - cnt1):
                    ans += x - prev - 1 - max(0, cnt0 * cnt0 - cnt1)
                    if max(0, cnt0 * cnt0 - cnt1) != 0:
                        ans += 1
                cnt1 += x - prev - 1
                prev = x
                cnt0 += 1
                if cnt0 * cnt0 <= cnt1:
                    ans += 1
                if cnt0 * cnt0 > cnt1 + n - x - 1:
                    break
                prev = x
            if n - prev - 1 >= max(0, cnt0 * cnt0 - cnt1):
                ans += n - prev - 1 - max(0, cnt0 * cnt0 - cnt1)
                if max(0, cnt0 * cnt0 - cnt1) != 0:
                    ans += 1
        return ans