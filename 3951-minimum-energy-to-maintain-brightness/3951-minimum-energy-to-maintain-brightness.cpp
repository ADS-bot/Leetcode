class Solution {
public:
    long long minEnergy(int n, int brightness, vector<vector<int>>& a) {
        long long bulbs = (brightness + 2) / 3;
        
        sort(a.begin(), a.end());
        
        long long time = 0;
        long long s = a[0][0];
        long long e = a[0][1];
        
        for (int i = 1; i < a.size(); i++) {
            if (a[i][0] <= e) {
                if (a[i][1] > e) {
                    e = a[i][1];
                }
            } else {
                time = time + (e - s + 1);
                s = a[i][0];
                e = a[i][1];
            }
        }
        
        time = time + (e - s + 1);
        
        long long ans = time * bulbs;
        return ans;
    }
};