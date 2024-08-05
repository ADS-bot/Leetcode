#include <bits/stdc++.h>
using namespace std;
#define SZ 63333
#define fi first
#define se second
typedef long long ll;
#define mp make_pair
#define pb push_back
#define pii pair<int,int>
int len(int x,int y) {
    return (y-x+1);
}
int len(pii s) {
    return len(s.fi,s.se);
}
struct BB {
int bs[SZ];
void edt(int x,int y) {
    x=SZ-10-x;
    for(;x<SZ;x+=x&-x) bs[x]+=y;
}
int qry(int x) {
    x=SZ-10-x;
    int ans=0;
    for(;x;x-=x&-x) ans+=bs[x];
    return ans;
}
void clr(int x) {
    x=SZ-10-x;
    for(;x<SZ;x+=x&-x) bs[x]=0;
}
}B[2];
void edt(int x,int y) {
    cerr<<"edt::"<<x<<" "<<y<<"\n";
    B[1].edt(x,x*y);
    B[0].edt(x,y);
}
int qry(int x) {
    return B[1].qry(x)+(1-x)*B[0].qry(x);
}
class Solution {
public:
    vector<int> numberOfAlternatingGroups(vector<int>& colors, vector<vector<int>>& queries) {
        map<int,int> C;
        int n=colors.size();
        for(int i=0;i<colors.size();++i) {
            int r=i;
            while(r<colors.size() && (colors[r]+colors[i]+r+i)%2==0) ++r;
            C[i]=r-1;
            edt(r-i,1);
            i=r-1;
        }
        vector<int> aa;
        for(auto q:queries) {
            if(q[0]==1) {
                int ans=qry(q[1]);
                auto A=*C.begin(),B=*C.rbegin();
                if(A!=B) {
                    // see if we can glue them
                    if(colors[0]!=colors.back()) {
                        int l1=A.se-A.fi+1;
                        int l2=B.se-B.fi+1;
                        ans-=max(l1-q[1]+1,0);
                        ans-=max(l2-q[1]+1,0);
                        ans+=max(l1+l2-q[1]+1,0);
                    }
                }
                else if(colors[0]!=colors.back()) {
                    ans=n;
                }
                aa.pb(ans);
            }
            else {
                int x=q[1],y=q[2];
                if(colors[x]==y) continue;
                colors[x]=y;
                // find the block x is in
                auto it=C.upper_bound(x);
                --it;
                assert(it!=C.end()&&it->fi<=x&&it->se>=x);
                int L=it->fi,R=it->se;
                edt(len(*it),-1);
                C.erase(it);
                int ML=x,MR=x;
                if(L!=ML) {
                    C[L]=x-1;
                    edt(len(L,x-1),1);
                }
                else {
                    if(x&&colors[x]!=colors[x-1]) {
                        // check prev block
                        auto it=C.upper_bound(x);
                        --it;
                        ML=it->fi;
                        edt(len(*it),-1);
                        C.erase(it);
                    }
                }
                if(R!=MR) {
                    C[x+1]=R;
                    edt(len(x+1,R),1);
                }else{
                    if(x+1<colors.size()&&colors[x+1]!=colors[x]) {
                        auto it=C.upper_bound(x);
                        MR=it->se;
                        edt(len(*it),-1);
                        C.erase(it);
                    }
                }
                C[ML]=MR;
                edt(len(ML,MR),1);
            }
        }
        for(int i=0;i<=n+2;++i) B[0].clr(i),B[1].clr(i);
        return aa;
    }
};