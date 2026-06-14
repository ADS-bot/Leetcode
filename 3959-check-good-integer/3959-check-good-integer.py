class Solution:
    def checkGoodInteger(self, n: int) -> bool:
        ds=0
        ss=0
        for d in str(n):
            dt=int(d)
            ds+=dt
            ss+=dt*dt
        return (ss-ds)>=50
        