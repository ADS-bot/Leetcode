class Solution {
public:
    int getLength(vector<int>& a) {
        int n=a.size(),r=1;
        for(int i=0;i<n;i++){
            unordered_map<int,int> c,f;
            for(int j=i;j<n;j++){
                int x=a[j],p=c[x]++;
                p&&(!--f[p]&&f.erase(p),0);
                f[p+1]++;
                int l=j-i+1,ok=l<2||c.size()<2;
                if(!ok&&f.size()==2){
                    auto u=f.begin(),v=next(u);
                    int x=u->first,y=v->first;
                    ok=(x==(y<<1))||(y==(x<<1));
                }
                ok&&(r=max(r,l));
            }
        }
        return r;
    }
};