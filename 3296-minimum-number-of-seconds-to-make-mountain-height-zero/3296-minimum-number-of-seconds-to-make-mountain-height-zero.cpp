class Solution {
public:
    long long minNumberOfSeconds(int mountainHeight, vector<int>& workerTimes) {
        auto canFinish = [&](long long T) -> long long {
            long long total = 0;
            for(auto t: workerTimes){
                if(t > T) continue;
                long long x = floor( (sqrt(1 + 8.0 * T / t) -1 ) / 2 );
                total += x;
                if(total >= mountainHeight) return total;
            }
            return total;
        };
        long long left = 0, right = 1e18, answer = -1;
        while(left <= right){
            long long mid = left + (right - left) / 2;
            long long total = canFinish(mid);
            if(total >= mountainHeight){
                answer = mid;
                right = mid -1;
            }
            else{
                left = mid +1;
            }
        }
        return answer;
    }
};