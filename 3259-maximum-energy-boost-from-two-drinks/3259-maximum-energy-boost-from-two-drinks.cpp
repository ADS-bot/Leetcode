class Solution {
public:
    long long maxEnergyBoost(vector<int>& energyDrinkA, vector<int>& energyDrinkB) {
        int n = energyDrinkA.size();
        vector<long long> dpa(n + 2), dpb(n + 2);
        for (int i = n-1; i >= 0; i--) {
            dpa[i] = energyDrinkA[i] + max(dpa[i+1], dpb[i+2]);
            dpb[i] = energyDrinkB[i] + max(dpb[i+1], dpa[i+2]);
        }
        return max(dpa[0], dpb[0]);
    }
};