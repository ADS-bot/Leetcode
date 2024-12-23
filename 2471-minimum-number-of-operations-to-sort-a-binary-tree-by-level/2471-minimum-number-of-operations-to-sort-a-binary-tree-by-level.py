class Solution:
    def minimumOperations(self, root: Optional[TreeNode]) -> int:
        def swapcount(arr):
            idx = dict()
            n = len(arr)
            swaps = 0
            for i in range(n):
                idx[arr[i]] = i
            sortedArr = sorted(arr)
            for i in range(n):
                if arr[i] != sortedArr[i]:
                    v1 = arr[i]
                    v2 = sortedArr[i]
                    arr[idx[v1]], arr[idx[v2]] = arr[idx[v2]], arr[idx[v1]]
                    idx[v1], idx[v2] = idx[v2], idx[v1]
                    swaps += 1
            return swaps
        
        levels = defaultdict(list)
        queue = [(root, 0)]
        while queue:
            node, lvl = queue.pop(0)
            levels[lvl].append(node.val)
            if node.left:
                queue.append((node.left, lvl+1))
            if node.right:
                queue.append((node.right, lvl+1))       
        
        res = 0
        for lvl, values in levels.items():
            res += swapcount(values)
        return res