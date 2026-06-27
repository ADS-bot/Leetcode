class Solution {
public:
    int shortestPath(int n, vector<vector<int>>& edges, string labels, int k) {
        vector<vector<pair<int, int>>> arr(n);
        for (auto& vec : edges) {
            int x = vec[0];
            int y = vec[1];
            int z = vec[2];
            arr[x].push_back({y, z});
        }

        vector<vector<int>> v(n, vector<int>(k + 1, 2e9));
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

        v[0][1] = 0;
        pq.push({0, 0, 1});

        while (!pq.empty()) {
            vector<int> vec = pq.top();
            pq.pop();

            int a = vec[0];
            int b = vec[1];
            int c = vec[2];

            if (a > v[b][c]) continue;
            if (b == n - 1) return a;

            for (auto& pre : arr[b]) {
                int x = pre.first;
                int y = pre.second;
                int z = (labels[b] == labels[x]) ? c + 1 : 1;

                if (z <= k) {
                    if (a + y < v[x][z]) {
                        v[x][z] = a + y;
                        pq.push({a + y, x, z});
                    }
                }
            }
        }
        return -1;
    }
};