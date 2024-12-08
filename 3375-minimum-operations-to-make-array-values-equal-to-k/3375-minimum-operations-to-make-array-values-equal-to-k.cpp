#include <vector>
#include <set>

class Solution {
public:
    int minOperations(std::vector<int>& nums, int k) {
        for(auto num : nums){
            if(num < k){
                return -1;
            }
        }
        
        std::set<int> distinctGreater;
        for(auto num : nums){
            if(num > k){
                distinctGreater.insert(num);
            }
        }
        

        return distinctGreater.size();
    }
};