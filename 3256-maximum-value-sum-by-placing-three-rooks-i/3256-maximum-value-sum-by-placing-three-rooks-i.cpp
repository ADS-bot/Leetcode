class Solution {
public:
    using ll = long long;
    long long maximumValueSum(vector<vector<int>>& board) {
        ll n = board.size(), m = board[0].size();
        ll no_nodes = 2 + n + m;

        vector<vector<ll> > adj(no_nodes);
        vector<vector<ll> > capacity(no_nodes, vector<ll>(no_nodes, 0));
        vector<vector<ll> > cost(no_nodes, vector<ll>(no_nodes, 0));
    
        for (ll i = 0; i < n; i++) {
            adj[0].push_back(2 + i);
            capacity[0][2 + i] = 1;
        }
        for (ll i = 0; i < m; i++) {
            capacity[2 + n + i][1] = 1;
            adj[2 + n + i].push_back(1);
        }
        for (ll r = 0; r < n; r++)
            for (ll c = 0; c < m; c++) {
                adj[2 + r].push_back(2 + n + c);
                capacity[2 + r][2 + n + c] = 1;
                cost[2 + r][2 + n + c] = -board[r][c];
            }
    
        ll source = 0;
        ll dest = 1;
        ll sz = adj.size();
 
        // add reverse edges
        for (ll v = 0; v < sz; v++)
            for (ll u: adj[v]) {
                if (capacity[u][v] != 0) continue;
                // the reverse edge u->v already exists in the graph and has some capacity
    
                adj[u].push_back(v);
                cost[u][v] = -cost[v][u];
            }
    
        vector<vector<ll> > flow(sz, vector<ll>(sz, 0)); // flow[i][j] is the current flow from i to j
    
        vector<ll> par(sz, -1); // to keep track of the augmenting path
        function<bool()> find_aug_path = [&]() {
            vector<ll> dis(sz, LLONG_MAX);
            dis[source] = 0;
            vector<bool> in_q(sz, false);
    
            queue<ll> q;
            q.push(source);
            in_q[source] = true;
    
            while (!q.empty()) {
                ll v = q.front();
                q.pop();
    
                for (ll u: adj[v]) {
                    if ((flow[v][u] >= capacity[v][u])) continue;
    
                    if (dis[v] + cost[v][u] < dis[u]) {
                        dis[u] = dis[v] + cost[v][u];
                        par[u] = v;
                        if (!in_q[u]) {
                            q.push(u);
                            in_q[u] = true;
                        }
                    }
                }
    
                in_q[v] = false;
            }
    
            return dis[dest] != LLONG_MAX;
        };
    
        ll curr_flow = 0;
        ll curr_cost = 0;
        while (curr_flow < 3) {
            fill(par.begin(), par.end(), -1);
            if (!find_aug_path()) break;
    
            ll min_edge = LLONG_MAX;
    
            ll curr = dest;
            while (curr != source) {
                ll prev_node = par[curr];
                min_edge = min(min_edge, capacity[prev_node][curr] - flow[prev_node][curr]);
                curr = prev_node;
            }

            min_edge = min(min_edge, 3 - curr_flow);
    
            curr = dest;
            while (curr != source) {
                flow[par[curr]][curr] += min_edge;
                flow[curr][par[curr]] -= min_edge;
                curr_cost += min_edge * cost[par[curr]][curr];
                curr = par[curr];
            }
            curr_flow += min_edge;
        }
    
        return -curr_cost;
    }
};