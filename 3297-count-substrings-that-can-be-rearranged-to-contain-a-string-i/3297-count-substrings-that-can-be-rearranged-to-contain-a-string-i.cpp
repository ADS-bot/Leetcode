class Solution {
public:
    long long validSubstringCount(string word1, string word2) {
        vector<int> required(26, 0);
        for(char c : word2) required[c - 'a']++;
        int requiredUnique = 0;
        for(int cnt : required) if(cnt > 0) requiredUnique++;
        vector<int> window(26);
        int formed = 0;
        long long res = 0;
        int left = 0;
        for(int right = 0; right < word1.size(); right++){
            char c = word1[right];
            window[c - 'a']++;
            if(required[c - 'a'] > 0 && window[c - 'a'] == required[c - 'a']) formed++;
            while(left <= right && formed == requiredUnique){
                res += word1.size() - right;
                char lc = word1[left];
                window[lc - 'a']--;
                if(required[lc - 'a'] > 0 && window[lc - 'a'] < required[lc - 'a']) formed--;
                left++;
            }
        }
        return res;
    }
};