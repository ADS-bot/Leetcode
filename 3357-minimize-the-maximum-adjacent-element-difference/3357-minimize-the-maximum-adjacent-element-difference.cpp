class Solution {
public:
    int minDifference(vector<int> &v) {
        auto check = [&](int64_t d) {
            int n = v.size();
            vector<int>v2;
            auto check1=[&]{
                for(int l=0,r;l<n;l=r){
                    for(r=l+1;r<n and v[r]==v[l];r++){}
                    if(v[l]==-1){
                        if(l)v2.push_back(v[l-1]);
                        if(r<n)v2.push_back(v[r]);
                    }
                }
                ranges::sort(v2);
                if(v2.empty())return true;
                if(v2.back()-v2[0]<=d*2)return true;
                return false;
            };
            if(check1())return true;
            auto check2=[&]{
                int64_t V0=v2[0]+d,V1=v2.back()-d;
                for(int l=0,r;l<n;l=r){
                    for(r=l+1;r<n and v[r]==v[l];r++){}
                    if(v[l]==-1){
                        if(l){
                            int64_t L=v[l-1];
                            if(r<n){
                                int64_t R=v[r];
                                if((abs(V0-L)<=d and abs(V0-R)<=d) or 
                                   (r-l>1 and  abs(V0-L)<=d and abs(V1-R)<=d and abs(V0-V1)<=d) or
                                   (abs(V1-L)<=d and abs(V1-R)<=d) or 
                                   (r-l>1 and abs(V1-L)<=d and abs(V0-R)<=d and abs(V0-V1)<=d));
                                else return false;
                            }
                            else{
                                if(abs(V0-L)<=d or 
                                   abs(V1-L)<=d);
                                else return false;
                            }
                        }
                        else{
                            if(r<n){
                                int64_t R=v[r];
                                if(abs(V0-R)<=d or 
                                   abs(V1-R)<=d);
                                else return false;
                            }else{
                                ;
                            }
                        }
                    }
                }
                return true;
            };
            
            return check2();
        };
        int64_t low = 0, high = 10000000000;
        for(int i=1;i<v.size();i++)if(~v[i-1] and ~v[i])low=max<int64_t>(low,abs(v[i]-v[i-1]));
        while (low < high) {
            auto mid = (low + high) / 2;
            if (check(mid))
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }
};