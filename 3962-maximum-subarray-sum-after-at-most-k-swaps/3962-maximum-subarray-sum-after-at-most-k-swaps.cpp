class Solution{
public:
long long maxSum(vector<int>&a,int b){
 struct F{int n,d;vector<int>c;vector<long long>s;F(int x):n(x),d(1),c(x+1),s(x+1){for(;d*2<=n;d*=2);}void a(int i,int q,int v){for(;i<=n;i+=i&-i)c[i]+=q,s[i]+=1LL*q*v;}int C(int i){int r=0;for(;i;i-=i&-i)r+=c[i];return r;}long long S(int i){long long r=0;for(;i;i-=i&-i)r+=s[i];return r;}int K(int q){int i=0;for(int j=d;j;j>>=1)i+j<=n&&c[i+j]<q&&(q-=c[i+=j]);return i+1;}long long L(int q,vector<int>&v){int z=C(n),i,p;return q<1?0:q>=z?S(n):(i=K(q),p=C(i-1),S(i-1)+1LL*(q-p)*v[i-1]);}};
 int n=a.size(),m;vector<int>v=a,p(n);sort(v.begin(),v.end());v.erase(unique(v.begin(),v.end()),v.end());m=v.size();
 long long r=-(1LL<<60),t=0;
 for(int i=0;i<n;i++)p[i]=lower_bound(v.begin(),v.end(),a[i])-v.begin()+1,t+=a[i];
 F w(m);for(int i=0;i<n;i++)w.a(p[i],1,a[i]);
 for(int l=0;l<n;l++){
  F f(m),g=w;long long s=0;
  for(int i=l;i<n;i++){
   g.a(p[i],-1,a[i]),f.a(p[i],1,a[i]),s+=a[i];
   int e=i-l+1,u=n-e,h=min({b,e,u}),z=0;
   while(z<h){int q=z+h+1>>1;v[g.K(u-q+1)-1]>v[f.K(q)-1]?z=q:h=q-1;}
   r=max(r,s+(z?t-s-g.L(u-z,v)-f.L(z,v):0));
  }
 }
 return r;
}
};