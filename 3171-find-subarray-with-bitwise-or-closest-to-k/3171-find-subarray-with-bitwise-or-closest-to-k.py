class Solution:
    def minimumDifference(self, nums: List[int], k: int) -> int:
        Min=10**9
        dp=[0]*32
        def OR(a):
            d=bin(a)
            for k in range(32-(len(d)-2),32):
                if d[k-(32-len(d))]=='1':
                    dp[k]=dp[k]+1
            return 0
        def get():
            dd=''
            for l in range(32):
                if dp[l]!=0:
                    dd=dd+'1'
                else:
                    dd=dd+str(dp[l])
            return int(dd,2)
        j=0
        for i in range(len(nums)):
            OR(nums[i])
            Min=min(Min,abs(k-get()))
            while i>j and get()>=k:
                dd=bin(nums[j])
                for q in range(32-(len(dd)-2),32):
                    if dd[q-(32-len(dd))]=='1':
                        dp[q]=dp[q]-1
                j=j+1
                Min=min(Min,abs(k-get()))
        return Min