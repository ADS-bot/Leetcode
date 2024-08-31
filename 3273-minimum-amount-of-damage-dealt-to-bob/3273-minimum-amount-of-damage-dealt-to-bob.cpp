class Solution {
public:
    long long minDamage(int power, vector<int>& damage, vector<int>& health) {
        #define ld long double
        #define ll long long
        int n=damage.size();
        vector<array<ld, 3>> v(n);
        for(int i=0; i<n; i++){
            int hp = (health[i]+power-1)/power;
            ld dmg = damage[i];
            v[i] = {dmg / (ld)hp, dmg, (ld) hp};
        }
        sort(v.rbegin(), v.rend());
        ll ans=0, T=0;
        for(auto &[_, dmg, hp] : v){
            T += (ll) (hp + 0.00000001);
            ans += (ll) (dmg + 0.00000001) * T;
        }
        return ans;
    }
};