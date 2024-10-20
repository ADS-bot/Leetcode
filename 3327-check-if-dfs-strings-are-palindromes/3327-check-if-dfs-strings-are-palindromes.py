class Solution:
    def findAnswer(self, parent: List[int], S: str) -> List[bool]:
        n = len(S)
        mod1, mod2 = 10**9 + 7, 10**9 + 9
        B1, B2 = 911, 3571
        max_len = n + 1

        pow_B1 = [1] * max_len
        pow_B2 = [1] * max_len
        for i in range(1, max_len):
            pow_B1[i] = (pow_B1[i - 1] * B1) % mod1
            pow_B2[i] = (pow_B2[i - 1] * B2) % mod2

        children = [[] for _ in range(n)]
        root = -1
        for i in range(n):
            if parent[i] == -1:
                root = i
            else:
                children[parent[i]].append(i)

        len_subtree = [0] * n
        H1 = [0] * n
        H2 = [0] * n
        HR1 = [0] * n
        HR2 = [0] * n
        answer = [False] * n

        def dfs(u):
            len_subtree[u] = 1
            H1_u, H2_u = 0, 0
            HR1_u = HR2_u = ord(S[u])
            for c in children[u]:
                dfs(c)
            for c in children[u]:
                H1_u = (H1_u * pow_B1[len_subtree[c]] + H1[c]) % mod1
                H2_u = (H2_u * pow_B2[len_subtree[c]] + H2[c]) % mod2
                len_subtree[u] += len_subtree[c]
            H1_u = (H1_u * B1 + ord(S[u])) % mod1
            H2_u = (H2_u * B2 + ord(S[u])) % mod2
            H1[u], H2[u] = H1_u, H2_u
            for c in reversed(children[u]):
                HR1_u = (HR1_u * pow_B1[len_subtree[c]] + HR1[c]) % mod1
                HR2_u = (HR2_u * pow_B2[len_subtree[c]] + HR2[c]) % mod2
            HR1[u], HR2[u] = HR1_u, HR2_u
            answer[u] = (H1[u] == HR1[u] and H2[u] == HR2[u])

        dfs(root)
        return answer