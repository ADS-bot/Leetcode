class Solution {
public:
    int maxDistinct(string s) {
        vector<int> v(26,0);
        int n = (int)s.size();
        int c = 0;
        for(int i=0;i<n;++i){
            int j = s[i]-'a';
            if(!v[j]){
                v[j]=1;
                ++c;
            }
        }
        return c;
    }
};