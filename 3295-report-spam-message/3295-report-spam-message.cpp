class Solution {
public:
    bool reportSpam(vector<string>& message, vector<string>& bannedWords) {
        unordered_set<string> banned(bannedWords.begin(), bannedWords.end());
        int count = 0;
        for(const auto& word : message){
            if(banned.find(word) != banned.end()){
                count++;
                if(count >=2) return true;
            }
        }
        return false;
    }
};