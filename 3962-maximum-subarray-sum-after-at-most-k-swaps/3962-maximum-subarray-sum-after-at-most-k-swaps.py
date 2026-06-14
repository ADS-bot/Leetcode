class Solution:
    def maxSum(self, nums: list[int], k: int) -> int:
        N = len(nums)
        P = sum(1 for x in nums if x > 0)
        
        if k >= P:
            if P == 0:
                return max(nums)
            return sum(x for x in nums if x > 0)
            
        best_ans = -float('inf')
        current = 0
        for x in nums:
            current += x
            if current > best_ans:
                best_ans = current
            if current < 0:
                current = 0
                
        if k == 0:
            return best_ans
            
        sorted_nums = sorted(nums, reverse=True)
        Opt = [0] * (N + 1)
        for i in range(N):
            Opt[i+1] = Opt[i] + sorted_nums[i]
            
        for L in range(1, min(k, N) + 1):
            if Opt[L] > best_ans:
                best_ans = Opt[L]
                
        TopK_global = sorted_nums[:k]
        MaxGlobal = sum(x for x in TopK_global if x > 0)
        
        LeftTop = [[] for _ in range(N)]
        current_left = []
        for i in range(N):
            LeftTop[i] = current_left[::-1]
            bisect.insort(current_left, nums[i])
            if len(current_left) > k:
                current_left.pop(0)
                
        RightTop = [[] for _ in range(N)]
        current_right = []
        for i in range(N - 1, -1, -1):
            RightTop[i] = current_right[::-1]
            bisect.insort(current_right, nums[i])
            if len(current_right) > k:
                current_right.pop(0)
                
        for i in range(N):
            cur_sum = 0
            h = []
            sum_neg_h = 0
            for j in range(i, N):
                val = nums[j]
                cur_sum += val
                
                if len(h) < k:
                    heapq.heappush(h, -val)
                    if val < 0:
                        sum_neg_h += val
                elif val < -h[0]:
                    removed_val = -heapq.heapreplace(h, -val)
                    if removed_val < 0:
                        sum_neg_h -= removed_val
                    if val < 0:
                        sum_neg_h += val
                
                L = j - i + 1
                if L <= k:
                    continue
                    
                possible_max = cur_sum + MaxGlobal - sum_neg_h
                if Opt[L] < possible_max:
                    possible_max = Opt[L]
                    
                if possible_max > best_ans:
                    smallest_S = sorted([-x for x in h])
                    p1, p2 = 0, 0
                    exact_gain = 0
                    left_list = LeftTop[i]
                    right_list = RightTop[j]
                    len_l = len(left_list)
                    len_r = len(right_list)
                    
                    for x in range(len(smallest_S)):
                        val_O = -float('inf')
                        if p1 < len_l and p2 < len_r:
                            if left_list[p1] > right_list[p2]:
                                val_O = left_list[p1]
                                p1 += 1
                            else:
                                val_O = right_list[p2]
                                p2 += 1
                        elif p1 < len_l:
                            val_O = left_list[p1]
                            p1 += 1
                        elif p2 < len_r:
                            val_O = right_list[p2]
                            p2 += 1
                        else:
                            break
                            
                        if val_O > smallest_S[x]:
                            exact_gain += val_O - smallest_S[x]
                        else:
                            break
                            
                    if cur_sum + exact_gain > best_ans:
                        best_ans = cur_sum + exact_gain
                        
        return best_ans