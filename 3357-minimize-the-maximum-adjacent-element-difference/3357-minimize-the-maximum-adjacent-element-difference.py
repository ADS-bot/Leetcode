class Solution(object):
    def minDifference(self, nums):
        def work(k):
            n = len(nums)
            for i in range(1,n):
                if nums[i-1]!=-1 and nums[i]!=-1 and abs(nums[i]-nums[i-1])>k:
                    return False
            q = []
            for i in range(n):
                if nums[i]==-1:
                    if i>0 and nums[i-1]!=-1:
                        q.append(nums[i-1])
                    if i<n-1 and nums[i+1]!=-1:
                        q.append(nums[i+1])
            if len(q)==0:
                return True
            q = sorted(q)
            L = q[0] + k
            R = q[-1] - k
            if k==3:
                print(L,R)
            if L>=R:
                return True
            l = 0
            r = 0
            while l<n:
                if nums[l]!=-1:
                    l+=1
                    continue
                r = l
                while r+1<n and nums[r+1]==-1:
                    r += 1
                if l>0 and r+1<n:
                    a,b = nums[l-1], nums[r+1]
                    a,b = min(a,b), max(a,b)
                    if (abs(a-L)<=k and abs(b-L)<=k) or (abs(a-R)<=k and abs(b-R)<=k):
                        pass
                    else:
                        if r-l+1==1:
                            return False
                        else:
                            if R-L>k:
                                return False
                        
                            
                l = r+1
            return True
                    
        l = 0
        r = int(2e9)
        ans = int(2e9)
        while l<=r:
            mid = (l+r)//2
            if work(mid):
                ans = mid
                r = mid-1
            else:
                l = mid+1
        return ans