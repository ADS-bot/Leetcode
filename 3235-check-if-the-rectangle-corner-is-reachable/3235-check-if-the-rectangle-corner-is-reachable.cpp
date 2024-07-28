struct DSU {
private:
	std::vector<int> parent_or_size;
public:
	DSU(int n = 1): parent_or_size(n, -1) {}
	int get_root(int u) {
		if (parent_or_size[u] < 0) return u;
		return parent_or_size[u] = get_root(parent_or_size[u]);
	}
	int size(int u) { return -parent_or_size[get_root(u)]; }
	bool same_set(int u, int v) {return get_root(u) == get_root(v); }
	bool merge(int u, int v) {
		u = get_root(u), v = get_root(v);
		if (u == v) return false;
		if (parent_or_size[u] > parent_or_size[v]) std::swap(u, v);
		parent_or_size[u] += parent_or_size[v];
		parent_or_size[v] = u;
		return true;
	}
	std::vector<std::vector<int>> group_up() {
		int n = parent_or_size.size();
		std::vector<std::vector<int>> groups(n);
		for (int i = 0; i < n; ++i) {
			groups[get_root(i)].push_back(i);
		}
		groups.erase(std::remove_if(groups.begin(), groups.end(), [&](auto &s) { return s.empty(); }), groups.end());
		return groups;
	}
};

class Solution {
public:
    bool canReachCorner(int X, int Y, vector<vector<int>>& circles) {
        int n = circles.size();
        DSU D(n+4);
        using ll = long long int;

        for (int i = 0; i < n; ++i) {
            int x1 = circles[i][0], y1 = circles[i][1], r1 = circles[i][2];
            for (int j = i+1; j < n; ++j) {
                int x2 = circles[j][0], y2 = circles[j][1], r2 = circles[j][2];

                // intersect if dist((x1, y1), (x2, y2)) <= r1 + r2
                ll d = 1ll*(x1-x2)*(x1-x2) + 1ll*(y1-y2)*(y1-y2);
                ll d2 = 1ll*(r1+r2)*(r1+r2);
                if (d <= d2) D.merge(i, j);
            }

            for (int x : {0, X}) {
                // (x-x1)^2 + (y-y1)^2 = r1^2
                if (abs(x-x1) > r1) continue;

                ll d = 1ll*r1*r1 - 1ll*(x-x1)*(x-x1);
                long double rt = sqrtl(d);
                
                long double lo = y1 - rt, hi = y1 + rt;

                if (lo >= Y or hi <= 0) continue;
                if (x == 0) D.merge(i, n);
                else D.merge(i, n+1);
            }

            for (int y : {0, Y}) {
                if (abs(y-y1) > r1) continue;

                ll d = 1ll*r1*r1 - 1ll*(y-y1)*(y-y1);
                long double rt = sqrtl(d);
                
                long double lo = x1 - rt, hi = x1 + rt;

                if (lo >= X or hi <= 0) continue;
                if (y == 0) D.merge(i, n+2);
                else D.merge(i, n+3);
            }
        }

        if (D.same_set(n, n+1) or D.same_set(n, n+2) or D.same_set(n+2, n+3) or D.same_set(n+1, n+3)) return false;
        return true;
    }
};