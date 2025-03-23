class Solution {
public:
    long long minOperations(vector<vector<int>>& queries) {
        static long long p4[16], psum[16];
        p4[0]=1; 
        for(int i=1;i<16;i++) p4[i]=p4[i-1]*4;
        psum[0]=0; 
        for(int i=1;i<16;i++){
            psum[i]=psum[i-1]+(long long)i*3*p4[i-1];
        }
        auto f=[&](long long x){
            if(x<1) return 0LL;
            int i=0;
            while(i+1<16 && p4[i+1]<=x) i++;
            long long r=psum[i]+(x-p4[i]+1)*(i+1);
            return r;
        };
        long long ans=0;
        for(auto &q:queries){
            long long l=q[0], r=q[1];
            long long s=f(r)-f(l-1);
            ans+=(s+1)/2;
        }
        return ans;
    }
};