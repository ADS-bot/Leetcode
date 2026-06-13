class Solution {
public:
    int maxScore(vector<int>& nums, int maxVal) {
        int n = nums.size();
        int e = maxVal;
        int i = 0;
        while (i < n)
        {
            if (nums[i] > e)
            {
                e = nums[i];
            }
            i++;
        }
        vector<int> f(e + 1, 0);
        i = 0;
        while (i < n)
        {
            f[nums[i]]++;
            i++;
        }
        vector<int> c(e + 1, 0);
        i = 1;
        while (i <= e)
        {
            int j = i;
            while (j <= e)
            {
                c[i] += f[j];
                j += i;
            }
            i++;
        }
        vector<int> h(e + 1, 0);
        vector<int> g;
        vector<int> p(e + 1, 0);
        h[1] = 1;
        i = 2;
        while (i <= e)
        {
            if (p[i] == 0)
            {
                g.push_back(i);
                h[i] = -1;
            }
            int j = 0;
            int z = g.size();
            while (j < z && i * g[j] <= e)
            {
                p[i * g[j]] = 1;
                if (i % g[j] == 0)
                {
                    h[i * g[j]] = 0;
                    break;
                }
                else
                {
                    h[i * g[j]] = -h[i];
                }
                j++;
            }
            i++;
        }
        vector<int> w(e + 1, 0);
        int d = 1;
        while (d <= e)
        {
            if (h[d] != 0)
            {
                int v = d;
                while (v <= e)
                {
                    w[v] += h[d] * c[d];
                    v += d;
                }
            }
            d++;
        }
        int s = -100005;
        int v = 1;
        while (v <= e)
        {
            int o = n - w[v];
            if (f[v] > 0)
            {
                int r = 0;
                if (v > 1)
                {
                    r = v - o + 1;
                }
                else
                {
                    r = 1;
                }
                if (r > s)
                {
                    s = r;
                }
            }
            if (v <= maxVal)
            {
                int r = 0;
                if (o > 0)
                {
                    r = v - o;
                }
                else
                {
                    r = v - 1;
                }
                if (r > s)
                {
                    s = r;
                }
            }
            v++;
        }
        return s;
    }
};