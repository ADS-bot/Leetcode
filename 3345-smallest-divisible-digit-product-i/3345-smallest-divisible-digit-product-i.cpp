class Solution {
public:
    int smallestNumber(int n, int t) {
        
        auto check = [&](int n)
        {
            string s = to_string(n);
            int x = 1;
            for (auto c : s)
                x *= c - '0';
            return x % t == 0;
        };
        
        while (1)
        {
            if (check(n)) break;
            n += 1;
        }
        
        return n;
    }
};