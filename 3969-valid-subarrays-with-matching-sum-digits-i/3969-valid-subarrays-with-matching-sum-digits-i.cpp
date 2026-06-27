class Solution {
public:
    int countValidSubarrays(vector<int>& nums, int x) {
        int n = nums.size();
        int count = 0;
        for (int l = 0; l < n; l++) {
            long long sum = 0;
            for (int r = l; r < n; r++) {
                sum += nums[r];
                int last = sum % 10;
                long long tmp = sum;
                while (tmp >= 10) tmp /= 10;
                int first = (int)tmp;
                if (first == x && last == x) count++;
            }
        }
        return count;
    }
};