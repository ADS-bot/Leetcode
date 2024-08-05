#include <bits/stdc++.h>
using namespace std;
#define SZ 1333333
#define fi first
#define se second
typedef long long ll;
#define mp make_pair
#define pb push_back
#define pii pair<int,int>

class Solution {
public:
    vector<int> shortestDistanceAfterQueries(int n, vector<vector<int>>& queries) {
        int ans=0;
        map<int,int> pr;
        vector<int> aa;
        for(auto q:queries) {
            int a=q[0],b=q[1];
            auto s=pr.upper_bound(a);
            if(s!=pr.begin()) {
                auto u=--s;
                // check if (a,b) is covered
                if(u->se>=b) goto skip;
            }
            // remove all the covered
            s=pr.lower_bound(a);
            while(s!=pr.end()&&s->se<=b)
                ans-=s->se-s->fi-1,
                s=pr.erase(s);
            pr[a]=b;
            ans+=b-a-1;
            skip:;
            aa.pb(n-1-ans);
        }
        return aa;
    }
};